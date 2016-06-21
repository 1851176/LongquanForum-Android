/**
 * Generated by smali2java 1.0.0.558
 * Copyright (C) 2013 Hensence.com
 */

package com.mobcent.discuz.activity;

import android.app.Activity;

import com.appbyme.app178470.R;
import android.os.Handler;

import android.os.Message;
import android.widget.ImageView;
import android.view.Window;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;

public class SplashActivity extends Activity {
    private int MESSAGE_LOAD_PAYSTATE_PAGE;
    private int MESSAGE_START_HOME;
    public String TAG;
    private final int TIMEOUT;
    private final int TIME_LOADING_PAGE;
    private long configId;
    private boolean dataTaskExecuted;
    // private SharedPreferencesDB db;
    private boolean getAllDataByNet;
    private String imgLoadPageUrl;
    // private InitHelper initHelper;
    private Handler mHandler;
    // private MCResource resource;
    // private UriSkipModel skipModel;
    private String skipToWhere;
    private ImageView splashBackgroundImg;
    private boolean timeOutExecuted;

    public SplashActivity() {
        TAG = "SplashActivity";
        TIMEOUT = 0x1388;
        TIME_LOADING_PAGE = 0x7d0;
        dataTaskExecuted = false;
        getAllDataByNet = true;
        MESSAGE_START_HOME = 0x1;
        MESSAGE_LOAD_PAYSTATE_PAGE = 0x2;
        configId = 0x0;
        mHandler = new Handler() {

            public void dispatchMessage(Message msg) {
                /*if(msg.what == MESSAGE_START_HOME) {
                    if(dataTaskExecuted) {
                        initHelper.dispatchActivity(this$0, skipToWhere, skipModel);
                    }
                    timeOutExecuted = true;
                    return;
                }
                if(msg.what == MESSAGE_LOAD_PAYSTATE_PAGE) {
                    if(db.getPayState() != null) {
                        PayStateModel payStateModel = db.getPayState();
                        try {
                            String[] imageUrlArray = payStateModel.getLoadingPageImage().split(",");
                            img = imageUrlArray[0x0];
                        } catch(Exception localException1) {
                        }
                        String imageUrl = payStateModel.getImgUrl() + img;
                        imgLoadPageUrl = MCAsyncTaskLoaderImage.formatUrl(imageUrl, "720x1280");
                        if(TextUtils.isEmpty(img)) {
                            imgLoadPageUrl = "";
                        } else {
                            ImageLoader.getInstance().displayImage(imgLoadPageUrl, splashBackgroundImg);
                        }
                    }
                    MCLogUtil.e(TAG, "imgLoadPageUrl ==== " + imgLoadPageUrl);
                    if(TextUtils.isEmpty(imgLoadPageUrl)) {
                        timeOutExecuted = true;
                        mHandler.removeMessages(MESSAGE_START_HOME);
                        if(dataTaskExecuted) {
                            initHelper.dispatchActivity(this$0, skipToWhere, skipModel);
                        }
                    }
                }*/
            }
        };
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*configId = ConfigOptHelper.getConfigId(this);
        resource = MCResource.getInstance(getApplicationContext());
        db = SharedPreferencesDB.getInstance(getApplicationContext());
        initHelper = InitHelper.getInstance(getApplicationContext());
        Intent intent = getIntent();
        if(intent != null) {
            getAllDataByNet = intent.getBooleanExtra("getAllDataByNet", true);
            skipToWhere = intent.getStringExtra("homeSkipToWhere");
            Uri uri = intent.getData();
            if((uri != null) && (!TextUtils.isEmpty(uri.getScheme()))) {
                String url = uri.toString();
                String[] scheme = url.split("\\?");
                if(scheme.length == 0x2) {
                    String domain = scheme[0x0].substring((scheme[0x0].lastIndexOf("/") + 0x1), scheme[0x0].length());
                    if((!TextUtils.isEmpty(domain)) && (!TextUtils.isEmpty(scheme[0x1]))) {
                        UriSkipModel uriSkipModel = new UriSkipModel();
                        String[] params = scheme[0x1].split("&");
                        for(int i = 0x0; i < params.length; i = i + 0x1) {
                            String[] keyAndValue = params[i].split("=");
                            String key = keyAndValue[0x0];
                            String value = keyAndValue[0x1];
                            if(key.equals("user_id")) {
                                uriSkipModel.userId = Long.parseLong(value);
                            } else if(key.equals("board_id")) {
                                uriSkipModel.boardId = Long.parseLong(value);
                            } else if(key.equals("topic_id")) {
                                uriSkipModel.topicId = Long.parseLong(value);
                            } else if(key.equals("appbyme_url")) {
                                uriSkipModel.url = value;
                            } else if(key.equals("topic_type")) {
                                uriSkipModel.type = Integer.parseInt(value);
                            }
                        }
                        if(domain.equals("usercenter.appbymeclient.com")) {
                            uriSkipModel.skip = UriConstant.ActionSkip.USERCENTER;
                        } else if(domain.equals("detail.appbymeclient.com")) {
                            if(uriSkipModel.type == 0x1) {
                                uriSkipModel.skip = UriConstant.ActionSkip.TOPIC_DETAIL;
                            } else if(uriSkipModel.type == 0x2) {
                                uriSkipModel.skip = UriConstant.ActionSkip.ARTICLE_DETAIL;
                            } else {
                                uriSkipModel = 0x0;
                            } else if(domain.equals("web.appbymeclient.com")) {
                                uriSkipModel.skip = UriConstant.ActionSkip.WEBURL;
                            } else {
                                uriSkipModel = 0x0;
                            }
                        }
                        skipModel = uriSkipModel;
                    }
                } else if(scheme.length > 0x2) {
                    String[] schemeUrl = url.split("\\?", 0x2);
                    domain = schemeUrl[0x0].substring((schemeUrl[0x0].lastIndexOf("/") + 0x1), schemeUrl[0x0].length());
                    if("web.appbymeclient.com".equals(domain)) {
                        UriSkipModel uriSkipModel = new UriSkipModel();
                        uriSkipModel.skip = UriConstant.ActionSkip.WEBURL;
                        String paramUrl = schemeUrl[0x1];
                        uriSkipModel.url = paramUrl.substring((paramUrl.indexOf("=") + 0x1), paramUrl.length());
                        skipModel = uriSkipModel;
                    }
                }
            }
        }*/
        prepareView();
        /*initHelper.init(this, configId, getAllDataByNet, new RequestCalback(this) {

            public void onPreExecute() {
            }

            public void onPostExecute(BaseResultModel<ConfigModel> result) {
                (DiscuzApplication)getApplication().setConfigModel(result);
                if(timeOutExecuted) {
                    initHelper.dispatchActivity(this$0, skipToWhere, skipModel);
                }
                dataTaskExecuted = true;
            }

            public void onPostExecute(SettingModel settingModel) {
                (DiscuzApplication)getApplication().setSettingModel(settingModel);
            }

            public void onPostExecute(PermissionModel permissionModel) {
                (DiscuzApplication)getApplication().setPermissionModel(permissionModel);
            }
        });*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(it);
                finish();
            }
        }, 5000);
    }

    private void prepareView() {
        getWindow().setFlags(0x400, 0x400);
        requestWindowFeature(0x1);
        setContentView(R.layout.splash_activity);
        splashBackgroundImg = (ImageView)findViewById(R.id.mc_forum_splash_background_img);
        splashBackgroundImg.setImageDrawable(getResources().getDrawable(R.drawable.mc_forum_loading_page2));
        mHandler.sendEmptyMessageDelayed(MESSAGE_START_HOME, 0x1388);
        mHandler.sendEmptyMessageDelayed(MESSAGE_LOAD_PAYSTATE_PAGE, 0x7d0);
    }

    public void onBackPressed() {
    }

    protected void onDestroy() {
        if(splashBackgroundImg != null) {
            splashBackgroundImg.setImageBitmap(null);
            splashBackgroundImg.setImageDrawable(null);
        }
        super.onDestroy();
    }
}
