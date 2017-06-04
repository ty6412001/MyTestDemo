package com.fsd.mvvmlight.freeseeds.entity;


import com.fsd.mvvmlight.freeseeds.entity.query.QueryFilterOfRelation;
import com.fsd.mvvmlight.freeseeds.exception.ListException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import library.helper.JSONHelper;
import library.helper.JSONHelper.JSONObjectWrapper;


public class Session extends Entity {

    public static final class Field extends Entity.Field {

        public static final String LIFECYCLE = "lifecycle";

        public static final String TITLE = "title";
        public static final String CONTENT = "content";//description
        public static final String CATEGORY = "category";

        public static final String TIMECREATE = "timeCreate";
        public static final String TIMEUPDATE = "timeUpdate";
        public static final String TIMEEXPIRE = "timeExpire";

        public static final String USERCREATOR = Role.USERCREATOR;
        public static final String USERSADMIN = Role.USERSADMIN;
        public static final String USERSMEMBER = Role.USERSMEMBER;

        public static final String TOPICOF = "topicOf";
        public static final String MESSAGESHAS = "messagesHas";
    }

    public static final class Class {
        public static final String value = "session";
    }

    public static final class Role {
        public static final String USERCREATOR = "userCreator";
        public static final String USERSADMIN = "usersAdmin";
        public static final String USERSMEMBER = "usersMember";

        public static final String USERSUNKNOWN = "usersUnknown";
    }

    public class View extends Entity.View {

    }

    public class Lifecycle {
        public static final String ACTIVE = "active";
        public static final String INACTIVE = "inactive";
        public static final String ABSENT = "absent";
    }

    public String lifecycle;

    public String title;
    public String content;
    public String category;

    public Long timeCreate;
    public Long timeUpdate;
    public Long timeExpire;

    public User userCreator;
    public List<User> usersAdmin;
    public List<User> usersMember;

    public Topic topicOf;
    public List<Message> messagesHas;

    public Session() {
        super();
    }

    public Session(String id, Boolean dummySignatureForInitById) {
        super(id, dummySignatureForInitById);
    }

//	public Session(String id, Topic topicOf) {
//		super(id);
//		this.topicOf = topicOf;
//	}

    public Session(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                   String lifecycle,
                   String title, String content, String category,
                   Long timeCreate, Long timeUpdate, Long timeExpire,
                   User userCreator, List<User> usersAdmin, List<User> usersMember,
                   Topic topicOf, List<Message> messagesHas) throws Exception {

        this.self_installFields(clazz, id, label, state, control, note,
                lifecycle,
                title, content, category,
                timeCreate, timeUpdate, timeExpire,
                userCreator, usersAdmin, usersMember,
                topicOf, messagesHas);

        this.sub_validateFields();
    }

    //TODO after init do we set initAndTemp to null?
    public Session(Object entityOfObject) throws Exception {
        super(entityOfObject);
    }

    protected void self_installFields(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                                      String lifecycle,
                                      String title, String content, String category,
                                      Long timeCreate, Long timeUpdate, Long timeExpire,
                                      User userCreator, List<User> usersAdmin, List<User> usersMember, Topic topicOf, List<Message> messagesHas) {

        super.self_installFields(clazz, id, label, state, control, note);

        this.lifecycle = lifecycle;

        this.title = title;
        this.content = content;
        this.category = category;

        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;
        this.timeExpire = timeExpire;

        this.userCreator = userCreator;
        this.usersAdmin = usersAdmin;
        this.usersMember = usersMember;

        this.topicOf = topicOf;
        this.messagesHas = messagesHas;
    }

