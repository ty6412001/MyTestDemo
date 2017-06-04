package com.fsd.mvvmlight.freeseeds.entity.event;

import com.fsd.mvvmlight.freeseeds.entity.DomainError;

//dummy class...
public class EventVARFreeRetrieveReturnB extends EventVARFreeReturnB {
	
	public EventVARFreeRetrieveReturnB() {
		super();
	}
	
	public EventVARFreeRetrieveReturnB(Object eventOfObject) throws Exception {
		super(eventOfObject);
	}
	
	//only login used authId but keep a placeholder in return event
	public EventVARFreeRetrieveReturnB(String eventType, String eventLabel, Long eventTime, 
			String authId, String authToken) throws Exception {
		this.self_installFields(eventType, eventLabel, eventTime, authId, authToken);
		this.sub_validateFields();
	}
	
	public EventVARFreeRetrieveReturnB(String eventType, String eventLabel, Long eventTime, DomainError error) {
		super(eventType, eventLabel, eventTime, error);
	}
	
	protected void self_installFields(String eventType, String eventLabel, Long eventTime, 
			String authId, String authToken) throws Exception {
		super.self_installFields(eventType, eventLabel, eventTime, 
				authId, authToken);
	}
	
	protected void self_installFields(String eventType, String eventLabel, Long eventTime, DomainError error) {
		super.self_installFields(eventType, eventLabel, eventTime, error);
	}

	@Override
	protected void sub_validateFields() throws Exception {	

	}
}
