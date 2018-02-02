package com.nevermore.oceans.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nevermore.oceans.R;


/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class MessageView extends FrameLayout {

    public ImageView ivStatus;
    public TextView tvSubject;
    public TextView tvContent;
    public TextView tvTime;
    public ImageView ivIcon;

    public MessageView(@NonNull Context context) {
        this(context,null);
    }

    public MessageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MessageView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.message_layout,this);

        tvSubject = (TextView) findViewById(R.id.tv_subject);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvTime = (TextView) findViewById(R.id.tv_time);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        ivStatus= (ImageView) findViewById(R.id.iv_status);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MessageView);

        String string = typedArray.getString(R.styleable.MessageView_subjectText);
        if(!TextUtils.isEmpty(string)){
            tvSubject.setText(string);
        }
        Drawable drawable = typedArray.getDrawable(R.styleable.MessageView_messageIcon);
        if(drawable!=null){
            ivIcon.setImageDrawable(drawable);
        }

        typedArray.recycle();

    }
}
