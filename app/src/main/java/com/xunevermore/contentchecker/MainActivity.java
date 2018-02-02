package com.xunevermore.contentchecker;

import android.text.TextUtils;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.nevermore.oceans.uits.ContentBody;
import com.nevermore.oceans.uits.ContentChecker;
import com.nevermore.oceans.uits.LengthCondition;
import com.nevermore.oceans.uits.NonNullCondition;
import com.nevermore.oceans.uits.PhoneNumCondition;
import com.nevermore.oceans.uits.SubmitUtil;
import com.xunevermore.contentchecker.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        SubmitUtil.newInstance()
                .setSubmitBtn(mDataBing.btnGetCode)
                .setEditTexts(mDataBing.edtPhone)
                .syncOperations();

        SubmitUtil.newInstance()
                .setSubmitBtn(mDataBing.btnRegister)
                .setEditTexts(mDataBing.edtPhone,mDataBing.edtPassword)
                .syncOperations();

        mDataBing.btnGetCode.setOnClickListener(v -> {
            mDataBing.btnGetCode.startCountDown();
            mDataBing.btnGetCode.setEnabled(false);
        });
        
        //点击注册
        mDataBing.btnRegister.setOnClickListener(v -> {

            String phoneNum = mDataBing.edtPhone.getText().toString().trim();
            String password = mDataBing.edtPassword.getText().toString().trim();
            ContentBody phoneNumBody = new ContentBody("手机号", phoneNum);

            ContentBody passwordBody = new ContentBody("密码", password);

            boolean result = ContentChecker.getCheckMachine()
                    .putChecker(ContentChecker.getChecker(phoneNumBody)//手机号检查
//                            .addCondition(new NonNullCondition())
                            .addCondition(new PhoneNumCondition()))
                    .putChecker(ContentChecker.getChecker(passwordBody)//密码检查
//                            .addCondition(new NonNullCondition())
                            .addCondition(new LengthCondition(6)))
                    .checkAll();
            if(result){
                register(phoneNum,password);
            }

        });

    }

    private void register(String phoneNum, String password) {

        ToastUtils.showShort("开始注册");
    }


}
