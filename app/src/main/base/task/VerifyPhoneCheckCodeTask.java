/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package com.mobcent.discuz.base.task;

import com.mobcent.discuz.android.service.VerifyService;
import android.content.Context;
import com.mobcent.discuz.android.model.BaseResult;
import com.mobcent.discuz.android.service.impl.VerifyServiceImpl;

public class VerifyPhoneCheckCodeTask extends BaseTask {
    private String code;
    private String phoneNum;
    private VerifyService verifyService;
    
    public VerifyPhoneCheckCodeTask(Context context, BaseRequestCallback<BaseResult> _callback, String phoneNum, String code) {
        super(context, _callback);
        verifyService = new VerifyServiceImpl(context);
        phoneNum = phoneNum;
        code = code;
    }
    
    protected BaseResult doInBackground(Void[] params) {
        return verifyService.checkCode(phoneNum, code);
    }
}