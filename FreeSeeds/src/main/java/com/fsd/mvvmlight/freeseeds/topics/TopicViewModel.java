package com.fsd.mvvmlight.freeseeds.topics;


import android.app.Fragment;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.fsd.mvvmlight.freeseeds.entity.Topic;
import com.fsd.mvvmlight.freeseeds.entity.event.EventFreeTopicSearch;
import com.fsd.mvvmlight.freeseeds.entity.event.EventVARFreeTopicSearchReturn;
import com.fsd.mvvmlight.freeseeds.news.TopNewsService;
import com.kelin.mvvmlight.BR;
import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.fsd.mvvmlight.freeseeds.R;
import com.fsd.mvvmlight.freeseeds.FreeSeedsApp;
import com.fsd.mvvmlight.freeseeds.retrofit.RetrofitProvider;
import com.trello.rxlifecycle.FragmentLifecycleProvider;

import java.util.List;

import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;
import rx.Notification;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by zhangyabei on 4/4/17.
 */

public class TopicViewModel implements ViewModel {
    public static final String TOKEN_TOP_NEWS_FINISH = "token_top_topic_finish" + FreeSeedsApp.sPackageName;

    final static String eventVarFreeTopicSearch = "{\"__eventType__\":\"topicSearch\", " +
            "\"queries\":[{\"targetClass\": \"topic\", \"fieldFilters\":"+
            "[{\"operator\":\"like\",\"value\":\"*\"},{\"operator\":\"like\","+
            "\"value\":\"offer\",\"field\":\"objectives\"}], \"timeFilter\":{\"field\":\"timeCreate\","+
            "\"earliest\":1000, \"latest\":1995994583758}, \"spaceFilter\":{\"type\":\"circle\", \"centerX\":34.073943, \"centerY\":-118.219302, \"radius\":2000000},"+
            "\"sort\":{\"field\":\"timeCreate\", \"direction\":\"descend\"}, \"start\":0, \"rows\":10}]}";
    //context
    private Fragment fragment;

    /**
     * model
     */
    private List<List<Topic>> topicList;
    private TopNewsService.News topNews;

    /*
      data for presenter
     */
    final static int count_per_page = 10;
    private int current = 0;

    // viewModel for RecyclerView
    public final ObservableList<TopicItemViewModel> itemViewModel = new ObservableArrayList<>();
    // view layout for RecyclerView
    public final ItemViewSelector<TopicItemViewModel> itemView = new BaseItemViewSelector<TopicItemViewModel>() {
        @Override
        public void select(ItemView itemView, int position, TopicItemViewModel itemViewModel) {
            itemView.set(BR.viewModel,  R.layout.listitem_news);
        }

        @Override
        public int viewTypeCount() {
            return 2;
        }

    };
    //collection of view style,wrap to a class to manage conveniently!
    public final TopicViewModel.ViewStyle viewStyle = new TopicViewModel.ViewStyle();

    public class ViewStyle {
        public final ObservableBoolean isRefreshing = new ObservableBoolean(true);
        public final ObservableBoolean progressRefreshing = new ObservableBoolean(false);
    }

    /**
     * command
     */

    public final ReplyCommand onRefreshCommand = new ReplyCommand<>(() -> {
//        Observable.just(Calendar.getInstance())
//                .doOnNext(c -> c.add(Calendar.DAY_OF_MONTH, 1))
//                .map(c -> NewsListHelper.DAY_FORMAT.format(c.getTime()))
//                .subscribe(d -> loadTopNews(d));
    });
    /**
     * @param p count of listview items,is unused here!
     * @params,funciton when return true，the callback just can be invoked!
     */
    public final ReplyCommand<Integer> onLoadMoreCommand = new ReplyCommand<>((p) -> {
        loadNewsList();
    });


