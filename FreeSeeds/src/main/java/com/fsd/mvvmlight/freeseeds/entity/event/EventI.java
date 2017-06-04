package com.fsd.mvvmlight.freeseeds.entity.event;

import org.json.JSONObject;

import core.service.PlayContext;

/**
 * Created by zhangyabei on 5/9/17.
 */

public interface EventI {

    public void setEventId(String eventId);
    public void setEventLabel(String eventLabel);
    public void setEventTime(long eventTime);
    public void setEventType(String eventType);
    public void setEventSource(String eventSource);
    public void setEventData(Object eventData);
    public void setEventDriver(String eventDriver);
    public void setEventRootId(String eventRootId);
    public void setEventParentId(String eventParentId);
    public void setPlayContext(PlayContext playContext);

    public String getEventId();
    public String getEventLabel();
    public Long getEventTime();
    public String getEventType();
    public String getEventSource();
    public Object getEventData();
    public String getEventDriver();
    public String getEventRoot();
    public String getEventParent();
    public PlayContext getPlayContext();

    //	public void setUnderlying(JSONObject underlying);
    public JSONObject toJSONObject();

    public boolean hasField(String field);
    public void setField(String field, Object value);
    public Object getField(String field);
    public void removeField(String field);
//	public Object getFieldAsDoubleOrString(String attribute);
//	public String getFieldAsString(String attribute);
//	public Integer getFieldAsInt(String attribute);
//	public Long getFieldAsLong(String attribute);
//	public Double getFieldAsDouble(String attribute);
//	public JSONObject getFieldAsJSONObject(String attribute);
//	public JSONArray getFieldAsJSONArray(String attribute);
//	public EventJSON getFieldAsEventJSON(String attribute);//get partial eventBean, not valid for epcore, just to use the wrapper
//	public List<EventI> getFieldAsEventJSONList(String attribute);//get partial eventBeans
//	public Map<String, Object> getFieldAsMap(String attribute);

    //this is diff from self_validateFields which are used at init stage; this validate is used when the event construct was completed
    public void validateEvent() throws Exception;

}
