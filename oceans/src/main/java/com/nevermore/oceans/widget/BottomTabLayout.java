package com.nevermore.oceans.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


/**
 *
 * author: XuNeverMore
 * create on 2017/5/16 0016
 * github https://github.com/XuNeverMore
 */


public class BottomTabLayout extends LinearLayout {
    public BottomTabLayout(Context context) {
        this(context, null);
    }

    public BottomTabLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        
        setOrientation(HORIZONTAL);//水平方向
        ViewCompat.setElevation(this,5);
    }

    private static final String TAG = "BottomTabLayout";

    private void setListeners() {
        int childCount = getChildCount();
        Log.i(TAG, "setListeners: "+childCount);

        for (int i = 0; i < childCount; i++) {

            final int position = i;

            final View child = getChildAt(i);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemTabClickListener != null) {

                        //防止选中的tab被重复点击
                        if(lastSelectedItemView!=null&&lastSelectedItemView==v){
                            return;
                        }
                        onItemTabClickListener.onItemTabClick(position, v);
                    }
                }
            });
        }
    }

    private int mSelected = 0 ;

    private OnItemTabClickListener onItemTabClickListener;

    public void setOnItemTabClickListener(OnItemTabClickListener onItemTabClickListener) {
        this.onItemTabClickListener = onItemTabClickListener;
        setListeners();
    }

    public void setUpWithViewPager(ViewPager viewPager){
        if(viewPager!=null&&viewPager.getAdapter()!=null){
            setOnItemTabClickListener(new OnItemTabClickListener() {
                @Override
                public void onItemTabClick(int position, View itemView) {

                    BottomTabLayout.this.selectItem(itemView);
                }
            });
        }
    }


    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        selectItem(savedState.selectedPoi);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {

        Parcelable parcelable = super.onSaveInstanceState();
        SavedState state = new SavedState(parcelable);
        state.selectedPoi = mSelected;
        return state;
    }

    static class SavedState extends BaseSavedState{

        int selectedPoi;

        SavedState(Parcelable parcelable){
            super(parcelable);
        }

        private SavedState(Parcel source) {
            super(source);
            selectedPoi = source.readInt();
        }



        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeValue(selectedPoi);
        }

        @Override
        public String toString() {
            return "CompoundButton.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " selected=" + selectedPoi + "}";
        }

        @SuppressWarnings("hiding")
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    @Override
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    @Override
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

    private View lastSelectedItemView;//上一个被选中的item

    public void selectItem(View itemView){

        //让上一个被选中的tab恢复原来状态
        if(lastSelectedItemView!=null){
            changeItemSelectState(lastSelectedItemView,false);
        }
        mSelected = indexOfChild(itemView);
        //选中itemView
        changeItemSelectState(itemView,true);
        //保存itemView 下次切换修改
        lastSelectedItemView = itemView;

    }


    public void selectItem(int position){
        if(position<getChildCount()){
            View childAt = getChildAt(position);
            mSelected = position;
            selectItem(childAt);
        }
    }


    //改变tab 选中状态
    private void changeItemSelectState(View view,boolean selected){

        if(view instanceof BottomTabView){
            ((BottomTabView) view).setSelectState(selected);
        }else {
            view.setSelected(selected);
        }

    }


    public interface OnItemTabClickListener {

        void onItemTabClick(int position, View itemView);
    }
}
