package com.cl.proficiencytest;

import android.app.Activity;
import android.app.Application;

import com.cl.proficiencytest.util.CrashHandler;
import com.cl.proficiencytest.util.LogUtil;

import java.util.LinkedList;
import java.util.List;

public class App extends Application {
    private static final String TAG ="Proficiency-Test";
    private static App app;
    private static List<Activity> activityList = new LinkedList<>();

    public static synchronized App getAppInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext(), new CrashHandler.CrashCallback() {
            @Override
            public void OnCrash(Throwable e) {

            }
        });

    }

    /**
     * @param activity add an activity to the list
     */
    public void addActivity(Activity activity){
        if(activityList != null){
            if(!activityList.contains(activity)){
                activityList.add(activity);
                LogUtil.d(TAG, "activityList:size:"+activityList.size());
            }
        }
    }

    /**
     * @param activity  delete an activity from the list
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
        LogUtil.d(TAG, "activityList:size:"+activityList.size());
    }

    /**
     * end all activities
     */
    public static void finishAllActivity() {
        if (activityList == null) {
            return;
        }
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();
    }

    /**
     * exit app
     */
    public static void exitApp() {
        try {
            LogUtil.e(TAG, "app exit");
            finishAllActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
