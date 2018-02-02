package com.nevermore.oceans.common;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/1/31 0031.
 */

public class EasyDialogFragment extends DialogFragment {

    private SparseArray<View.OnClickListener> clickListenerSparseArray = new SparseArray<>();

    public static EasyDialogFragment newInstance(){
        EasyDialogFragment fragment = new EasyDialogFragment();
        fragment.setStyle(STYLE_NO_TITLE,0);
        return fragment;
    }

    private int contentViewId;

    public EasyDialogFragment setContentViewId(int contentViewId) {
        this.contentViewId = contentViewId;
        return this;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Dialog dialog = getDialog();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0x00000000));
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(contentViewId, container, false);
        if(clickListenerSparseArray.size()!=0){
            for (int i = 0; i < clickListenerSparseArray.size(); i++) {
                int viewId = clickListenerSparseArray.keyAt(i);
                View view = rootView.findViewById(viewId);
                if(view==null){
                    throw new NullPointerException("not find view ");
                }
                View.OnClickListener onClickListener = clickListenerSparseArray.get(viewId);
                view.setOnClickListener(onClickListener);
            }
        }
        return rootView;
    }


    public EasyDialogFragment setOnClickListener(int viewId, View.OnClickListener listener){
        clickListenerSparseArray.put(viewId,listener);
        return this;
    }
}
