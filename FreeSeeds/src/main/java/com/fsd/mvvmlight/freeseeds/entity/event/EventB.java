package com.fsd.mvvmlight.freeseeds.entity.event;

import org.json.JSONObject;
import com.fsd.mvvmlight.freeseeds.exception.ListException;
import java.util.Map;

import library.helper.JSONHelper.*;
/**
 * Created by zhangyabei on 5/7/17.
 */

public abstract class EventB implements EventI  {
    public static final class Field {

        //user specify
        public static final String EVENTTYPE = "__eventType__";

        //system generate and system use
        public static final String EVENTID = "__eventId__";
        //user specify
        public static final String EVENTLABEL = "__eventLabel__"; //local id, can be draft

        //system generate and system use
        public static final String EVENTTIME = "__eventTime__";
        public static final String EVENTDATA = "__eventData__";

        //system generate and system use, not used here
        public static final String EVENTSOURCE = "__eventSource__";
        public static final String EVENTDRIVER = "__eventDriver__";
        public static final String EVENTROOT = "__eventRoot__";
        public static final String EVENTPARENT = "__eventParent__";
        public static final String PLAYCONTEXT = "__playContext__";

    }

    public EventB() {

    }

    //TODO catched exception instead of throwing...
    public EventB(Object eventOfObject) throws Exception {
        JSONObject eventJoo = null;
        if (eventOfObject instanceof String) {
            eventJoo = new JSONObject((String) eventOfObject);
        }
        else if (eventOfObject instanceof JSONObject) {
            eventJoo = (JSONObject) eventOfObject;
        }
        else if (eventOfObject instanceof EventI) {
            eventJoo = ((EventI) eventOfObject).toJSONObject();
        }
        else if (eventOfObject instanceof Map) {
            eventJoo = new JSONObject((Map<?, ?>) eventOfObject);//immutable???
        }
        else {
            //raise unsupported exception?
            String message = ListException.createExceptionMessage(null, "unsupported Object for event initialization");
            throw new ListException(message);
        }
        JSONObjectWrapper eventJwo = new JSONObjectWrapper(eventJoo);
        this.sub_decodeFields(eventJwo);
        this.sub_validateFields();//since it does not implement get method, cant validate here
    }

    protected void sub_decodeFields(JSONObjectWrapper eventJwo) throws Exception {

    }

    //when init does not require fields all set
    protected void sub_validateFields() throws Exception {

    }

    @Override
    public void validateEvent() throws Exception {
        if (!this.hasField(Field.EVENTTYPE)) { //input provide
            String message = ListException.createExceptionMessage(null, "missing "+ Field.EVENTTYPE);
            throw new ListException(message);
        }
        else if (!this.hasField(Field.EVENTID)) { //core overwrite
            String message = ListException.createExceptionMessage(null, "missing "+ Field.EVENTID);
            throw new ListException(message);
        }
        else if (!this.hasField(Field.EVENTTIME)) { //core overwrite
            String message = ListException.createExceptionMessage(null, "missing "+ Field.EVENTTIME);
            throw new ListException(message);
        }
        else if (!this.hasField(Field.EVENTSOURCE)) { //core default to unknown
            String message = ListException.createExceptionMessage(null, "missing "+ Field.EVENTSOURCE);
            throw new ListException(message);
        }
    }

    @Override
    public synchronized JSONObject toJSONObject() {//for json, probably return a copy instead, immutable, sychronize on access method if needed
        JSONObject eventJoo = null;
        try {
            eventJoo = new JSONObject();
            eventJoo.put(Field.EVENTID, this.getEventId());
            eventJoo.put(Field.EVENTLABEL, this.getEventLabel());
            eventJoo.put(Field.EVENTTYPE, this.getEventType());
            eventJoo.put(Field.EVENTDRIVER, this.getEventDriver());
            eventJoo.put(Field.EVENTSOURCE, this.getEventSource());
            eventJoo.put(Field.EVENTDATA, this.getEventData());
            eventJoo.put(Field.EVENTTIME, this.getEventTime());
            eventJoo.put(Field.EVENTROOT, this.getEventRoot());
            eventJoo.put(Field.EVENTPARENT, this.getEventParent());
            eventJoo.put(Field.PLAYCONTEXT, this.getPlayContext());
        } catch (Exception e) {
            e.printStackTrace();
            eventJoo = new JSONObject();
        }
        return eventJoo;
    }

    @Override
    public String toString() {
        JSONObject eventJoo = this.toJSONObject();
        if (eventJoo == null) {
            eventJoo = new JSONObject();
        }
        return eventJoo.toString();
    }

}
