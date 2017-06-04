package com.fsd.mvvmlight.freeseeds.entity.event;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.service.PlayContext;
import library.helper.JSONHelper.*;

/**
 * Created by zhangyabei on 5/11/17.
 */

public class Event extends EventB {

    //TODO no need this type of variable name __, no need set get function ? but it shares an interface
    protected String __eventType__;
    protected String __eventId__;
    protected String __eventLabel__;
    protected Long __eventTime__;
    protected Object __eventData__;
    protected String __eventSource__;
    protected String __eventDriver__;
    protected String __eventRoot__;
    protected String __eventParent__;
    protected String __playContext__;

    //	private String __eventOperation__;
//	private String __eventObjects__;
//    protected Map<String, java.lang.reflect.Field> nameFieldMap;
    //method to setup the map, required for implmement the super set/get methods etc.
    public static List<java.lang.reflect.Field> getAllFields(List<java.lang.reflect.Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    private void loadNameFieldMap() {
//        nameFieldMap = new HashMap<String, java.lang.reflect.Field>();
//        List<java.lang.reflect.Field> fieldList = getAllFields(new ArrayList<java.lang.reflect.Field>(), this.getClass());
//        for (int i=0; i< fieldList.size(); i++) {
//            String name = fieldList.get(i).getName();
//            nameFieldMap.put(name, fieldList.get(i));
//        }
    }

    public Event() {
        super();
        this.loadNameFieldMap();
    }

    public Event(String eventType, String eventId, String eventLabel, Long eventTime, String eventData, String eventSource, String eventDriver,
                    String eventRoot, String eventParent) throws Exception {

        this.self_installFields(eventType, eventId, eventLabel, eventTime, eventData, eventSource, eventDriver, eventRoot, eventParent);

        this.sub_validateFields();
    }

    public Event(Object eventOfObject) throws Exception {
        super(eventOfObject);
    }

    protected void self_installFields(String eventType, String eventId, String eventLabel, Long eventTime, String eventData, String eventSource, String eventDriver,
                                      String eventRoot, String eventParent) {
        this.__eventType__ = eventType;
        this.__eventId__ = eventId;
        this.__eventLabel__ = eventLabel;
        this.__eventTime__ = eventTime;
        this.__eventSource__ = eventSource;
        this.__eventDriver__ = eventDriver;
        this.__eventData__ = eventData;
        this.__eventRoot__ = eventRoot;
        this.__eventParent__ = eventParent;
        this.loadNameFieldMap();//TODO maybe remove reflect...
    }

    @Override
    protected void sub_decodeFields(JSONObjectWrapper eventJwo) throws Exception {
        super.sub_decodeFields(eventJwo);
        try {
            if (eventJwo == null) {
                return;
            } else {
                this.__eventType__ = eventJwo.getString(Field.EVENTTYPE);
                this.__eventId__ = eventJwo.getString(Field.EVENTID);
                this.__eventLabel__ = eventJwo.getString(Field.EVENTLABEL);
                this.__eventTime__ = eventJwo.getLong(Field.EVENTTIME);
                this.__eventSource__ = eventJwo.getString(Field.EVENTSOURCE);
                this.__eventDriver__ = eventJwo.getString(Field.EVENTDRIVER);
                this.__eventData__ = eventJwo.getString(Field.EVENTDATA);
                this.__eventRoot__ = eventJwo.getString(Field.EVENTROOT);
                this.__eventParent__ = eventJwo.getString(Field.EVENTPARENT);
                this.__playContext__ = eventJwo.getString(Field.PLAYCONTEXT);
                this.loadNameFieldMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //cant be validated at EventB since variables inited here
    @Override
    protected void sub_validateFields() throws Exception {
//    	if (!this.hasField(Field.EVENTTYPE)) { //input provide
//    		String message = ListException.createExceptionMessage(null, "missing "+ Field.EVENTTYPE);
//			throw new ListException(message);
//		}
//    	else if (!this.hasField(Field.EVENTID)) { //core overwrite
//			String message = ListException.createExceptionMessage(null, "missing "+ Field.EVENTID);
//			throw new ListException(message);
//		}
//    	else if (!this.hasField(Field.EVENTTIME)) { //core overwrite
//			String message = ListException.createExceptionMessage(null, "missing "+ Field.EVENTTIME);
//			throw new ListException(message);
//		}
//    	else if (!this.hasField(Field.EVENTSOURCE)) { //core default to unknown
//	    	String message = ListException.createExceptionMessage(null, "missing "+ Field.EVENTSOURCE);
//	    	throw new ListException(message);
//	   	}
    }

    @Override
    public void setEventId(String eventId) {
        this.setField(Field.EVENTID, eventId);
    }

    @Override
    public void setEventLabel(String eventLabel) {
        this.setField(Field.EVENTLABEL, eventLabel);
    }

    @Override
    public void setEventTime(long eventTime) {
        this.setField(Field.EVENTTIME, eventTime);
    }

    @Override
    public void setEventType(String eventType) {
        this.setField(Field.EVENTTYPE, eventType);
    }

    @Override
    public void setEventSource(String eventSource) {
        this.setField(Field.EVENTSOURCE, eventSource);
    }

    @Override
    public void setEventData(Object eventData) {
        this.setField(Field.EVENTDATA, eventData);
    }

    @Override
    public void setEventDriver(String eventDriver) {
        this.setField(Field.EVENTDRIVER, eventDriver);
    }

    @Override
    public void setEventRootId(String eventRootId) {
        this.setField(Field.EVENTROOT, eventRootId);
    }

    @Override
    public void setEventParentId(String eventParentId) {
        this.setField(Field.EVENTPARENT, eventParentId);
    }

    @Override
    public void setPlayContext(PlayContext playContext) {
        this.setField(Field.PLAYCONTEXT, playContext);
    }

    @Override
    public synchronized String getEventId() {
        return this.__eventId__;
    }

    @Override
    public synchronized String getEventLabel() {
        return this.__eventLabel__;
    }

    @Override
    public synchronized String getEventType() {
        return this.__eventType__;
    }

    @Override
    public synchronized String getEventSource() {
        return this.__eventSource__;
    }

    @Override
    public synchronized Object getEventData() {
        return this.__eventData__;
    }

    @Override
    public synchronized Long getEventTime() {
        return this.__eventTime__;
    }

    @Override
    public synchronized String getEventDriver() {
        return this.__eventDriver__;
    }

    @Override
    public synchronized String getEventRoot() {//return self id if no root id found
        return this.__eventRoot__;
    }

    @Override
    public String getEventParent() {//return self id if no parent id found
        return this.__eventParent__;
    }

    @Override
    public PlayContext getPlayContext() {
        PlayContext playContext = null;
        try {
            if (this.hasField(Field.PLAYCONTEXT)) {
                Object playContextObject = this.getField(Field.PLAYCONTEXT);
                if (playContextObject instanceof String) {
                    playContext = new PlayContext((String) playContextObject);
                } else if (playContextObject instanceof PlayContext) {
                    playContext = (PlayContext) playContextObject;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playContext;
    }

    @Override
    public synchronized boolean hasField(String field) {
        //handle special names//event though no class here
        if (field.equals("class")) {
            field = "clazz";
        }

//        return this.nameFieldMap.containsKey(field);
        return false;
    }

    @Override
    public synchronized void setField(String field, Object value) {
        try {
            //handle special names
            if (field.equals("class")) {
                field = "clazz";
            }
//            this.nameFieldMap.get(field).set(this, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized Object getField(String field) {
        Object value = null;
        try {
            //handle special names
            if (field.equals("class")) {
                field = "clazz";
            }
//            value = this.nameFieldMap.get(field).get(this);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public synchronized void removeField(String field) {
        try {
            //handle special names
            if (field.equals("class")) {
                field = "clazz";
            }
//            if (this.nameFieldMap.containsKey(field)) {
//                this.nameFieldMap.get(field).set(this, null);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public synchronized Object getFieldAsDoubleOrString(String fieldName) {
//    	Object fieldValue = null;
//		try {
//			//handle special names
//			if (fieldName.equals("class")) {
//				fieldName = "clazz";
//			}
//			String fieldValueString = this.nameFieldMap.get(fieldName).get(this).toString();
//			fieldValue = StringHelper.getDoubleOrStringFromString(fieldValueString);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fieldValue;
//    }
//
//    @Override
//	public synchronized String getFieldAsString(String fieldName) {
//		String fieldValue = null;
//		try {
//			//handle special names
//			if (fieldName.equals("class")) {
//				fieldName = "clazz";
//			}
//			fieldValue = this.nameFieldMap.get(fieldName).get(this).toString();
////			fieldValue = this.getClass().getDeclaredField(fieldName).get(this).toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fieldValue;
//	}
//
//    @Override
//	public synchronized Integer getFieldAsInt(String fieldName) {
//		Integer fieldValue = null;
//		try {
//			//handle special names
//			if (fieldName.equals("class")) {
//				fieldName = "clazz";
//			}
//
//			String fieldValueString = this.nameFieldMap.get(fieldName).get(this).toString();
//			fieldValue = Integer.parseInt(fieldValueString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fieldValue;
//	}
//
//    @Override
//	public synchronized Long getFieldAsLong(String fieldName) {
//		Long fieldValue = null;
//		try {
//			//handle special names
//			if (fieldName.equals("class")) {
//				fieldName = "clazz";
//			}
//
//			String fieldValueString = this.nameFieldMap.get(fieldName).get(this).toString();
//			fieldValue = Long.parseLong(fieldValueString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fieldValue;
//	}
//
//    @Override
//	public synchronized Double getFieldAsDouble(String fieldName) {
//		Double fieldValue = null;
//		try {
//			//handle special names
//			if (fieldName.equals("class")) {
//				fieldName = "clazz";
//			}
//
//			String fieldValueString = this.nameFieldMap.get(fieldName).get(this).toString();
//			fieldValue = Double.parseDouble(fieldValueString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fieldValue;
//	}
//
//    @Override
//	public synchronized JSONObject getFieldAsJSONObject(String fieldName) {
//		JSONObject fieldValue = null;
//		try {
//			//handle special names
//			if (fieldName.equals("class")) {
//				fieldName = "clazz";
//			}
//
//			String fieldValueString = this.nameFieldMap.get(fieldName).get(this).toString();
//			fieldValue = new JSONObject(fieldValueString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fieldValue;
//	}
//
//    @Override
//	public synchronized Map<String, Object> getFieldAsMap(String fieldName) {
//		Map<String, Object> fieldValue = null;
//		try {
//			//handle special names
//			if (fieldName.equals("class")) {
//				fieldName = "clazz";
//			}
//			//TODO not checking nil???
//			fieldValue = new HashMap<String, Object>();
//			String fieldValueString = this.nameFieldMap.get(fieldName).get(this).toString();
//			JSONObject fieldValueJoo = new JSONObject(fieldValueString);
//			Iterator<?> iter = fieldValueJoo.keys();
//			while (iter.hasNext()) {
//				String key = iter.next().toString();
//				Object value = fieldValueJoo.get(key);
//				fieldValue.put(key, value);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fieldValue;
//	}
//
//    @Override
//	public synchronized JSONArray getFieldAsJSONArray(String fieldName) {//very big problem here again, for JSON event, we get the array and change the original, here we cannot... need to take care of the mutability...
//		JSONArray fieldValue = null;
//		try {
//			//handle special names
//			if (fieldName.equals("class")) {
//				fieldName = "clazz";
//			}
//
//			String fieldValueString = this.nameFieldMap.get(fieldName).get(this).toString();
//			fieldValue = new JSONArray(fieldValueString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fieldValue;
//	}
//
//    @Override
//    public EventJSON getFieldAsEventJSON(String fieldName) {
//    	EventJSON fieldValue = null;
//		try {
//			//handle special names
//			if (fieldName.equals("class")) {
//				fieldName = "clazz";
//			}
//
//			String fieldValueString = this.nameFieldMap.get(fieldName).get(this).toString();
//			fieldValue = new EventJSON(fieldValueString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fieldValue;
//    }
//
//    @Override
//    public List<EventI> getFieldAsEventJSONList(String fieldName) {
//    	List<EventI> fieldValue = null;
//		try {
//			//handle special names
//			if (fieldName.equals("class")) {
//				fieldName = "clazz";
//			}
//
//			JSONArray fieldValueRaw = this.getFieldAsJSONArray(fieldName);
//			fieldValue = new ArrayList<EventI>();
//			for (int i=0; i< fieldValueRaw.length(); i++) {
//				EventJSON eventJSON = new EventJSON(fieldValueRaw.getJSONObject(i));
//				fieldValue.add(eventJSON);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fieldValue;
//    }

}