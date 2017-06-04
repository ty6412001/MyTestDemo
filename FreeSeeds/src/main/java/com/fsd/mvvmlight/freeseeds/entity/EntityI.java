package com.fsd.mvvmlight.freeseeds.entity;

import org.json.JSONObject;

import java.util.List;

public interface EntityI {
	public JSONObject toJSONObject();
	public EntityI makeCopyOfView(String view); 	
	public void madeToView(String view); //only used at server side 
	public EntityI makeCopyOfFields(List<String> fieldKeys);
}
