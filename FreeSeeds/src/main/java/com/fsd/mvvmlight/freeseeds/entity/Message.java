package com.fsd.mvvmlight.freeseeds.entity;

import com.fsd.mvvmlight.freeseeds.exception.ListException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.helper.JSONHelper;
import library.helper.JSONHelper.JSONObjectWrapper;


public class Message extends Entity {

    public static final class Field extends Entity.Field {

        public static final String LIFECYCLE = "lifecycle";

        public static final String TYPE = "type";
        public static final String CONTENT = "content";

        public static final String TIMECREATE = "timeCreate";
        public static final String TIMEUPDATE = "timeUpdate";
        public static final String TIMEEXPIRE = "timeExpire";

        public static final String USERCREATOR = Role.USERCREATOR;
        public static final String USERSRECEIVER = Role.USERSRECEIVER;

        public static final String MEDIASHAS = "mediasHas";
        public static final String SESSIONOF = "sessionOf";
    }

    public static final class Class {
        public static final String value = "message";
    }

    public static final class Type {
        public static final String TEXT = "text";
        public static final String IMAGE = "image";
        public static final String VOICE = "voice";
        public static final String VIDEO = "video";
    }

    public static final class Role {
        public static final String USERCREATOR = "userCreator";
        public static final String USERSRECEIVER = "usersReceiver";

        public static final String USERSUNKNOWN = "usersUnknown";
    }

    public class Lifecycle {
        public static final String ACTIVE = "active";
        public static final String INACTIVE = "inactive";
    }

    public String lifecycle;

    public String type;
    public String content;

    public Long timeCreate;
    public Long timeUpdate;
    public Long timeExpire;

    public User userCreator;
    public List<User> usersReceiver;

    public List<Media> mediasHas;
    public Session sessionOf;

    public Message() {
        super();
    }

    public Message(String id, Boolean dummySignatureForInitById) {
        super(id, dummySignatureForInitById);
    }

    public Message(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                   String lifecycle,
                   String type, String content,
                   Long timeCreate, Long timeUpdate, Long timeExpire,
                   User userCreator, List<User> usersReceiver,
                   List<Media> mediasHas, Session sessionOf) throws Exception {

        this.self_installFields(clazz, id, label, state, control, note,
                lifecycle,
                type, content,
                timeCreate, timeUpdate, timeExpire,
                userCreator, usersReceiver,
                mediasHas, sessionOf);

        this.sub_validateFields();
    }

    public Message(Object entityOfObject) throws Exception {
        super(entityOfObject);
    }

    protected void self_installFields(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                                      String lifecycle,
                                      String type, String content,
                                      Long timeCreate, Long timeUpdate, Long timeExpire,
                                      User userCreator, List<User> usersReceiver,
                                      List<Media> mediasHas, Session sessionOf) {

        super.self_installFields(clazz, id, label, state, control, note);

        //local
        this.lifecycle = lifecycle;
        this.type = type;
        this.content = content;
        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;
        this.timeExpire = timeExpire;
        this.userCreator = userCreator;
        this.usersReceiver = usersReceiver;
        this.mediasHas = mediasHas;
        this.sessionOf = sessionOf;
    }

