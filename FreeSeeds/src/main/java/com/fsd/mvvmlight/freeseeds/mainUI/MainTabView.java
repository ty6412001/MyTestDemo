package com.fsd.mvvmlight.freeseeds.mainUI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fsd.mvvmlight.freeseeds.R;

/**
 * Created by zhangyabei on 27/05/2017.
 */

public class MainTabView extends FrameLayout {
  //  @BindView(R.id.tab_laber_tv)
    TextView mLaberTV;

    public MainTabView(Context context) {
        super(context);
        init(context);
    }

    public MainTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.view_main_tab, this);
     //   ButterKnife.bind(this, this);
    }

    public void setData(String laber) {
        mLaberTV.setText(laber);
    }
}
