package com.nevermore.oceans.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by Administrator on 2018/2/1 0001.
 */

public class ImageLoadUtil {


    /**
     * 加载圆形图片
     * @param context
     * @param path
     * @param imageView
     */
    public static void loadRoundImage(Context context,String path, ImageView imageView){
        Glide.with(context).load(path).transform(new GlideCircleTransform(context)).into(imageView);
    }

    public static void loadRoundImage(Context context,int resid,ImageView imageView){
        Glide.with(context).load(resid).transform(new GlideCircleTransform(context)).into(imageView);
    }


    /**
     * 加载圆角图片
     * @param context
     * @param path
     * @param imageView
     */
    public static void loadRoundCornerImage(Context context,String path,ImageView imageView){
        Glide.with(context).load(path).transform(new GlideRoundTransform(context)).into(imageView);
    }





}
