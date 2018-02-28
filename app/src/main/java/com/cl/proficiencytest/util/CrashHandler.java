package com.cl.proficiencytest.util;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.PrintStream;

/**
 * Created by tymonliang on 03/09/2017.
 * crash handling tool
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler instance;
    private Thread.UncaughtExceptionHandler mDeExceptionHandler;
    private Context mContext;
    private String tag = "CrashHandler";
    private CrashCallback mCrashCallback;

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        if (null == instance) {
            instance = new CrashHandler();
        }
        return instance;
    }

    public void init(Context ctx, CrashCallback mcback) {
        mContext = ctx;
        this.mCrashCallback = mcback;
        mDeExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        handleException(ex);
    }

    private void handleException(final Throwable ex) {
        mCrashCallback.OnCrash(ex);

        if (ex == null) {
            return;
        }
        Log.e("FreelancerTest", " crashHandler:" + ex.getLocalizedMessage());
        ex.printStackTrace();

        String logPath;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            try {
                logPath = Environment.getExternalStorageDirectory() .getAbsolutePath()
                        + File.separator
                        + File.separator
                        + "log";
                File logDir = new File(logPath);

                if (!logDir.exists()) logDir.mkdirs();

                PrintStream errs = new PrintStream(logPath
                        + File.separator
                        + mContext.getPackageName()
                        + System.currentTimeMillis()
                        + ".log");

                ex.printStackTrace(errs);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        final String msg = ex.getLocalizedMessage();
        Log.e(tag, "ex:" + msg);
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "Sorry, application goes wrong!" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

    /**
     * callback interface
     */
    public interface CrashCallback {
        void OnCrash(Throwable e);
    }
}
