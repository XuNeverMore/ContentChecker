package com.nevermore.oceans.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nevermore.oceans.R;


/**
 *
 * @ClassName:TopBar

 * @PackageName:com.ixiangni.ui

 * @Create On 2017/6/16 0016   13:19

 * @Site:http://www.handongkeji.com

 * @author:xuchuanting

 * @Copyrights 2017/6/16 0016 handongkeji All rights reserved.
 */


public class TopBar extends FrameLayout{

    private final TextView tvCenter;
    private final TextView tvRight;
    private final ImageView ivFinish;
    private final ImageView ivRight,iv_right_add;

    public ImageView getIvFinish() {
        return ivFinish;
    }


    public TopBar(@NonNull Context context) {
        this(context,null);
    }

    public TopBar(@NonNull final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.top_bar_layout,this);

        ViewCompat.setElevation(findViewById(R.id.top_bar_relative),5);

        tvCenter = (TextView) findViewById(R.id.tv_center);
        tvRight = (TextView) findViewById(R.id.tv_right);
        ivFinish = (ImageView) findViewById(R.id.iv_finish);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        iv_right_add = (ImageView) findViewById(R.id.iv_right_add);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        String centerText = typedArray.getString(R.styleable.TopBar_centerText);
        boolean isadd_more = typedArray.getBoolean(R.styleable.TopBar_is_add_more, false);
        Drawable addDrawable = typedArray.getDrawable(R.styleable.TopBar_right_add_icom);
        Drawable drawable = typedArray.getDrawable(R.styleable.TopBar_rightIcon);

        if(drawable!=null){
            ivRight.setImageDrawable(drawable);
        }

        if (isadd_more){
            tvRight.setVisibility(GONE);
            iv_right_add.setVisibility(VISIBLE);
            if (addDrawable!=null){
                iv_right_add.setImageDrawable(addDrawable);
            }
        }
        boolean isHideBack = typedArray.getBoolean(R.styleable.TopBar_hideBack, false);

        if(!isHideBack){
            //点击返回关闭当前Activity
            ivFinish.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(context instanceof Activity){
                        ((Activity)context).onBackPressed();
                    }
                }
            });
        }else {//隐藏返回键
            ivFinish.setVisibility(GONE);
        }

        if(!TextUtils.isEmpty(centerText)){
            tvCenter.setText(centerText);
        }
        String rightText = typedArray.getString(R.styleable.TopBar_rightText);
        if(!TextUtils.isEmpty(rightText)){
            tvRight.setText(rightText);
        }
        typedArray.recycle();

    }

    public TextView getTvCenter() {
        return tvCenter;
    }

    public ImageView getIvRight() {
        return ivRight;
    }

    public TextView getTvRight() {
        return tvRight;
    }
    public void setRighIconVisible(boolean visible){
        ivRight.setVisibility(visible?VISIBLE:GONE);
    }

    public void setRightIcon(int imgRes){
        ivRight.setImageResource(imgRes);
    }

    public void setRightText(String text){
        tvRight.setText(text);
    }

    public void setOnRightIconClickListener(OnClickListener listener){
        ivRight.setOnClickListener(listener);
    }

    public void setCenterText(String text){
        tvCenter.setText(text);
    }

    public void setOnRightClickListener(OnClickListener listener){
        tvRight.setOnClickListener(listener);
    }

    public void setOnLeftClickListener(OnClickListener listener){
        ivFinish.setOnClickListener(listener);
    }
    public void setRightAddImageListener(OnClickListener listener){
        iv_right_add.setOnClickListener(listener);
    }

    public void setOnCenterTextClick(OnClickListener listener){
        tvCenter.setOnClickListener(listener);
    }
}
