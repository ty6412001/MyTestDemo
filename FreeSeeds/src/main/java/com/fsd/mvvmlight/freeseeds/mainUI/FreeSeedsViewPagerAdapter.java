package com.fsd.mvvmlight.freeseeds.mainUI;

import android.content.Context;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.View;

import com.fsd.mvvmlight.freeseeds.topics.DiscoveryFragment;

import java.util.List;

/**
 * Created by zhangyabei on 26/05/2017.
 */

public class FreeSeedsViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragments;

    public FreeSeedsViewPagerAdapter(Context context, FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mContext = context;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    public View getTabView(int position) {
        MainTabView tabView = new MainTabView(mContext);
        Fragment fragment = getItem(position);
        if (fragment instanceof DiscoveryFragment) {
           // tabView.setData(((DiscoveryFragment) fragment).getLaber());
        }
        return tabView;
    }
}
