package com.fsd.mvvmlight.freeseeds.entity.event;

import com.fsd.mvvmlight.freeseeds.client.ErrorContext;
import com.fsd.mvvmlight.freeseeds.entity.DomainError;
import com.fsd.mvvmlight.freeseeds.entity.Entity;
import com.fsd.mvvmlight.freeseeds.entity.Topic;
import com.fsd.mvvmlight.freeseeds.exception.ListException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import library.helper.JSONHelper.JSONObjectWrapper;


public class EventVARFreeTopicSearchReturn extends EventVARFreeRetrieveReturnB {
	
	public static final class Field extends EventVARFreeReturnB.Field {	
		public static final String RESULTS = "results";
	}
	
	public List<List<Topic>> results;
	
	public EventVARFreeTopicSearchReturn(Object eventOfObject) throws Exception{		
		super(eventOfObject);
	}
		
	//search return has token???
	public EventVARFreeTopicSearchReturn(String eventLabel, Long eventTime, String authToken, List<List<Topic>> results) throws Exception {
		this.self_installFields(eventLabel, eventTime, authToken, results);
		this.sub_validateFields();
	}
	
	public EventVARFreeTopicSearchReturn(String eventLabel, Long eventTime, DomainError error) {
		this.self_installFields(eventLabel, eventTime, error);
	}
		
	protected void self_installFields(String eventLabel, Long eventTime, String authToken, List<List<Topic>> results) throws Exception {
		super.self_installFields(EventFreeB.EventType.TOPICSEARCHRETURN, eventLabel, eventTime, null, authToken);
		this.results = results;		
	}
	
	protected void self_installFields(String eventLabel, Long eventTime, DomainError error) {
		super.self_installFields(EventFreeB.EventType.TOPICSEARCHRETURN, eventLabel, eventTime, error);
	}
	
	@Override
	protected void sub_decodeFields(JSONObjectWrapper eventJwo) throws Exception {
		super.sub_decodeFields(eventJwo);
		if (eventJwo == null) {
			return;
		} else {
			Object entityListListOfObject = eventJwo.get(Field.RESULTS);
			this.results = Topic.makeTopicListListFromEntityListListOfObject(entityListListOfObject);
		}
	}
	
	@Override
	protected void sub_validateFields() throws Exception {
		super.sub_validateFields();
		
		if (!this.getEventType().equals(EventFreeB.EventType.TOPICSEARCHRETURN)) {//not sync'd can use this in the middle of constructor?
			String exceptionMessage = ListException.createExceptionMessage(ErrorContext.Code.VALIDATE_INVALIDEVENTFIELD_EVENTTYPE, ErrorContext.Description.VALIDATE_INVALIDEVENTFIELD_EVENTTYPE + " for " + EventFreeB.EventType.TOPICSEARCHRETURN);
    		throw new ListException(exceptionMessage);
		}
		//?session does not have this//needed??
		if (this.results == null) {
			this.results = new ArrayList<List<Topic>>();
		}
	}
	
	@Override
	public synchronized JSONObject toJSONObject() {//for json, probably return a copy instead, immutable, sychronize on access method if needed
    	JSONObject eventJoo = super.toJSONObject();
    	try {
    		if (eventJoo == null) {
    			eventJoo = new JSONObject();
    		}
			
    		JSONArray resultsJao = Entity.makeJSONArrayFromEntityListList(this.results);
        	eventJoo.put(Field.RESULTS, resultsJao);
    	} catch (Exception e) {
    		e.printStackTrace();
    		eventJoo = new JSONObject();
    	}
    	return eventJoo;
	}
	
}
