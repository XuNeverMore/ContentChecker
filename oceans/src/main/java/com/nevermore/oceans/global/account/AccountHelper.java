package com.nevermore.oceans.global.account;

import android.content.Context;
import android.content.SharedPreferences;

import com.nevermore.oceans.ob.Dispatcher;
import com.nevermore.oceans.ob.SuperObservableManager;

/**
 * Created by Administrator on 2018/2/1 0001.
 */

public class AccountHelper {
    private static AccountHelper instance;

    public static final String ACCOUNT_DATA = "ACCOUNT_DATA";

    public static final String TOKEN = "token";
    public static final String PHONE_NUM = "phone_num";
    public static final String PASSWORD = "password";
    public static final String LOGIN_STATE = "loginState";//登录状态

    private AccountHelper() {

    }

    public static AccountHelper getInstance(){
        if(instance == null){
            synchronized (AccountHelper.class){
                if(instance == null){
                    instance = new AccountHelper();
                }
            }
        }

        return instance;
    }

    /**
     * 是否登录
     * @param context
     * @return
     */
    public boolean  isLogin(Context context){
        return getDefaultSp(context).getBoolean(LOGIN_STATE,false);
    }

    private SharedPreferences getDefaultSp(Context context) {
        return context.getSharedPreferences(ACCOUNT_DATA, Context.MODE_PRIVATE);
    }

    /**
     * 登录账户
     * @param context
     * @param phoneNum
     * @param password
     */
    public void loginAccount(Context context,String phoneNum,String password){
        getDefaultSp(context).edit()
                .putString(PHONE_NUM,phoneNum)
                .putString(PASSWORD,password)
                .putBoolean(LOGIN_STATE,true)
                .commit();
        SuperObservableManager.notify(LoginStateChangeable.class, new Dispatcher<LoginStateChangeable>() {
            @Override
            public void call(LoginStateChangeable loginStateChangeable) {
                loginStateChangeable.onLogin();
            }
        });
    }



    public void logout(Context context){
        getDefaultSp(context).edit()
                .putBoolean(LOGIN_STATE,false)
                .commit();
        SuperObservableManager.notify(LoginStateChangeable.class, new Dispatcher<LoginStateChangeable>() {
            @Override
            public void call(LoginStateChangeable loginStateChangeable) {
                loginStateChangeable.onLogout();
            }
        });
    }


}
