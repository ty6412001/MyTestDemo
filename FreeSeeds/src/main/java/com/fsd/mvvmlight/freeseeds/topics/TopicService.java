package com.fsd.mvvmlight.freeseeds.topics;



import java.io.Serializable;
import java.util.List;

import com.fsd.mvvmlight.freeseeds.entity.event.EventFreeTopicSearch;

import com.fsd.mvvmlight.freeseeds.entity.Topic;
import com.fsd.mvvmlight.freeseeds.entity.event.EventVARFreeTopicSearchReturn;

import org.json.JSONObject;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zhangyabei on 4/1/17.
 */

public interface TopicService {

    @POST("listDemo/api/1.0/topic/operation/search")
    public Observable<EventVARFreeTopicSearchReturn> getTopicList(@Body EventFreeTopicSearch eventVarFreeTopicSearch);

    public class Topics {
        private List<List<Topic>> topics;
        public void setTopics (List<List<Topic>> topics) {this.topics = topics;}
        public List<List<Topic>> getTopics() {return this.topics;}
    }

}
