/**
  * Generated by smali2java 1.0.0.558
  * Copyright (C) 2013 Hensence.com
  */

package com.mobcent.discuz.base.fragment;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import java.util.List;
import android.support.v4.app.Fragment;
import com.mobcent.discuz.android.model.ConfigComponentModel;
import com.mobcent.discuz.base.dispatch.FragmentDispatchHelper;
import android.os.Bundle;
import com.mobcent.discuz.android.model.ConfigModuleModel;
import com.mobcent.discuz.module.topic.list.fragment.BaseTopicListFragment;
import android.support.v4.view.ViewPager;
import com.mobcent.discuz.base.delegate.SubTitleChangeListener;
import com.mobcent.lowest.android.ui.widget.MCTabBarScrollView;
import java.util.ArrayList;
import com.mobcent.lowest.base.utils.MCListUtils;
import java.util.Iterator;
import com.mobcent.discuz.base.delegate.SubChangeListener;
import android.app.Activity;
import com.mobcent.lowest.base.utils.MCResource;
import android.graphics.drawable.Drawable;
import android.content.Context;
import com.mobcent.lowest.base.utils.MCPhoneUtil;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.view.View;
import java.util.Collection;
import android.support.v4.view.PagerAdapter;
import com.mobcent.discuz.base.delegate.SlideDelegate;
import android.content.Intent;

public class SubPageFragment extends BaseModuleFragment {
    public String TAG;
    private List<ConfigComponentModel> childModelList<ConfigComponentModel>;
    private int currentPosition;
    private int maxTab;
    private boolean noSubTab;
    private ViewPager pager;
    private SubPageFragment.SubPageFragmentAdapter pagerAdapter;
    private List<String> subList<String>;
    private SubTitleChangeListener subTitleListener;
    private MCTabBarScrollView subWidget;
    
    public SubPageFragment() {
        TAG = "SubPageFragment";
        currentPosition = 0x0;
        subList = new ArrayList();
        maxTab = 0x4;
        noSubTab = false;
        subTitleListener = new SubTitleChangeListener(this) {
            
            public void onSelected(int position) {
                pager.setCurrentItem(position, true);
            }
        };
    }
    
    protected String getRootLayoutName() {
        return "base_subnav_fragment";
    }
    
    protected void initDatas(Bundle savedInstanceState) {
        super.initDatas(savedInstanceState);
        if(childModelList == null) {
            childModelList = new ArrayList();
        }
        if(moduleModel != null) {
            List<ConfigComponentModel> componentList = moduleModel.getComponentList();
            if((childModelList.isEmpty()) && (!MCListUtils.isEmpty(componentList))) {
                childModelList.addAll(componentList);
            }
            if((subList.isEmpty()) && (!childModelList.isEmpty())) {
                subList.clear();
                for(int i = 0x0, len = childModelList.size(); i < len; i = i + 0x1) {
                    subList.add((ConfigComponentModel)childModelList.get(i).getTitle());
                }
            }
        }
    }
    
    protected void initViews(View rootView) {
        super.initViews(rootView);
        subWidget = (MCTabBarScrollView)findViewByName(rootView, "subnav_widget");
        pager = (ViewPager)findViewByName(rootView, "pager_layout");
        pager.setOffscreenPageLimit(0x3);
        setTabs();
        if(pagerAdapter == null) {
            pagerAdapter = new SubPageFragment.SubPageFragmentAdapter(this, getChildFragmentManager());
        }
        pager.setAdapter(pagerAdapter);
        if("subnavTopbar".equals(moduleModel.getStyle())) {
            subWidget.setVisibility(0x8);
            noSubTab = true;
        }
        if((!MCListUtils.isEmpty(subList)) && (!noSubTab)) {
            subWidget.selectCurrentTabNoAnimation(currentPosition);
        }
    }
    
    public void onResume() {
        super.onResume();
        dealChildFragmentState(true);
    }
    
    public void onPause() {
        super.onPause();
        dealChildFragmentState(false);
    }
    
    private void dealChildFragmentState(boolean isResume) {
        if((pagerAdapter != null) && (!MCListUtils.isEmpty(getChildFragmentManager().getFragments()))) {
            for(Fragment f : getChildFragmentManager().getFragments()) {
                if((f != null) && (f.isVisible())) {
                    if(isResume) {
                        f.onResume();
                    }
                    f.onPause();
                }
            }
        }
    }
    
