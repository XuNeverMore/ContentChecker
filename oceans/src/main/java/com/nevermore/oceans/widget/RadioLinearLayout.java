package com.nevermore.oceans.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2018/1/26 0026.
 */

public class RadioLinearLayout extends LinearLayout {
    public RadioLinearLayout(Context context) {
        this(context,null);
    }

    public RadioLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadioLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private View lastSelectedChileView;
    private int mSelectedPosition = 0;

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable = super.onSaveInstanceState();
        SaveState saveState = new SaveState(parcelable);

        return saveState;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        selectedChild(mSelectedPosition);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {

        SaveState saveState = (SaveState) state;
        super.onRestoreInstanceState(saveState.getSuperState());
        int selectedPoi = saveState.selectedPoi;
        selectedChild(selectedPoi);
    }

    private void selectedChild(int selectedPoi) {


        getChildAt(selectedPoi).setSelected(true);

    }

    static class SaveState extends BaseSavedState{

        int selectedPoi;

         SaveState(Parcelable superState) {
            super(superState);
        }

        SaveState(Parcel source) {
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
            return "RadioLinearLayout.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " mSelectedPosition=" + selectedPoi + "}";
        }

        @SuppressWarnings("hiding")
        public static final Parcelable.Creator<SaveState> CREATOR =
                new Parcelable.Creator<SaveState>() {
                    @Override
                    public SaveState createFromParcel(Parcel in) {
                        return new SaveState(in);
                    }

                    @Override
                    public SaveState[] newArray(int size) {
                        return new SaveState[size];
                    }
                };

    }



}
