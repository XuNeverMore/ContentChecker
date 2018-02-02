# ContentChecker

```
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
                            .addCondition(new PhoneNumCondition()))
                    .putChecker(ContentChecker.getChecker(passwordBody)//密码检查
                            .addCondition(new LengthCondition(6)))
                    .checkAll();
            if(result){
                register(phoneNum,password);
            }

        });
```


:blush:
 
[详细说明](https://www.jianshu.com/p/d38bad4c4f16) 
