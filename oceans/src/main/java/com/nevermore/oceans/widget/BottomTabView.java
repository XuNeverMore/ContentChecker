package com.nevermore.oceans.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
 * author XuNeverMore
 * create on 2017/5/16 0016
 * github https://github.com/XuNeverMore
 */

public class BottomTabView extends RelativeLayout {
    private static final String TAG = "BottomTabView";
    private Drawable drawableN;
    private Drawable drawableS;
    private ImageView tabIcon;
    private TextView tabText;
    private String text;
    private TextView tvCount;
    private int textColorNoraml = 0xff8E8E93;
    private int textColorSelected = 0xff102B50;
    private View redPoint;

    public BottomTabView(@NonNull Context context) {
        this(context, null);
    }

    public BottomTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.item_bottom, this, true);

        tabIcon = (ImageView) findViewById(R.id.tab_icon);
        tabText = (TextView) findViewById(R.id.tab_text);
        tvCount = (TextView) findViewById(R.id.tv_count);

        redPoint = (View) findViewById(R.id.red_point);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomTabView);
        drawableN = typedArray.getDrawable(R.styleable.BottomTabView_tabIconNormal);
        drawableS = typedArray.getDrawable(R.styleable.BottomTabView_tabIconSelected);
        textColorNoraml = typedArray.getColor(R.styleable.BottomTabView_tabTextColorNormal, textColorNoraml);
        textColorSelected = typedArray.getColor(R.styleable.BottomTabView_tabTextColorSelected, textColorSelected);

        tabText.setTextColor(textColorNoraml);
        text = typedArray.getString(R.styleable.BottomTabView_tabText);

        if (drawableN != null) {
            tabIcon.setImageDrawable(drawableN);
        }
        if (!TextUtils.isEmpty(text)) {
            tabText.setText(text);
        }

        typedArray.recycle();
    }

    public void setSelectState(boolean select){
        setSelected(select);
        tabIcon.setImageDrawable(select?drawableS:drawableN);
        tabText.setTextColor(select?textColorSelected:textColorNoraml);

    }


    public void setRedPointVisiable(boolean visiable){
        redPoint.setVisibility(visiable?VISIBLE:GONE);
    }

    public String getTabText(){

        return tabText.getText().toString().trim();
    }

    public void setTabText(String text){
        tabText.setText(text);
    }

    public void setCount(int count){
        tvCount.setVisibility(count>0?VISIBLE:GONE);
        tvCount.setText(""+count);
    }


}
