/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package com.mobcent.discuz.base.task;

import com.mobcent.discuz.android.service.PostsService;
import android.content.Context;
import com.mobcent.discuz.android.model.BaseResultModel;

public class SupportAsyncTask extends BaseTask {
    private long pid;
    private PostsService postsService;
    private long tid;
    private String type;
    
    public SupportAsyncTask(Context context, long tid, long pid, String type, BaseRequestCallback<BaseResultModel<Object>> p5) {
        // :( Parsing error. Please contact me.
    }
    
    protected BaseResultModel<Object> doInBackground(Void params) {
        return postsService.support(tid, this, type);
    }
}