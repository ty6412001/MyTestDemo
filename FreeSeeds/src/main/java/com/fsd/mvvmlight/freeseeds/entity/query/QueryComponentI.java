package com.fsd.mvvmlight.freeseeds.entity.query;


import org.json.JSONObject;

public interface QueryComponentI {
	public JSONObject toJSONObject();
	public QueryComponentI makeCopy();
	
}
