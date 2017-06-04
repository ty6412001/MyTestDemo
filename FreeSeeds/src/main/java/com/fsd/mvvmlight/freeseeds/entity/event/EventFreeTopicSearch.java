package com.fsd.mvvmlight.freeseeds.entity.event;


import com.fsd.mvvmlight.freeseeds.client.ErrorContext;
import com.fsd.mvvmlight.freeseeds.entity.Query;
import com.fsd.mvvmlight.freeseeds.exception.ListException;

import java.util.List;
import java.util.Map;



public class EventFreeTopicSearch extends EventFreeRetrieveB {

    public EventFreeTopicSearch(Object eventOfObject) throws Exception{
        super(eventOfObject);
    }

    //TOCHECK may not need auth
    public EventFreeTopicSearch(String eventLabel, Long eventTime, String authId, String authToken, Map<String, Object> options, List<Query> queries) throws Exception {
        this.self_installFields(eventLabel, eventTime, authId, authToken, options, queries);
        this.sub_validateFields();
    }

    protected void self_installFields(String eventLabel, Long eventTime, String authId, String authToken, Map<String, Object> options, List<Query> queries) throws Exception {
        super.self_installFields(EventFreeB.EventType.TOPICSEARCH, eventLabel, eventTime, authId, authToken, null, null, null, options, queries);
    }

    @Override
    protected void sub_validateFields() throws Exception {
        super.sub_validateFields();

        if (!this.getEventType().equals(EventFreeB.EventType.TOPICSEARCH)) {//not sync'd can use this in the middle of constructor?
            String message = ListException.createExceptionMessage(null, "invalid eventType for " + EventFreeB.EventType.TOPICSEARCH + " event");
            throw new ListException(message);
        }

        if (this.queries == null) {
            String message = ListException.createExceptionMessage(ErrorContext.Code.VALIDATE_MISSINGEVENTFIELD_QUERIES, ErrorContext.Description.VALIDATE_MISSINGEVENTFIELD_QUERIES);//to do message
            throw new ListException(message);
        }
    }
}
