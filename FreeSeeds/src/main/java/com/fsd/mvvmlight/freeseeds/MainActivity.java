package com.fsd.mvvmlight.freeseeds;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.fsd.mvvmlight.freeseeds.mainUI.CustomFragmentTabHost;
import com.kelin.mvvmlight.messenger.Messenger;
import com.fsd.mvvmlight.freeseeds.news.NewsListFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class MainActivity extends RxAppCompatActivity
        implements TabHost.OnTabChangeListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVariable(com.fsd.mvvmlight.freeseeds.BR.viewModel, new MainViewModel(this));
        initViews();

//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//
//        ((AppBarLayout) findViewById(R.id.appBarLayout)).addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                int height = appBarLayout.getHeight() - getSupportActionBar().getHeight() - ViewUtils.getStatusBarHeight(MainActivity.this);
//                int alpha = 255 * (0 - verticalOffset) / height;
//                collapsingToolbarLayout.setExpandedTitleColor(Color.argb(0, 255, 255, 255));
//                collapsingToolbarLayout.setCollapsedTitleTextColor(Color.argb(alpha, 255, 255, 255));
//            }
//        });

//        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//
//        // Indicator must setViewPager after setAdapter,but data for ViewPager is load in other ViewModel
//        Messenger.getDefault().register(this, MainViewModel.TOKEN_UPDATE_INDICATOR, () ->
//                circlePageIndicator.setViewPager(viewPager));
//
//        NewsListFragment fragment = new NewsListFragment();
//        getFragmentManager().beginTransaction()
//                .replace(R.id.content, fragment)
//                .commit();
    }

    private long exitTime = 0;
    private CustomFragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;

    private Class[] fragmentArray = {NewsListFragment.class,
            NewsListFragment.class, NewsListFragment.class, NewsListFragment.class,};

    private int[] mImageViewArray = {
            R.drawable.tab_discover_btn,
            R.drawable.tab_watch_btn,
            R.drawable.tab_follow_btn,
            R.drawable.tab_me_btn};

    private int[] mTextColorArray = {R.drawable.tab_watch_text_color,
            R.drawable.tab_follow_text_color,
            R.drawable.tab_discover_text_color,
            R.drawable.tab_me_text_color};

    private String[] mTextViewArray = {"Discover", "Watch", "Follow", "Me"};

//    @Override
    protected void initViews() {

        layoutInflater = LayoutInflater.from(this);
        mTabHost = (CustomFragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getFragmentManager(), R.id.fl_content);

        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextViewArray[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
        }
        mTabHost.setOnTabChangedListener(this);
    }

    private View getTabItemView(int index) {
        View tabView = layoutInflater.inflate(R.layout.tabhost_item_view, null);

        ImageView ivTabIcon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
        ivTabIcon.setImageResource(mImageViewArray[index]);
        TextView tvTabText = (TextView) tabView.findViewById(R.id.tv_tab_text);
        tvTabText.setText(mTextViewArray[index]);
        tvTabText.setTextColor(getResources().getColorStateList(mTextColorArray[index]));

        return tabView;
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }
        supportInvalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
//            App.toast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_gallery) {
//            NewsListFragment fragment = new NewsListFragment();
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.content, fragment)
//                    .commit();
//
//        }
//
//        return true;
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Messenger.getDefault().unregister(this);
    }
}
