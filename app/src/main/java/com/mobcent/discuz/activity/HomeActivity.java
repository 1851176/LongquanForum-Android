/**
 * Generated by smali2java 1.0.0.558
 * Copyright (C) 2013 Hensence.com
 */

package com.mobcent.discuz.activity;

import com.appbyme.app178470.R;
import com.mobcent.discuz.base.constant.BaseIntentConstant;
import com.mobcent.discuz.fragments.DiscoveryFragment;
import com.mobcent.discuz.fragments.DiscuzFragment;
import com.mobcent.discuz.fragments.HomeFragment;
import com.mobcent.discuz.fragments.MeFragment;
import com.mobcent.discuz.ui.TabView;
import com.mobcent.lowest.android.ui.module.plaza.constant.PlazaConstant;
import com.mobcent.discuz.android.constant.ConfigConstant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class HomeActivity extends FragmentActivity implements BaseIntentConstant, PlazaConstant, ConfigConstant, TabView.OnTabChangeListener {
    private String TAG;
    private Fragment[] fragment = new Fragment[4];

    public HomeActivity() {

    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.home_page_activity);
        TabView tv = (TabView)findViewById(R.id.view_tab);
        tv.setOnTabChangeListener(this);
        LoginUtils.getInstance().init(this);
        fragment[0] = new HomeFragment();
        fragment[1] = new DiscuzFragment();
        fragment[2] = new DiscoveryFragment();
        fragment[3] = new MeFragment();
        onTabChange(0);
    }

    @Override
    public void onTabChange(int position) {
        if (position == 2 && !LoginUtils.getInstance().isLogin()) {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment[position]).commit();
    }
}