    @Override
    protected void sub_decodeFields(JSONObjectWrapper entityJwo) throws Exception {
        super.sub_decodeFields(entityJwo);
        try {
            if (entityJwo == null) {
                return;
            } else {
                this.lifecycle = entityJwo.getString(Field.LIFECYCLE);
                this.title = entityJwo.getString(Field.TITLE);
                this.content = entityJwo.getString(Field.CONTENT);
                this.category = entityJwo.getString(Field.CATEGORY);
                this.timeCreate = entityJwo.getLong(Field.TIMECREATE);
                this.timeUpdate = entityJwo.getLong(Field.TIMEUPDATE);
                this.timeExpire = entityJwo.getLong(Field.TIMEEXPIRE);

                if (entityJwo.has(Field.USERCREATOR)) {
                    this.userCreator = new User(entityJwo.get(Field.USERCREATOR));
                }

                this.usersAdmin = User.makeUserListFromEntityListOfObject(entityJwo.get(Field.USERSADMIN));
                this.usersMember = User.makeUserListFromEntityListOfObject(entityJwo.get(Field.USERSMEMBER));

                if (entityJwo.has(Field.TOPICOF)) {
                    this.topicOf = new Topic(entityJwo.get(Field.TOPICOF));
                }

                this.messagesHas = Message.makeMessageListFromEntityListOfObject(entityJwo.get(Field.MESSAGESHAS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void sub_validateFields() throws Exception {
        if (this.state != null && !Topic.getStateValues().contains(this.state.toLowerCase())) {
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
            entityJoo.put(Field.TITLE, title);
            entityJoo.put(Field.CONTENT, content);
            entityJoo.put(Field.CATEGORY, category);
            entityJoo.put(Field.TIMECREATE, timeCreate);
            entityJoo.put(Field.TIMEUPDATE, timeUpdate);
            entityJoo.put(Field.TIMEEXPIRE, timeExpire);

            if (this.userCreator!=null) {
                entityJoo.put(Field.USERCREATOR, userCreator.toJSONObject());
            }

            entityJoo.put(Field.USERSADMIN, User.makeJSONArrayFromEntityList(this.usersAdmin));
            entityJoo.put(Field.USERSMEMBER, User.makeJSONArrayFromEntityList(this.usersMember));

            if (this.topicOf!=null) {
                entityJoo.put(Field.TOPICOF, this.topicOf.toJSONObject());
            }

            entityJoo.put(Field.MESSAGESHAS, Message.makeJSONArrayFromEntityList(this.messagesHas));

        } catch (Exception e) {
            e.printStackTrace();
            entityJoo = new JSONObject();
        }
        return entityJoo;
    }

    @Override
    public boolean equals(Object object) {
        boolean rt = false;
        if (object instanceof Session) {
            Session session = (Session) object;
            if (this.id != null) {
                rt = this.id.equals(session.id);
            } else if (session.id == null) {
                rt = true;
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
        if (this.usersAdmin!= null) {
            if (this.usersAdmin.contains(user) || this.usersAdmin.contains(User.makePredefinedUserAnyUser())) {
                String role = Role.USERSADMIN;
                roles.add(role);
            }
        }
        if (this.usersMember!= null) {
            if (this.usersMember.contains(user) || this.usersMember.contains(User.makePredefinedUserAnyUser())) {
                String role = Role.USERSMEMBER;
                roles.add(role);
            }
        }
        if (roles.isEmpty()) {
            roles.add(Role.USERSUNKNOWN);
        }

        return roles;
    }

    public static List<String> getLifecycleValues() {
        List<String> lifecycleValues = new ArrayList<String>();
        lifecycleValues.add(Lifecycle.ACTIVE.toLowerCase());
        lifecycleValues.add(Lifecycle.INACTIVE.toLowerCase());
        lifecycleValues.add(Lifecycle.ABSENT.toLowerCase());
        return lifecycleValues;
    }

    public static List<String> getFieldKeys() {
        List<String> fieldKeys = new ArrayList<String>();

        fieldKeys.add(Session.Field.CLASS);
        fieldKeys.add(Session.Field.ID);
        fieldKeys.add(Session.Field.LABEL);
        fieldKeys.add(Session.Field.STATE);
        fieldKeys.add(Session.Field.CONTROL);
        fieldKeys.add(Session.Field.NOTE);

        fieldKeys.add(Session.Field.LIFECYCLE);

        fieldKeys.add(Session.Field.TITLE);
        fieldKeys.add(Session.Field.CONTENT);
        fieldKeys.add(Session.Field.CATEGORY);

        fieldKeys.add(Session.Field.TIMECREATE);
        fieldKeys.add(Session.Field.TIMEUPDATE);
        fieldKeys.add(Session.Field.TIMEEXPIRE);

        fieldKeys.add(Session.Field.USERCREATOR);
        fieldKeys.add(Session.Field.USERSADMIN);
        fieldKeys.add(Session.Field.USERSMEMBER);

        fieldKeys.add(Session.Field.TOPICOF);
        fieldKeys.add(Session.Field.MESSAGESHAS);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForView(String view) {
        if (Session.View.ID.equals(view)) {
            return Session.getFieldKeysForViewId();
        }
        if (Session.View.ABSTRACT.equals(view)) {
            return Session.getFieldKeysForViewAbstract();
        }
        if (Session.View.INFO.equals(view)) {
            return Session.getFieldKeysForViewInfo();
        }
        if (Session.View.ORIGINAL.equals(view)) {
            return Session.getFieldKeysForViewOriginal();
        }
        return new ArrayList<String>();
    }

    public static List<String> getFieldKeysForViewId() {
        List<String> fieldKeys = new ArrayList<String>();
        fieldKeys.add(Topic.Field.ID);
        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewAbstract() {
        List<String> fieldKeys = new ArrayList<String>();

        //        fieldKeys.add(Session.Field.CLASS);
        fieldKeys.add(Session.Field.ID);
        //        fieldKeys.add(Session.Field.LABEL);
        //        fieldKeys.add(Session.Field.STATE);
        fieldKeys.add(Session.Field.CONTROL);
        //        fieldKeys.add(Session.Field.NOTE);

        fieldKeys.add(Session.Field.LIFECYCLE);

        fieldKeys.add(Session.Field.TITLE);
        fieldKeys.add(Session.Field.CONTENT);
        fieldKeys.add(Session.Field.CATEGORY);

        fieldKeys.add(Session.Field.TIMECREATE);
        fieldKeys.add(Session.Field.TIMEUPDATE);
        fieldKeys.add(Session.Field.TIMEEXPIRE);

        fieldKeys.add(Session.Field.USERCREATOR);
        fieldKeys.add(Session.Field.USERSADMIN);
        fieldKeys.add(Session.Field.USERSMEMBER);

        fieldKeys.add(Session.Field.TOPICOF);
        //        fieldKeys.add(Session.Field.MESSAGESHAS);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewInfo() {
        List<String> fieldKeys = new ArrayList<String>();

        //        fieldKeys.add(Session.Field.CLASS);
        fieldKeys.add(Session.Field.ID);
        //        fieldKeys.add(Session.Field.LABEL);
        //        fieldKeys.add(Session.Field.STATE);
        fieldKeys.add(Session.Field.CONTROL);
        //        fieldKeys.add(Session.Field.NOTE);

        fieldKeys.add(Session.Field.LIFECYCLE);

        fieldKeys.add(Session.Field.TITLE);
        fieldKeys.add(Session.Field.CONTENT);
        fieldKeys.add(Session.Field.CATEGORY);

        fieldKeys.add(Session.Field.TIMECREATE);
        fieldKeys.add(Session.Field.TIMEUPDATE);
        fieldKeys.add(Session.Field.TIMEEXPIRE);

        fieldKeys.add(Session.Field.USERCREATOR);
        fieldKeys.add(Session.Field.USERSADMIN);
        fieldKeys.add(Session.Field.USERSMEMBER);

        fieldKeys.add(Session.Field.TOPICOF);
        //        fieldKeys.add(Session.Field.MESSAGESHAS);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewOriginal() {
        List<String> fieldKeys = new ArrayList<String>();

        fieldKeys.add(Session.Field.CLASS);
        fieldKeys.add(Session.Field.ID);
        fieldKeys.add(Session.Field.LABEL);
        fieldKeys.add(Session.Field.STATE);
        fieldKeys.add(Session.Field.CONTROL);
        fieldKeys.add(Session.Field.NOTE);

        fieldKeys.add(Session.Field.LIFECYCLE);

        fieldKeys.add(Session.Field.TITLE);
        fieldKeys.add(Session.Field.CONTENT);
        fieldKeys.add(Session.Field.CATEGORY);

        fieldKeys.add(Session.Field.TIMECREATE);
        fieldKeys.add(Session.Field.TIMEUPDATE);
        fieldKeys.add(Session.Field.TIMEEXPIRE);

        fieldKeys.add(Session.Field.USERCREATOR);
        fieldKeys.add(Session.Field.USERSADMIN);
        fieldKeys.add(Session.Field.USERSMEMBER);

        fieldKeys.add(Session.Field.TOPICOF);
        fieldKeys.add(Session.Field.MESSAGESHAS);

        return fieldKeys;
    }

    @Override
    public Session makeCopyOfFields(List<String> fieldKeys) {

        List<String> fieldKeysForEntityCopy = Session.getFieldKeys();
        if (fieldKeys != null) {
            fieldKeysForEntityCopy = fieldKeys;
        }

        Session entityCopy = new Session();

        if (fieldKeysForEntityCopy.contains(Session.Field.CLASS)) {
            entityCopy.clazz = this.clazz;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.ID)) {
            entityCopy.id = this.id;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.LABEL)) {
            entityCopy.label = this.label;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.STATE)) {
            entityCopy.state = this.state;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.CONTROL)) {
            entityCopy.control = this.control;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.NOTE)) {
            entityCopy.note = this.note;
        }

        if (fieldKeysForEntityCopy.contains(Session.Field.LIFECYCLE)) {
            entityCopy.lifecycle = this.lifecycle;
        }

        if (fieldKeysForEntityCopy.contains(Session.Field.TITLE)) {
            entityCopy.title = this.title;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.CONTENT)) {
            entityCopy.content = this.content;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.CATEGORY)) {
            entityCopy.category = this.category;
        }

        if (fieldKeysForEntityCopy.contains(Session.Field.TIMECREATE)) {
            entityCopy.timeCreate = this.timeCreate;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.TIMEUPDATE)) {
            entityCopy.timeUpdate = this.timeUpdate;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.TIMEEXPIRE)) {
            entityCopy.timeExpire = this.timeExpire;
        }

        if (fieldKeysForEntityCopy.contains(Session.Field.USERCREATOR)) {
            entityCopy.userCreator = this.userCreator;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.USERSADMIN)) {
            entityCopy.usersAdmin = this.usersAdmin;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.USERSMEMBER)) {
            entityCopy.usersMember = this.usersMember;
        }

        if (fieldKeysForEntityCopy.contains(Session.Field.TOPICOF)) {
            entityCopy.topicOf = this.topicOf;
        }
        if (fieldKeysForEntityCopy.contains(Session.Field.MESSAGESHAS)) {
            entityCopy.messagesHas = this.messagesHas;
        }

        return entityCopy;
    }

    @Override
    public Session makeCopyOfView(String view) {
        if (Session.View.ID.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewId();
        }
        if (Session.View.ABSTRACT.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewAbstract();
        }
        if (Session.View.INFO.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewInfo();
        }
        if (Session.View.ORIGINAL.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewOriginal();
        }
        return null;
    }

    @Override
    public Session makeCopyOfViewId() {
        return this.makeCopyOfFields(Session.getFieldKeysForViewId());
    }

    @Override
    public Session makeCopyOfViewAbstract() {
        return this.makeCopyOfFields(Session.getFieldKeysForViewAbstract());
    }

    @Override
    public Session makeCopyOfViewInfo() {
        return this.makeCopyOfFields(Session.getFieldKeysForViewInfo());
    }

    @Override
    public Session makeCopyOfViewOriginal() {
        return this.makeCopyOfFields(Session.getFieldKeysForViewOriginal());
    }

    @Override
    public void madeToView(String view) {
        if (Session.View.ID.equals(view)) {
            this.madeToViewId();
        }
        if (Session.View.ABSTRACT.equals(view)) {
            this.madeToViewAbstract();
        }
        if (Session.View.INFO.equals(view)) {
            this.madeToViewInfo();
        }
    }

    @Override
    public void madeToViewId() {
        List<String> fieldKeysToKeep = Session.getFieldKeysForViewId();
        List<String> fieldKeysToSetNil = Session.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewAbstract() {
        List<String> fieldKeysToKeep = Session.getFieldKeysForViewAbstract();
        List<String> fieldKeysToSetNil = Session.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewInfo() {
        List<String> fieldKeysToKeep = Session.getFieldKeysForViewInfo();
        List<String> fieldKeysToSetNil = Session.getFieldKeysForViewOriginal();
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
        if (fields.contains(Field.TITLE)) {
            this.title = null;
        }
        if (fields.contains(Field.CONTENT)) {
            this.content = null;
        }
        if (fields.contains(Field.CATEGORY)) {
            this.category = null;
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
        if (fields.contains(Field.USERSADMIN)) {
            this.usersAdmin = null;
        }
        if (fields.contains(Field.USERSMEMBER)) {
            this.usersMember = null;
        }
        if (fields.contains(Field.TOPICOF)) {
            this.topicOf = null;
        }
        if (fields.contains(Field.MESSAGESHAS)) {
            this.messagesHas = null;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Session> makeSessionListFromEntityListOfObject(Object entityListOfObject) throws Exception {

        List<Session> entityList = null;

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
            String message = ListException.createExceptionMessage(null, "unsupported Object for EventVAR topic deserialization");
            throw new ListException(message);
        }

        if (entityOfObjectList == null) {
            return null;
        }

        entityList = new ArrayList<Session>();
        for (int i=0; i< entityOfObjectList.size(); i++) {
            Object entityOfObject = entityOfObjectList.get(i);
            Session entity = new Session(entityOfObject);
            entityList.add(entity);
        }
        return entityList;
    }

    //used for deserialize list<list<Topic>> for query results//TOTEST
    @SuppressWarnings("unchecked")
    public static List<List<Session>> makeSessionListListFromEntityListListOfObject(Object entityListListOfObject) throws Exception {

        List<List<Session>> entityListList = null;

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
            String message = ListException.createExceptionMessage(null, "unsupported Object for EventVAR topic list list deserialization");
            throw new ListException(message);
        }

        if (entityListOfObjectList == null) {
            return null;
        }

        entityListList = new ArrayList<List<Session>>();
        for (int i=0; i< entityListOfObjectList.size(); i++) {
            Object entityListOfObject = entityListOfObjectList.get(i);
            List<Session> entityList = Session.makeSessionListFromEntityListOfObject(entityListOfObject);
            entityListList.add(entityList);
        }
        return entityListList;
    }

    //assume leaf already pruned
    public static List<Session> util_pruneSessionList(List<Session> sessionList, QueryFilterOfRelation relationFilter) {
        List<Session> rt = sessionList;
        if (sessionList == null || relationFilter == null) {
            return rt;
        }
        Iterator<Session> iterSession = rt.iterator();
        while (iterSession.hasNext()) {
            Session session = iterSession.next();
            if (session.messagesHas == null || session.messagesHas.size() == 0) {
                //filter session itself using relationFilter
                String relationField = relationFilter.field;
                Long fieldValueToEval = null;
                if (Session.Field.TIMECREATE.equals(relationField)) {
                    fieldValueToEval = session.timeCreate;
                } else if (Session.Field.TIMEUPDATE.equals(relationField)) {
                    fieldValueToEval = session.timeUpdate;
                } else if (Session.Field.TIMEEXPIRE.equals(relationField)) {
                    fieldValueToEval = session.timeExpire;
                }
                if (fieldValueToEval != null) {
                    if (!relationFilter.eval(fieldValueToEval)) {
                        iterSession.remove();
                    }
                }
            }
        }
        return rt;
    }
}
