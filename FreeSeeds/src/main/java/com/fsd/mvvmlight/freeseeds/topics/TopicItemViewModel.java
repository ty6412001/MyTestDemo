package com.fsd.mvvmlight.freeseeds.topics;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.fsd.mvvmlight.freeseeds.R;
import com.fsd.mvvmlight.freeseeds.entity.Topic;
import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;


/**
 * Created by zhangyabei on 4/5/17.
 */

public class TopicItemViewModel implements ViewModel {
    //context
    private Context context;

    //model
    public Topic topicBean;

    //field to presenter
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> user = new ObservableField<>();
    public final ObservableField<String> distance = new ObservableField<>();
    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<String> date = new ObservableField<>();
    public TopicItemViewModel.ViewStyle viewStyle = new TopicItemViewModel.ViewStyle();

    //Use class viewStyle to wrap field which is binding to style of view
    public static class ViewStyle {
        public final ObservableInt titleTextColor = new ObservableInt();
    }


    //command
    public ReplyCommand itemClickCommand = new ReplyCommand(() -> {
//        this.viewStyle.titleTextColor.set(context.getResources().getColor(android.R.color.darker_gray));
//        Intent intent = new Intent(context, NewsDetailActivity.class);
//        intent.putExtra(NewsDetailActivity.EXTRA_KEY_NEWS_ID, storiesBean.getId());
//        context.startActivity(intent);
    });

    public TopicItemViewModel(Context context, Topic topicBean) {
        this.context = context;
        this.topicBean = topicBean;
        this.viewStyle.titleTextColor.set(context.getResources().getColor(android.R.color.black));

        title.set(topicBean.title);
        user.set(topicBean.userCreator.name);
            if (!topicBean.mediasHas.isEmpty())
                imageUrl.set( new ArrayList<String>(topicBean.mediasHas.get(0).urls.values()).get(0) );


    }

}
