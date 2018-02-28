package com.cl.proficiencytest.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cl.proficiencytest.R;


public class ImageLoader {

    public static void load(Context context, String url,  final ImageView iv){
        LogUtil.i("Glide URL ", url+"");
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                .listener(new RequestListener<String, GlideDrawable>() { // adjust image size
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        if (iv == null) {
//                            return false;
//                        }
//                        if (iv.getScaleType() != ImageView.ScaleType.FIT_XY) {
//                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//                        }
//                        ViewGroup.LayoutParams params = iv.getLayoutParams();
//                        int vw = iv.getWidth() - iv.getPaddingLeft() - iv.getPaddingRight();
//                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
//                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
//                        params.height = vh + iv.getPaddingTop() + iv.getPaddingBottom();
//                        iv.setLayoutParams(params);
//                        return false;
//                    }
//                })
                .into(iv);
    }
}
