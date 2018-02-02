package com.nevermore.oceans.uits;

/**
 * Created by Administrator on 2018/2/2 0002.
 */

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

/**
 *
 * @author XuNeverMore
 * @QQ 1045530120
 * @create on 2018/2/2 0002
 * @github https://github.com/XuNeverMore
 */

public class SubmitUtil implements TextWatcher {


    private Button btnSubmit;//提交按钮
    private EditText[] editTexts;//需要填写内容的输入框


    private SubmitUtil() {

    }

    public SubmitUtil(Button btnSubmit, EditText... editTexts) {
        this.btnSubmit = btnSubmit;
        this.editTexts = editTexts;
    }


    public static SubmitUtil newInstance(){
        return new SubmitUtil();
    }

    public SubmitUtil setSubmitBtn(Button btnSubmit){
        this.btnSubmit = btnSubmit;
        return this;
    }

    public SubmitUtil setEditTexts(EditText... editTexts){
        this.editTexts = editTexts;
        return this;
    }

    /**
     * 同步操作
     */
    public SubmitUtil syncOperations(){
        for(EditText e:editTexts){
            e.addTextChangedListener(this);
        }
        checkState();
        return this;
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        checkState();
    }

    private void checkState() {
        boolean notEmpty = isAllEditTextNotEmpty();
        btnSubmit.setEnabled(notEmpty);
    }

    /**
     * 所有EditText输入内容均非空
     * @return
     */
    private boolean isAllEditTextNotEmpty() {

        for(EditText e:editTexts){
            if(TextUtils.isEmpty(e.getText().toString().trim())){
                return false;
            }
        }

        return true;
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
