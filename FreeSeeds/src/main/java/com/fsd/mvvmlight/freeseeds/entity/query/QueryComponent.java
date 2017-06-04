package com.fsd.mvvmlight.freeseeds.entity.query;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import library.helper.JSONHelper.JSONObjectWrapper;


public class QueryComponent implements QueryComponentI {
	
	public static class Field {
		public static final String CLASS = "class";
	}
	
	public String clazz;
	
	public QueryComponent() {
		
	}
	
	public QueryComponent(String clazz) {
		this.self_installFields(clazz);
		this.sub_validateFields();
	}
	
	public QueryComponent(Object componentOfObject) {
		try {
			JSONObject componentJoo = null;
			if (componentOfObject instanceof String) {
				componentJoo = new JSONObject((String) componentOfObject); 
			} 
			else if (componentOfObject instanceof JSONObject) {
				componentJoo = (JSONObject) componentOfObject;
			} 
			else if (componentOfObject instanceof QueryComponentI) {
				componentJoo = ((QueryComponentI) componentOfObject).toJSONObject();
			} 
			else if (componentOfObject instanceof Map) {
				componentJoo = new JSONObject((Map<?, ?>) componentOfObject);//immutable???
			}
			else {
				//raise unsupported exception?
			}
			JSONObjectWrapper componentJwo = new JSONObjectWrapper(componentJoo);
			this.sub_decodeFields(componentJwo);
			this.sub_validateFields();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void self_installFields(String clazz) {
		this.clazz = clazz;
	}
	
	protected void sub_decodeFields(JSONObjectWrapper componentJwo) {
		try {		
			if (componentJwo == null) {
				return;
			} else {
				this.clazz = componentJwo.getString(Field.CLASS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void sub_validateFields() {
		
	}
	
	@Override
	public JSONObject toJSONObject() {//for json, probably return a copy instead, immutable, sychronize on access method if needed
		JSONObject componentJoo = null;
    	try {
    		componentJoo = new JSONObject();
    		componentJoo.put(Field.CLASS, this.clazz);
    	} catch (Exception e) {
    		e.printStackTrace();
    		componentJoo = new JSONObject();
    	}
    	return componentJoo;
    }

	@Override
	public String toString() {
		JSONObject filterJoo = this.toJSONObject();
		if (filterJoo == null) {
			return (new JSONObject()).toString();
		} else {
			return filterJoo.toString();
		}
	}
	
	@Override 
	public QueryComponent makeCopy() {
		return new QueryComponent(this.clazz);
	}

	public static JSONArray makeJSONArrayFromComponentList(List<? extends QueryComponentI> componentList) {
		
		JSONArray componentJao = null;
		
		if (componentList == null) {
			return componentJao;
		}
		
		componentJao = new JSONArray();
		for (QueryComponentI component : componentList) {
			componentJao.put(component.toJSONObject());
		}		
		return componentJao;
	}
}