    private void selectedFragment(int currentPosition) {
        if((pagerAdapter != null) && (!MCListUtils.isEmpty(getChildFragmentManager().getFragments()))) {
            for(Fragment f : getChildFragmentManager().getFragments()) {
                if((f != null) && (f.isVisible()) && (f instanceof SubChangeListener)) {
                    int position = f.getArguments().getInt("position");
                    (SubChangeListener)f.onSelected((position == currentPosition));
                }
            }
        }
    }
    
    protected void initActions(View rootView) {
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(this) {
            
            public void onPageSelected(int position) {
                currentPosition = position;
                if(!noSubTab) {
                    subWidget.selectCurrentTab(currentPosition);
                } else {
                    getTopBarHelper().getTopBox().selectTopSub(currentPosition);
                }
            }
            
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    
    private void setTabs() {
        if((activity == null) || (subList == null) || (noSubTab)) {
            return;
        }
        if(isCard) {
            subWidget.setTabBoxView(resource.getDrawable("mc_forum_tab_style2_bg"), dip2px(0x22), MCPhoneUtil.getDisplayWidth(activity.getApplicationContext()));
            subWidget.setArrowView(resource.getDrawable("mc_forum_tab_style2_arrow1"), dip2px(0xa), dip2px(0x10));
            subWidget.setContainArrow(false);
            subWidget.setArrowMarginTop(0x3);
            RelativeLayout.LayoutParams lps = (RelativeLayout.LayoutParams)pager.getLayoutParams();
            lps.setMargins(lps.leftMargin, -dip2px(0x7), lps.rightMargin, lps.bottomMargin);
            pager.setLayoutParams(lps);
        } else {
            subWidget.setTabBoxView(resource.getDrawable("mc_forum_tab_style1_bg"), dip2px(0x22), MCPhoneUtil.getDisplayWidth(activity.getApplicationContext()));
            subWidget.setArrowView(resource.getDrawable("mc_forum_tab_style1_glide"), dip2px(0x3), 0x0);
            subWidget.setContainArrow(true);
            subWidget.setArrowWidthRatio(0.9f);
        }
        int tabSize = subList.size() > maxTab ? maxTab : subList.size();
        subWidget.init(activity, subList, tabSize, new MCTabBarScrollView.ClickSubNavListener(this) {
            
            public void onClickSubNav(View v, int position, TextView view) {
                currentPosition = position;
                pager.setCurrentItem(position, true);
            }
            
            public void initTextView(TextView view) {
                if((view != null) && (view.getTag() != null)) {
                    int position = (Integer)view.getTag().intValue();
                    if(position == currentPosition) {
                        view.setTextColor(resource.getColor("mc_forum_tabbar_press_color"));
                        view.setTextSize(0x0, getResources().getDimension(resource.getDimenId("mc_forum_text_size_15")));
                        return;
                    }
                    view.setTextColor(resource.getColor("mc_forum_tabbar_normal_color"));
                    view.setTextSize(0x0, getResources().getDimension(resource.getDimenId("mc_forum_text_size_14")));
                }
            }
        });
    }
    
    public boolean isSlideFullScreen() {
        if(pager == null) {
            return true;
        }
        if(pager.getCurrentItem() == 0) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if(!MCListUtils.isEmpty(fragments)) {
                Fragment fragment = 0x0;
                try {
                    fragment = (Fragment)fragments.get(0x0);
                } catch(Exception localException1) {
                }
                if((fragment != null) && (fragment instanceof SlideDelegate)) {
                    return (SlideDelegate)fragment.isSlideFullScreen();
                }
            }
            return true;
            return true;
        }
        return false;
    }
    
    protected SubTitleChangeListener getSubTitleChangeListener() {
        return subTitleListener;
    }
    
    protected int getSubCurrentPosition() {
        return currentPosition;
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((getChildFragmentManager() != null) && (!MCListUtils.isEmpty(getChildFragmentManager().getFragments()))) {
            for(Fragment f : getChildFragmentManager().getFragments()) {
                f.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}