    public TopicViewModel(Fragment fragment) {
        this.fragment = fragment;

        BehaviorSubject<Notification<TopicService.Topics>> subject = BehaviorSubject.create();
        subject.filter(Notification::isOnNext)
                .subscribe(n -> Toast.makeText(fragment.getActivity(), "load finish!", Toast.LENGTH_SHORT).show());
        loadNewsList();
//        Observable.just(Calendar.getInstance())
//                .doOnNext(c -> c.add(Calendar.DAY_OF_MONTH, 1))
//                .map(c -> NewsListHelper.DAY_FORMAT.format(c.getTime()))
//                .subscribe(d -> loadTopNews(d));
    }


    private void loadNewsList() {
        viewStyle.isRefreshing.set(true);
        EventFreeTopicSearch event;
        try {
            event= new EventFreeTopicSearch( eventVarFreeTopicSearch);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        Observable<Notification<EventVARFreeTopicSearchReturn>> topicListOb =
                RetrofitProvider.getInstance().create(TopicService.class)
                        .getTopicList(event)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(((FragmentLifecycleProvider) fragment).bindToLifecycle())
                        .materialize().share();

        topicListOb.filter(Notification::isOnNext)
                .map(n -> n.getValue())
                .filter(m -> !m.results.isEmpty())
//                .doOnNext(m -> Observable.just(m.getDate()).map(d -> new NewsService.News.StoriesBean.ExtraField(true, d))
//                        .map(d -> new NewsService.News.StoriesBean(d))
//                        .subscribe(d -> itemViewModel.add(new TopicItemViewModel(fragment.getActivity(), d))))
                .doOnNext(m -> topicList = m.results)
                .doAfterTerminate(()-> viewStyle.isRefreshing.set(false))
                .flatMap(m -> Observable.from(m.results.get(0)))
                .subscribe(i -> itemViewModel.add(new TopicItemViewModel(fragment.getActivity(), i)));

//
//        NewsListHelper.dealWithResponseError(topicListOb.filter(Notification::isOnError)
//                .map(n -> n.getThrowable()));


    }

    private void loadTopNews(String date) {
        viewStyle.isRefreshing.set(true);
//
//        Observable<TopNewsService.News> topNewsOb =
//                RetrofitProvider.getInstance().create(TopNewsService.class)
//                        .getTopNewsList()
//                        .compose(((FragmentLifecycleProvider) fragment).bindToLifecycle());
//
//        Observable<NewsService.News> newsListOb =
//                RetrofitProvider.getInstance().create(NewsService.class)
//                        .getNewsList(date)
//                        .compose(((FragmentLifecycleProvider) fragment).bindToLifecycle());
//
//
//        Observable<Notification<Pair<TopNewsService.News, NewsService.News>>> combineRequestOb = Observable.combineLatest(topNewsOb, newsListOb, Pair::new)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .materialize().share();
//
//
//        combineRequestOb.filter(Notification::isOnNext)
//                .map(n -> n.getValue())
//                .map(p -> p.first)
//                .filter(m -> !m.getTop_stories().isEmpty())
//                .doOnNext(m -> Observable.just(NewsListHelper.isTomorrow(date)).filter(b -> b).subscribe(b -> itemViewModel.clear()))
//                .subscribe(m -> Messenger.getDefault().send(m, TOKEN_TOP_NEWS_FINISH));
//
//        combineRequestOb.filter(Notification::isOnNext)
//                .map(n -> n.getValue())
//                .map(p -> p.second).filter(m -> !m.getStories().isEmpty())
//                .doOnNext(m -> news = m)
//                .flatMap(m -> Observable.from(m.getStories()))
//                .subscribe(i -> itemViewModel.add(new NewItemViewModel(fragment.getActivity(), i)));
//
//        combineRequestOb.subscribe((n) -> {
//            viewStyle.isRefreshing.set(false);
//            viewStyle.progressRefreshing.set(false);
//        });
//
//        NewsListHelper.dealWithResponseError(combineRequestOb.filter(Notification::isOnError)
//                .map(n -> n.getThrowable()));

    }

}
