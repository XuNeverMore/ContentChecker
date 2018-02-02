package com.nevermore.oceans.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nevermore.oceans.R;


/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class EnterLayout extends LinearLayout {

    private TextView tvSubject;
    private TextView tvContent;
    private ImageView ivArrow;

    public EnterLayout(Context context) {
        this(context,null);
    }

    public EnterLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EnterLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.edit_linear_layout,this);
        tvSubject = (TextView) findViewById(R.id.tv_subject);
        tvContent = (TextView) findViewById(R.id.tv_content);
        ivArrow = (ImageView) findViewById(R.id.iv_arrow);



        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EnterLayout);

        boolean hide = typedArray.getBoolean(R.styleable.EnterLayout_hideArrow, false);
        if(hide){
            hideArrow();
        }


        String string = typedArray.getString(R.styleable.EnterLayout_textSubject);
        if(!TextUtils.isEmpty(string)){
            tvSubject.setText(string);
        }
        typedArray.recycle();
    }

    public void hideArrow() {
        ivArrow.setVisibility(INVISIBLE);

    }

    public String getContent(){
        return tvContent.getText().toString().trim();
    }

    public void setContent(String content){
        tvContent.setText(content);
    }
}
