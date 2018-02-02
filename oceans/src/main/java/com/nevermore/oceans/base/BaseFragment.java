package com.nevermore.oceans.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 2018/1/26 0026.
 */

public abstract class BaseFragment extends Fragment {


    protected void goActivity(Class<? extends Activity> clazz) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.startActivity(new Intent(getActivity(), clazz));
        }
    }
}
