package com.fsd.mvvmlight.freeseeds.entity.event;

import com.fsd.mvvmlight.freeseeds.entity.DomainError;

import org.json.JSONObject;

import library.helper.JSONHelper.JSONObjectWrapper;


public abstract class EventVARFreeReturnB extends Event implements EventVARFreeReturnI {
	
	//to do jsonpath for default instead of flat, overwrite done
	public static class Field {
		public static final String AUTHID = "authId";
		public static final String AUTHTOKEN = "authToken";	
		//TOCHECK do we need this error field
		public static final String ERROR = "error";//now just return a consolidated error rather than a list
				
		public static final String ENTITIES = "entities";//operate return
		public static final String QUERIES = "queries";//operate return
		public static final String RESULTS = "results";//query return
	}
	
	public String authId;//try not return this as possible, but for user login we have to return this or just return entity?//return this for login other just placeholder
	public String authToken;
	public DomainError error;
	
	public EventVARFreeReturnB() {	
		super();
	}
	
	public EventVARFreeReturnB(Object eventOfObject) throws Exception {	
		super(eventOfObject);
	}
	
	public EventVARFreeReturnB(String eventType, String eventLabel, Long eventTime, 
			String authId, String authToken) throws Exception {
		this.self_installFields(eventType, eventLabel, eventTime, 
				authId, authToken);
		this.sub_validateFields();
	}
	
	//should not throw exception
	public EventVARFreeReturnB(String eventType, String eventLabel, Long eventTime, DomainError error) {
		this.self_installFields(eventType, eventLabel, eventTime, error);
	}
	
	protected void self_installFields(String eventType, String eventLabel, Long eventTime, 
			String authId, String authToken) throws Exception {
		super.self_installFields(eventType, null, eventLabel, eventTime, null, null, null, null, null);
		this.authId = authId;
		this.authToken = authToken;
	}
	
	protected void self_installFields(String eventType, String eventLabel, Long eventTime, DomainError error) {
		super.self_installFields(eventType, null, eventLabel, eventTime, null, null, null, null, null);		
		this.error = error;
	}	
	
	@Override
	protected void sub_decodeFields(JSONObjectWrapper eventJwo) throws Exception {
		super.sub_decodeFields(eventJwo);
    	try {
    		if (eventJwo == null) {
				return;
			}
    		
    		this.authId = eventJwo.getString(Field.AUTHID);
    		this.authToken = eventJwo.getString(Field.AUTHTOKEN);//refreshed auth token if not null?

    		if (eventJwo.has(Field.ERROR)) {
				this.error = new DomainError(eventJwo.get(Field.ERROR));
			}	    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	@Override
	protected void sub_validateFields() throws Exception {
		
	}
	
	@Override
	public synchronized JSONObject toJSONObject() {//for json, probably return a copy instead, immutable, sychronize on access method if needed
    	JSONObject eventJoo = super.toJSONObject();
    	try {
    		if (eventJoo == null) {
    			eventJoo = new JSONObject();
    		}
    		
    		eventJoo.put(Field.AUTHID, this.authId);
			eventJoo.put(Field.AUTHTOKEN, this.authToken);
			
			if (error != null) {
				eventJoo.put(Field.ERROR, error.toJSONObject());
			}

    	} catch (Exception e) {
    		e.printStackTrace();
    		eventJoo = new JSONObject();
    	}
    	return eventJoo;
    }
}