    @Override
    protected void sub_decodeFields(JSONObjectWrapper entityJwo) throws Exception {
        super.sub_decodeFields(entityJwo);
        try {
            if (entityJwo == null) {
                return;
            } else {
                this.lifecycle = entityJwo.getString(Field.LIFECYCLE);
                this.type = entityJwo.getString(Field.TYPE);
                this.content = entityJwo.getString(Field.CONTENT);
                this.timeCreate = entityJwo.getLong(Field.TIMECREATE);
                this.timeUpdate = entityJwo.getLong(Field.TIMEUPDATE);
                this.timeExpire = entityJwo.getLong(Field.TIMEEXPIRE);

                if (entityJwo.has(Field.USERCREATOR)) {
                    this.userCreator = new User(entityJwo.get(Field.USERCREATOR));
                }

                this.usersReceiver = User.makeUserListFromEntityListOfObject(entityJwo.get(Field.USERSRECEIVER));

                this.mediasHas = Media.makeMediaListFromEntityListOfObject(entityJwo.get(Field.MEDIASHAS));

                if (entityJwo.has(Field.SESSIONOF)) {
                    this.sessionOf = new Session(entityJwo.get(Field.SESSIONOF));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void sub_validateFields() throws Exception {
        //set default if not valid etc.
        if (this.state != null && !Message.getStateValues().contains(this.state.toLowerCase())) {
            this.state = null;
        }
    }

    @Override
    public JSONObject toJSONObject() {

        JSONObject entityJoo = super.toJSONObject();

        try {
            if (entityJoo == null) {
                entityJoo = new JSONObject();
            }
            entityJoo.put(Field.LIFECYCLE, this.lifecycle);
            entityJoo.put(Field.TYPE, type);
            entityJoo.put(Field.CONTENT, content);
            entityJoo.put(Field.TIMECREATE, timeCreate);
            entityJoo.put(Field.TIMEUPDATE, timeUpdate);
            entityJoo.put(Field.TIMEEXPIRE, timeExpire);

            if (this.userCreator!=null) {
                entityJoo.put(Field.USERCREATOR, this.userCreator.toJSONObject());
            }

            entityJoo.put(Field.USERSRECEIVER, User.makeJSONArrayFromEntityList(this.usersReceiver));

            entityJoo.put(Field.MEDIASHAS, Media.makeJSONArrayFromEntityList(this.mediasHas));

            if (this.sessionOf!=null) {
                entityJoo.put(Field.SESSIONOF, this.sessionOf.toJSONObject());
            }

        } catch (Exception e) {
            e.printStackTrace();
            entityJoo = new JSONObject();
        }
        return entityJoo;
    }

    @Override
    public boolean equals(Object object) {
        boolean rt = false;
        if (object instanceof Message) {
            Message message = (Message) object;
            if (this.id != null) {
                rt = this.id.equals(message.id);
            } else if (message.id == null) {
                if (((this.sessionOf == null && message.sessionOf == null) || this.sessionOf.equals(message.sessionOf)) &&
                        ((this.timeCreate == null && message.timeCreate == null) || this.timeCreate.equals(message.timeCreate)) &&
                        ((this.userCreator == null && message.userCreator == null) || this.userCreator.equals(message.userCreator))) {
                    rt = true;
                }
            }
        }
        return rt;
    }

    public List<String> getRolesForUser(User user) {
        List<String> roles = new ArrayList<String>();
        if (this.userCreator!= null) {
            if (this.userCreator.equals(user)) {
                String role = Role.USERCREATOR;
                roles.add(role);
            }
        }
        if (this.usersReceiver!= null) {
            if (this.usersReceiver.contains(user) || this.usersReceiver.contains(User.makePredefinedUserAnyUser())) {
                String role = Role.USERSRECEIVER;
                roles.add(role);
            }
        }

        if (roles.isEmpty()) {
            roles.add(Role.USERSUNKNOWN);
        }
        return roles;
    }

    //client use, just in case, keep null consistent
    public static String getIdSuggested(String sessionOfId, String userCreatorOfId, Long timeCreate) {
        String sessionOfIdString = sessionOfId == null ? "nil" : sessionOfId;
        String userCreatorIdString = userCreatorOfId == null ? "nil" : userCreatorOfId;
        String timeCreateString = timeCreate == null ? "0" : timeCreate.toString();
        return sessionOfIdString + "_" + userCreatorIdString + "_" + timeCreateString;
    }

    public static List<String> getFieldKeys() {
        List<String> fieldKeys = new ArrayList<String>();

        fieldKeys.add(Message.Field.CLASS);
        fieldKeys.add(Message.Field.ID);
        fieldKeys.add(Message.Field.LABEL);
        fieldKeys.add(Message.Field.STATE);
        fieldKeys.add(Message.Field.CONTROL);
        fieldKeys.add(Message.Field.NOTE);//note should always be excluded if save?

        fieldKeys.add(Message.Field.LIFECYCLE);

        fieldKeys.add(Message.Field.TYPE);
        fieldKeys.add(Message.Field.CONTENT);

        fieldKeys.add(Message.Field.TIMECREATE);
        fieldKeys.add(Message.Field.TIMEUPDATE);
        fieldKeys.add(Message.Field.TIMEEXPIRE);

        fieldKeys.add(Message.Field.USERCREATOR);
        fieldKeys.add(Message.Field.USERSRECEIVER);

        fieldKeys.add(Message.Field.MEDIASHAS);
        fieldKeys.add(Message.Field.SESSIONOF);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForView(String view) {
        if (Message.View.ID.equals(view)) {
            return Message.getFieldKeysForViewId();
        }
        if (Message.View.ABSTRACT.equals(view)) {
            return Message.getFieldKeysForViewAbstract();
        }
        if (Message.View.INFO.equals(view)) {
            return Message.getFieldKeysForViewInfo();
        }
        if (Message.View.ORIGINAL.equals(view)) {
            return Message.getFieldKeysForViewOriginal();
        }
        return new ArrayList<String>();
    }

    public static List<String> getFieldKeysForViewId() {
        List<String> fieldKeys = new ArrayList<String>();

        fieldKeys.add(Message.Field.ID);
        fieldKeys.add(Message.Field.TIMECREATE);
        fieldKeys.add(Message.Field.USERCREATOR);
        fieldKeys.add(Message.Field.SESSIONOF);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewAbstract() {
        List<String> fieldKeys = new ArrayList<String>();

        //        fieldKeys.add(Message.Field.CLASS);
        fieldKeys.add(Message.Field.ID);
        //        fieldKeys.add(Message.Field.LABEL);
        //        fieldKeys.add(Message.Field.STATE);
        fieldKeys.add(Message.Field.CONTROL);
        //        fieldKeys.add(Message.Field.NOTE);

        fieldKeys.add(Message.Field.LIFECYCLE);

        fieldKeys.add(Message.Field.TYPE);
        fieldKeys.add(Message.Field.CONTENT);

        fieldKeys.add(Message.Field.TIMECREATE);
        fieldKeys.add(Message.Field.TIMEUPDATE);
        fieldKeys.add(Message.Field.TIMEEXPIRE);

        fieldKeys.add(Message.Field.USERCREATOR);
        fieldKeys.add(Message.Field.USERSRECEIVER);

        fieldKeys.add(Message.Field.MEDIASHAS);
        fieldKeys.add(Message.Field.SESSIONOF);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewInfo() {
        List<String> fieldKeys = new ArrayList<String>();

        //        fieldKeys.add(Message.Field.CLASS);
        fieldKeys.add(Message.Field.ID);
        //        fieldKeys.add(Message.Field.LABEL);
        //        fieldKeys.add(Message.Field.STATE);
        fieldKeys.add(Message.Field.CONTROL);
        //        fieldKeys.add(Message.Field.NOTE);

        fieldKeys.add(Message.Field.LIFECYCLE);

        fieldKeys.add(Message.Field.TYPE);
        fieldKeys.add(Message.Field.CONTENT);

        fieldKeys.add(Message.Field.TIMECREATE);
        fieldKeys.add(Message.Field.TIMEUPDATE);
        fieldKeys.add(Message.Field.TIMEEXPIRE);

        fieldKeys.add(Message.Field.USERCREATOR);
        fieldKeys.add(Message.Field.USERSRECEIVER);

        fieldKeys.add(Message.Field.MEDIASHAS);
        fieldKeys.add(Message.Field.SESSIONOF);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewOriginal() {
        List<String> fieldKeys = new ArrayList<String>();

        fieldKeys.add(Message.Field.CLASS);
        fieldKeys.add(Message.Field.ID);
        fieldKeys.add(Message.Field.LABEL);
        fieldKeys.add(Message.Field.STATE);
        fieldKeys.add(Message.Field.CONTROL);
        fieldKeys.add(Message.Field.NOTE);//note should always be excluded if save?

        fieldKeys.add(Message.Field.LIFECYCLE);

        fieldKeys.add(Message.Field.TYPE);
        fieldKeys.add(Message.Field.CONTENT);

        fieldKeys.add(Message.Field.TIMECREATE);
        fieldKeys.add(Message.Field.TIMEUPDATE);
        fieldKeys.add(Message.Field.TIMEEXPIRE);

        fieldKeys.add(Message.Field.USERCREATOR);
        fieldKeys.add(Message.Field.USERSRECEIVER);

        fieldKeys.add(Message.Field.MEDIASHAS);
        fieldKeys.add(Message.Field.SESSIONOF);

        return fieldKeys;
    }

    @Override
    public Message makeCopyOfFields(List<String> fieldKeys) {

        List<String> fieldKeysForEntityCopy = Message.getFieldKeys();
        if (fieldKeys != null) {
            fieldKeysForEntityCopy = fieldKeys;
        }

        Message entityCopy = new Message();

        if (fieldKeysForEntityCopy.contains(Message.Field.CLASS)) {
            entityCopy.clazz = this.clazz;
        }
        if (fieldKeysForEntityCopy.contains(Message.Field.ID)) {
            entityCopy.id = this.id;
        }
        if (fieldKeysForEntityCopy.contains(Message.Field.LABEL)) {
            entityCopy.label = this.label;
        }
        if (fieldKeysForEntityCopy.contains(Message.Field.STATE)) {
            entityCopy.state = this.state;
        }
        if (fieldKeysForEntityCopy.contains(Message.Field.CONTROL)) {
            entityCopy.control = this.control;
        }
        if (fieldKeysForEntityCopy.contains(Message.Field.NOTE)) {
            entityCopy.note = this.note;
        }

        if (fieldKeysForEntityCopy.contains(Message.Field.LIFECYCLE)) {
            entityCopy.lifecycle = this.lifecycle;
        }

        if (fieldKeysForEntityCopy.contains(Message.Field.TYPE)) {
            entityCopy.type = this.type;
        }
        if (fieldKeysForEntityCopy.contains(Message.Field.CONTENT)) {
            entityCopy.content = this.content;
        }

        if (fieldKeysForEntityCopy.contains(Message.Field.TIMECREATE)) {
            entityCopy.timeCreate = this.timeCreate;
        }
        if (fieldKeysForEntityCopy.contains(Message.Field.TIMEUPDATE)) {
            entityCopy.timeUpdate = this.timeUpdate;
        }
        if (fieldKeysForEntityCopy.contains(Message.Field.TIMEEXPIRE)) {
            entityCopy.timeExpire = this.timeExpire;
        }

        if (fieldKeysForEntityCopy.contains(Message.Field.USERCREATOR)) {
            entityCopy.userCreator = this.userCreator == null ? null : this.userCreator.makeCopyOfViewOriginal();//TOCHECK better make id view or just set?
        }
        if (fieldKeysForEntityCopy.contains(Message.Field.USERSRECEIVER)) {
            entityCopy.usersReceiver = this.usersReceiver;
        }

        if (fieldKeysForEntityCopy.contains(Message.Field.MEDIASHAS)) {
            entityCopy.mediasHas = this.mediasHas;
        }
        if (fieldKeysForEntityCopy.contains(Message.Field.SESSIONOF)) {
            entityCopy.sessionOf = this.sessionOf == null ? null : this.sessionOf.makeCopyOfViewOriginal();
        }

        return entityCopy;
    }

    @Override
    public Message makeCopyOfView(String view) {
        if (Message.View.ID.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewId();
        }
        if (Message.View.ABSTRACT.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewAbstract();
        }
        if (Message.View.INFO.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewInfo();
        }
        if (Message.View.ORIGINAL.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewOriginal();
        }
        return null;
    }

    @Override
    public Message makeCopyOfViewId() {
        return this.makeCopyOfFields(Message.getFieldKeysForViewId());
    }

    @Override
    public Message makeCopyOfViewAbstract() {
        return this.makeCopyOfFields(Message.getFieldKeysForViewAbstract());
    }

    @Override
    public Message makeCopyOfViewInfo() {
        return this.makeCopyOfFields(Message.getFieldKeysForViewInfo());
    }

    @Override
    public Message makeCopyOfViewOriginal() {
        return this.makeCopyOfFields(Message.getFieldKeysForViewOriginal());
    }

    @Override
    public void madeToView(String view) {
        if (Message.View.ID.equals(view)) {
            this.madeToViewId();
        }
        if (Message.View.ABSTRACT.equals(view)) {
            this.madeToViewAbstract();
        }
        if (Message.View.INFO.equals(view)) {
            this.madeToViewInfo();
        }
    }

    @Override
    public void madeToViewId() {
        List<String> fieldKeysToKeep = Message.getFieldKeysForViewId();
        List<String> fieldKeysToSetNil = Message.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewAbstract() {
        List<String> fieldKeysToKeep = Message.getFieldKeysForViewAbstract();
        List<String> fieldKeysToSetNil = Message.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewInfo() {
        List<String> fieldKeysToKeep = Message.getFieldKeysForViewInfo();
        List<String> fieldKeysToSetNil = Message.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    protected void util_setNilForFields(List<String> fields) {
        super.util_setNilForFields(fields);

        if (fields == null) {
            return;
        }

        if (fields.contains(Field.LIFECYCLE)) {
            this.lifecycle = null;
        }
        if (fields.contains(Field.TYPE)) {
            this.type = null;
        }
        if (fields.contains(Field.CONTENT)) {
            this.content = null;
        }
        if (fields.contains(Field.TIMECREATE)) {
            this.timeCreate = null;
        }
        if (fields.contains(Field.TIMEUPDATE)) {
            this.timeUpdate = null;
        }
        if (fields.contains(Field.TIMEEXPIRE)) {
            this.timeExpire = null;
        }
        if (fields.contains(Field.USERCREATOR)) {
            this.userCreator = null;
        }
        if (fields.contains(Field.USERSRECEIVER)) {
            this.usersReceiver = null;
        }
        if (fields.contains(Field.MEDIASHAS)) {
            this.mediasHas = null;
        }
        if (fields.contains(Field.SESSIONOF)) {
            this.sessionOf = null;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Message> makeMessageListFromEntityListOfObject(Object entityListOfObject) throws Exception {

        List<Message> entityList = null;

        if (entityListOfObject == null) {
            return entityList;
        }

        List<Object> entityOfObjectList = null;
        if (entityListOfObject instanceof String) {
            JSONArray entityListOfJao = new JSONArray((String) entityListOfObject);
            entityOfObjectList = JSONHelper.transformJSONArrayToList(entityListOfJao);
        } else if (entityListOfObject instanceof JSONArray) {
            JSONArray entityListOfJao = (JSONArray) entityListOfObject;
            entityOfObjectList = JSONHelper.transformJSONArrayToList(entityListOfJao);
        } else if (entityListOfObject instanceof List) {
            entityOfObjectList = (List<Object>) entityListOfObject;
        } else {
            //raise unsupported exception?
            String message = ListException.createExceptionMessage(null, "unsupported Object for Message list deserialization");
            throw new ListException(message);
        }

        if (entityOfObjectList == null) {
            return null;
        }

        entityList = new ArrayList<Message>();
        for (int i=0; i< entityOfObjectList.size(); i++) {
            Object entityOfObject = entityOfObjectList.get(i);
            Message entity = new Message(entityOfObject);
            entityList.add(entity);
        }
        return entityList;
    }

    //used for deserialize list<list<Message>> for query results//TOTEST
    @SuppressWarnings("unchecked")
    public static List<List<Message>> makeMessageListListFromEntityListListOfObject(Object entityListListOfObject) throws Exception {

        List<List<Message>> entityListList = null;

        if (entityListListOfObject == null) {
            return entityListList;
        }

        List<Object> entityListOfObjectList = null;
        //assume it is a jas, not handling list string
        if (entityListListOfObject instanceof String) {
            JSONArray entityListListOfJao = new JSONArray((String) entityListListOfObject);
            entityListOfObjectList = JSONHelper.transformJSONArrayToList(entityListListOfJao);
        } else if (entityListListOfObject instanceof JSONArray) {
            JSONArray entityListListOfJao = (JSONArray) entityListListOfObject;
            entityListOfObjectList = JSONHelper.transformJSONArrayToList(entityListListOfJao);
        } else if (entityListListOfObject instanceof List) {
            entityListOfObjectList = (List<Object>) entityListListOfObject;
        } else {
            //raise unsupported exception?
            String message = ListException.createExceptionMessage(null, "unsupported Object for EventVAR message list list deserialization");
            throw new ListException(message);
        }

        if (entityListOfObjectList == null) {
            return null;
        }

        entityListList = new ArrayList<List<Message>>();
        for (int i=0; i< entityListOfObjectList.size(); i++) {
            Object entityListOfObject = entityListOfObjectList.get(i);
            List<Message> entityList = Message.makeMessageListFromEntityListOfObject(entityListOfObject);
            entityListList.add(entityList);
        }
        return entityListList;
    }

    public static Map<String, List<Message>> util_makeSessionIdToMessagesHasMap(List<Message> messageList) {
        Map<String, List<Message>> rt = new HashMap<String, List<Message>>();//return null is not useful but requires null check so always at least return an emtpy map
        if (messageList == null) {
            return rt;
        }
        for (int i = 0; i < messageList.size(); i++) {
            Message message = messageList.get(i);
            if (message != null && message.sessionOf != null && message.sessionOf.id != null) {
                List<Message> messagesHas = rt.get(message.sessionOf.id);
                if (messagesHas == null) {
                    messagesHas = new ArrayList<Message>();
                    messagesHas.add(message);
                    rt.put(message.sessionOf.id, messagesHas);
                } else {
                    //timeCreate and userCreator should not be null
                    messagesHas.add(message);
                    Collections.sort(messagesHas, new Comparator<Message>() {
                        @Override
                        public int compare(Message o1, Message o2) {
                            // TODO Auto-generated method stub
                            int timeCreateComparison = o1.timeCreate.compareTo(o2.timeCreate);
                            if (timeCreateComparison != 0) {
                                return timeCreateComparison;
                            } else {
                                if (o1.userCreator.name == null && o2.userCreator.name != null) {
                                    return -1;
                                }
                                if (o1.userCreator.name != null && o2.userCreator.name == null) {
                                    return 1;
                                }
                                if (o1.userCreator.name == null && o2.userCreator.name == null) {
                                    return 0;
                                }
                                return o1.userCreator.name.compareTo(o2.userCreator.name);
                            }
                        }
                    });
                }
            }
        }
        return rt;
    }
}
