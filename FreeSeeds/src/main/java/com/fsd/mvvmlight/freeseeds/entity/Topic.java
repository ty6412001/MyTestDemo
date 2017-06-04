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

public class Topic extends Entity {

    public static final class Field extends Entity.Field {

        public static final String LIFECYCLE = "lifecycle";

        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String CATEGORY = "category";
        public static final String OBJECTIVES = "objectives";//offer, want//share

        public static final String TIMECREATE = "timeCreate";
        public static final String TIMEUPDATE = "timeUpdate";
        public static final String TIMEEXPIRE = "timeExpire";

        public static final String SPACEPOSITIONX = "spacePositionX";
        public static final String SPACEPOSITIONY = "spacePositionY";
        //add address as alternative? for now assume user converted address to coordinate//TODO force 0 if null?
        public static final String SPACEPOSITIONADDRESS = "spacePositionAddress";//street address. supplemental information, client may perform reverse geo coding to get the address if needed
        public static final String SPACEPOSITIONACCURACY = "spacePositionAccuracy";

        public static final String USERCREATOR = Role.USERCREATOR;
//		public static final String USERSADMIN = Roles.USERSADMIN;//who can view details and has more options//if set to an empty list, anyone is admin, if null go to unknown//probaly use a special all users to represent all...
//		public static final String USERSRECEIVER = Roles.USERSRECEIVER;//who can view info

        public static final String MEDIASHAS = "mediasHas";
        public static final String SESSIONSHAS = "sessionsHas";
    }

    public class View extends Entity.View {

    }

    public static final class Class {
        public static final String value = "topic";
    }

    public static final class Category {
        public static final String OTHERS = "others";
    }

    public static final class Objective {
        public static final String OFFER = "offer";
        public static final String WANT = "want";
    }

    //this may be only needed and customized by client
    public class Lifecycle {
        public static final String ACTIVE = "active";
        public static final String INACTIVE = "inactive";
        public static final String ABSENT = "absent";
//		public static final String INACTIVE_PENDING = "inactive_pending";
    }

    public static final class Role {
        public static final String USERCREATOR = "userCreator";
        public static final String USERSUNKNOWN = "usersUnknown";
    }

    public String lifecycle; //this field and state are reset automatically based on event type

    public String title;
    public String content;
    public String category;//default others//should be done at client side, otherwise if just return topic with id, category will be set to others
    public List<String> objectives;

    public Long timeCreate;
    public Long timeUpdate;
    public Long timeExpire;

    public Double spacePositionX;
    public Double spacePositionY;
    public String spacePositionAddress;
    public Double spacePositionAccuracy;//approximate radius

    public User userCreator;
    //	public List<User> usersAdmin;
//	public List<User> usersReceiver;
    public List<Media> mediasHas;
    public List<Session> sessionsHas;

    public Topic() {
        super();
    }

    public Topic(String id, Boolean dummySignatureForInitById) {//session only need id for the related topic obj
        super(id, dummySignatureForInitById);
        //no local properties added, no self validate all fields to avoid adding unwanted default fields?
    }

    public Topic(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                 String lifecycle,
                 String title, String content, String category, List<String> objectives,
                 Long timeCreate, Long timeUpdate, Long timeExpire,
                 Double spacePositionX, Double spacePositionY, String spacePositionAddress, Double spacePositionAccuracy,
                 User userCreator,
                 List<Media> mediasHas, List<Session> sessionsHas) throws Exception {

        this.self_installFields(clazz, id, label, state, control, note,
                lifecycle,
                title, content, category, objectives,
                timeCreate, timeUpdate, timeExpire,
                spacePositionX, spacePositionY, spacePositionAddress, spacePositionAccuracy,
                userCreator,
                mediasHas, sessionsHas);

        this.sub_validateFields();
    }

    public Topic(Object entityOfObject) throws Exception {
        super(entityOfObject);
    }

    protected void self_installFields(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                                      String lifecycle,
                                      String title, String content, String category, List<String> objectives,
                                      Long timeCreate, Long timeUpdate, Long timeExpire,
                                      Double spacePositionX, Double spacePositionY, String spacePositionAddress, Double spacePositionAccuracy,
                                      User userCreator,
                                      List<Media> mediasHas, List<Session> sessionsHas) {

        super.self_installFields(clazz, id, label, state, control, note);

        this.lifecycle = lifecycle;

        this.title = title;
        this.content = content;
        this.category = category;
        this.objectives = objectives;

        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;
        this.timeExpire = timeExpire;

        this.spacePositionX = spacePositionX;
        this.spacePositionY = spacePositionY;
        this.spacePositionAddress = spacePositionAddress;
        this.spacePositionAccuracy = spacePositionAccuracy;

        this.userCreator = userCreator;

        this.mediasHas = mediasHas;
        this.sessionsHas = sessionsHas;
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
                this.objectives = entityJwo.getValueAsList(Field.OBJECTIVES);
                this.timeCreate = entityJwo.getLong(Field.TIMECREATE);
                this.timeUpdate = entityJwo.getLong(Field.TIMEUPDATE);
                this.timeExpire = entityJwo.getLong(Field.TIMEEXPIRE);
                this.spacePositionX = entityJwo.getDouble(Field.SPACEPOSITIONX);
                this.spacePositionY = entityJwo.getDouble(Field.SPACEPOSITIONY);
                this.spacePositionAddress = entityJwo.getString(Field.SPACEPOSITIONADDRESS);
                this.spacePositionAccuracy = entityJwo.getDouble(Field.SPACEPOSITIONACCURACY);

                if (entityJwo.has(Field.USERCREATOR)) {
                    this.userCreator = new User(entityJwo.get(Field.USERCREATOR));
                }

                this.mediasHas = Media.makeMediaListFromEntityListOfObject(entityJwo.get(Field.MEDIASHAS));
                this.sessionsHas = Session.makeSessionListFromEntityListOfObject(entityJwo.get(Field.SESSIONSHAS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void sub_validateFields() throws Exception {
        //set default if not valid etc.
        if (this.state != null && !Topic.getStateValues().contains(this.state.toLowerCase())) {
            this.state = null;
        }
        //allow client side customized lifecycle values
//		if (this.lifecycle != null && !Topic.getLifecycleValues().contains(this.lifecycle.toLowerCase())) {
//			this.lifecycle = null;
//		}
        //need this?
//		if (this.category == null) {
//			this.category = Category.OTHERS;
//		}
    }

    @Override
    public JSONObject toJSONObject() {

        JSONObject entityJoo = super.toJSONObject();

        try {
            if (entityJoo == null) {
                entityJoo = new JSONObject();
            }
            entityJoo.put(Field.LIFECYCLE, this.lifecycle);
            entityJoo.put(Field.TITLE, this.title);
            entityJoo.put(Field.CONTENT, this.content);
            entityJoo.put(Field.CATEGORY, this.category);
            entityJoo.put(Field.OBJECTIVES, JSONHelper.transformListToJSONArray(this.objectives));
            entityJoo.put(Field.TIMECREATE, this.timeCreate);
            entityJoo.put(Field.TIMEUPDATE, this.timeUpdate);
            entityJoo.put(Field.TIMEEXPIRE, this.timeExpire);
            entityJoo.put(Field.SPACEPOSITIONX, this.spacePositionX);
            entityJoo.put(Field.SPACEPOSITIONY, this.spacePositionY);
            entityJoo.put(Field.SPACEPOSITIONADDRESS, this.spacePositionAddress);
            entityJoo.put(Field.SPACEPOSITIONACCURACY, this.spacePositionAccuracy);
            //avoid loop
            if (this.userCreator!=null) {
                entityJoo.put(Field.USERCREATOR, this.userCreator.toJSONObject());
            }

            entityJoo.put(Field.MEDIASHAS, Media.makeJSONArrayFromEntityList(this.mediasHas));

            entityJoo.put(Field.SESSIONSHAS, Session.makeJSONArrayFromEntityList(this.sessionsHas));

        } catch (Exception e) {
            e.printStackTrace();
            entityJoo = new JSONObject();
        }
        return entityJoo;
    }

    @Override
    public boolean equals(Object object) {
        boolean rt = false;
        if (object instanceof Topic) {
            Topic topic = (Topic) object;
            if (this.id != null) {
                rt = this.id.equals(topic.id);
            } else if (topic.id == null) {
                rt = true;
            }
        }
        return rt;
    }

    public boolean timeExpireIsExpired(Long time) {
        if (this.timeExpire == null) {
            return false;
        }
        Long timeToCompare = time;
        if (timeToCompare == null) {
            timeToCompare = System.currentTimeMillis();
        }

        return timeToCompare > this.timeExpire;
    }

    public List<String> getRolesForUser(User user) {
        List<String> roles = new ArrayList<String>();
        if (this.userCreator!= null) {
            if (this.userCreator.equals(user)) {
                String role = Role.USERCREATOR;
                roles.add(role);
            }
        }
//		if (this.usersAdmin!= null) {
//			if (this.usersAdmin.contains(user) || this.usersAdmin.contains(User.makePredefinedUserAnyUser())) {
//				String role = Roles.USERSADMIN;
//				roles.add(role);
//			}
//		}
//		if (this.usersReceiver!= null) {
//			if (this.usersReceiver.contains(user) || this.usersReceiver.contains(User.makePredefinedUserAnyUser())) {
//				String role = Roles.USERSRECEIVER;
//				roles.add(role);
//			}
//		}

        if (roles.isEmpty()) {
            roles.add(Role.USERSUNKNOWN);
        }
        return roles;
    }

    public static List<String> getObjectiveValues() {
        List<String> objectiveValues = new ArrayList<String>();
        objectiveValues.add(Objective.OFFER.toLowerCase());
        objectiveValues.add(Objective.WANT.toLowerCase());
        return objectiveValues;
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

        fieldKeys.add(Topic.Field.CLASS);
        fieldKeys.add(Topic.Field.ID);
        fieldKeys.add(Topic.Field.LABEL);
        fieldKeys.add(Topic.Field.STATE);
        fieldKeys.add(Topic.Field.CONTROL);
        fieldKeys.add(Topic.Field.NOTE);

        fieldKeys.add(Topic.Field.LIFECYCLE);

        fieldKeys.add(Topic.Field.TITLE);
        fieldKeys.add(Topic.Field.CONTENT);
        fieldKeys.add(Topic.Field.CATEGORY);
        fieldKeys.add(Topic.Field.OBJECTIVES);

        fieldKeys.add(Topic.Field.TIMECREATE);
        fieldKeys.add(Topic.Field.TIMEUPDATE);
        fieldKeys.add(Topic.Field.TIMEEXPIRE);

        fieldKeys.add(Topic.Field.SPACEPOSITIONX);
        fieldKeys.add(Topic.Field.SPACEPOSITIONY);
        fieldKeys.add(Topic.Field.SPACEPOSITIONADDRESS);
        fieldKeys.add(Topic.Field.SPACEPOSITIONACCURACY);

        fieldKeys.add(Topic.Field.USERCREATOR);
        fieldKeys.add(Topic.Field.MEDIASHAS);
        fieldKeys.add(Topic.Field.SESSIONSHAS);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForView(String view) {
        if (Topic.View.ID.equals(view)) {
            return Topic.getFieldKeysForViewId();
        }
        if (Topic.View.ABSTRACT.equals(view)) {
            return Topic.getFieldKeysForViewAbstract();
        }
        if (Topic.View.INFO.equals(view)) {
            return Topic.getFieldKeysForViewInfo();
        }
        if (Topic.View.ORIGINAL.equals(view)) {
            return Topic.getFieldKeysForViewOriginal();
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

//    	fieldKeys.add(Topic.Field.CLASS);
        fieldKeys.add(Topic.Field.ID);
//        fieldKeys.add(Topic.Field.LABEL);
//        fieldKeys.add(Topic.Field.STATE);
        fieldKeys.add(Topic.Field.CONTROL);
//        fieldKeys.add(Topic.Field.NOTE);

        fieldKeys.add(Topic.Field.LIFECYCLE);

        fieldKeys.add(Topic.Field.TITLE);
        fieldKeys.add(Topic.Field.CONTENT);
        fieldKeys.add(Topic.Field.CATEGORY);
        fieldKeys.add(Topic.Field.OBJECTIVES);

        fieldKeys.add(Topic.Field.TIMECREATE);
        fieldKeys.add(Topic.Field.TIMEUPDATE);
        fieldKeys.add(Topic.Field.TIMEEXPIRE);

        fieldKeys.add(Topic.Field.SPACEPOSITIONX);
        fieldKeys.add(Topic.Field.SPACEPOSITIONY);
        fieldKeys.add(Topic.Field.SPACEPOSITIONADDRESS);
        fieldKeys.add(Topic.Field.SPACEPOSITIONACCURACY);

        fieldKeys.add(Topic.Field.USERCREATOR);
        fieldKeys.add(Topic.Field.MEDIASHAS);
//        fieldKeys.add(Topic.Field.SESSIONSHAS);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewInfo() {
        List<String> fieldKeys = new ArrayList<String>();

//    	fieldKeys.add(Topic.Field.CLASS);
        fieldKeys.add(Topic.Field.ID);
//        fieldKeys.add(Topic.Field.LABEL);
//        fieldKeys.add(Topic.Field.STATE);
        fieldKeys.add(Topic.Field.CONTROL);
//        fieldKeys.add(Topic.Field.NOTE);

        fieldKeys.add(Topic.Field.LIFECYCLE);

        fieldKeys.add(Topic.Field.TITLE);
        fieldKeys.add(Topic.Field.CONTENT);
        fieldKeys.add(Topic.Field.CATEGORY);
        fieldKeys.add(Topic.Field.OBJECTIVES);

        fieldKeys.add(Topic.Field.TIMECREATE);
        fieldKeys.add(Topic.Field.TIMEUPDATE);
        fieldKeys.add(Topic.Field.TIMEEXPIRE);

        fieldKeys.add(Topic.Field.SPACEPOSITIONX);
        fieldKeys.add(Topic.Field.SPACEPOSITIONY);
        fieldKeys.add(Topic.Field.SPACEPOSITIONADDRESS);
        fieldKeys.add(Topic.Field.SPACEPOSITIONACCURACY);

        fieldKeys.add(Topic.Field.USERCREATOR);
        fieldKeys.add(Topic.Field.MEDIASHAS);
//        fieldKeys.add(Topic.Field.SESSIONSHAS);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewOriginal() {
        List<String> fieldKeys = new ArrayList<String>();

        fieldKeys.add(Topic.Field.CLASS);
        fieldKeys.add(Topic.Field.ID);
        fieldKeys.add(Topic.Field.LABEL);
        fieldKeys.add(Topic.Field.STATE);
        fieldKeys.add(Topic.Field.CONTROL);
        fieldKeys.add(Topic.Field.NOTE);

        fieldKeys.add(Topic.Field.LIFECYCLE);

        fieldKeys.add(Topic.Field.TITLE);
        fieldKeys.add(Topic.Field.CONTENT);
        fieldKeys.add(Topic.Field.CATEGORY);
        fieldKeys.add(Topic.Field.OBJECTIVES);

        fieldKeys.add(Topic.Field.TIMECREATE);
        fieldKeys.add(Topic.Field.TIMEUPDATE);
        fieldKeys.add(Topic.Field.TIMEEXPIRE);

        fieldKeys.add(Topic.Field.SPACEPOSITIONX);
        fieldKeys.add(Topic.Field.SPACEPOSITIONY);
        fieldKeys.add(Topic.Field.SPACEPOSITIONADDRESS);
        fieldKeys.add(Topic.Field.SPACEPOSITIONACCURACY);

        fieldKeys.add(Topic.Field.USERCREATOR);
        fieldKeys.add(Topic.Field.MEDIASHAS);
        fieldKeys.add(Topic.Field.SESSIONSHAS);

        return fieldKeys;
    }

    @Override
    public Topic makeCopyOfFields(List<String> fieldKeys) {

        List<String> fieldKeysForEntityCopy = Topic.getFieldKeys();
        if (fieldKeys != null) {
            fieldKeysForEntityCopy = fieldKeys;
        }

        Topic entityCopy = new Topic();

        if (fieldKeysForEntityCopy.contains(Topic.Field.CLASS)) {
            entityCopy.clazz = this.clazz;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.ID)) {
            entityCopy.id = this.id;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.LABEL)) {
            entityCopy.label = this.label;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.STATE)) {
            entityCopy.state = this.state;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.CONTROL)) {
            entityCopy.control = this.control;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.NOTE)) {
            entityCopy.note = this.note;
        }

        if (fieldKeysForEntityCopy.contains(Topic.Field.LIFECYCLE)) {
            entityCopy.lifecycle = this.lifecycle;
        }

        if (fieldKeysForEntityCopy.contains(Topic.Field.TITLE)) {
            entityCopy.title = this.title;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.CONTENT)) {
            entityCopy.content = this.content;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.CATEGORY)) {
            entityCopy.category = this.category;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.OBJECTIVES)) {
            entityCopy.objectives = this.objectives;
        }

        if (fieldKeysForEntityCopy.contains(Topic.Field.TIMECREATE)) {
            entityCopy.timeCreate = this.timeCreate;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.TIMEUPDATE)) {
            entityCopy.timeUpdate = this.timeUpdate;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.TIMEEXPIRE)) {
            entityCopy.timeExpire = this.timeExpire;
        }

        if (fieldKeysForEntityCopy.contains(Topic.Field.SPACEPOSITIONX)) {
            entityCopy.spacePositionX = this.spacePositionX;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.SPACEPOSITIONY)) {
            entityCopy.spacePositionY = this.spacePositionY;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.SPACEPOSITIONADDRESS)) {
            entityCopy.spacePositionAddress = this.spacePositionAddress;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.SPACEPOSITIONACCURACY)) {
            entityCopy.spacePositionAccuracy = this.spacePositionAccuracy;
        }

        if (fieldKeysForEntityCopy.contains(Topic.Field.USERCREATOR)) {
            entityCopy.userCreator = this.userCreator;
        }

        if (fieldKeysForEntityCopy.contains(Topic.Field.MEDIASHAS)) {
            entityCopy.mediasHas = this.mediasHas;
        }
        if (fieldKeysForEntityCopy.contains(Topic.Field.SESSIONSHAS)) {
            entityCopy.sessionsHas = this.sessionsHas;
        }

        return entityCopy;
    }

    @Override
    public Topic makeCopyOfView(String view) {
        if (Topic.View.ID.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewId();
        }
        if (Topic.View.ABSTRACT.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewAbstract();
        }
        if (Topic.View.INFO.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewInfo();
        }
        if (Topic.View.ORIGINAL.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewOriginal();
        }
        return null;
    }

    @Override
    public Topic makeCopyOfViewId() {
        return this.makeCopyOfFields(Topic.getFieldKeysForViewId());
    }

    @Override
    public Topic makeCopyOfViewAbstract() {
        return this.makeCopyOfFields(Topic.getFieldKeysForViewAbstract());
    }

    @Override
    public Topic makeCopyOfViewInfo() {
        return this.makeCopyOfFields(Topic.getFieldKeysForViewInfo());
    }

    @Override
    public Topic makeCopyOfViewOriginal() {
        return this.makeCopyOfFields(Topic.getFieldKeysForViewOriginal());
    }

    @Override
    public void madeToView(String view) {
        if (Topic.View.ID.equals(view)) {
            this.madeToViewId();
        }
        if (Topic.View.ABSTRACT.equals(view)) {
            this.madeToViewAbstract();
        }
        if (Topic.View.INFO.equals(view)) {
            this.madeToViewInfo();
        }
    }

    @Override
    public void madeToViewId() {
        List<String> fieldKeysToKeep = Topic.getFieldKeysForViewId();
        List<String> fieldKeysToSetNil = Topic.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewAbstract() {
        List<String> fieldKeysToKeep = Topic.getFieldKeysForViewAbstract();
        List<String> fieldKeysToSetNil = Topic.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewInfo() {
        List<String> fieldKeysToKeep = Topic.getFieldKeysForViewInfo();
        List<String> fieldKeysToSetNil = Topic.getFieldKeysForViewOriginal();
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
        if (fields.contains(Field.OBJECTIVES)) {
            this.objectives = null;
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
        if (fields.contains(Field.SPACEPOSITIONX)) {
            this.spacePositionX = null;
        }
        if (fields.contains(Field.SPACEPOSITIONY)) {
            this.spacePositionY = null;
        }
        if (fields.contains(Field.SPACEPOSITIONADDRESS)) {
            this.spacePositionAddress = null;
        }
        if (fields.contains(Field.SPACEPOSITIONACCURACY)) {
            this.spacePositionAccuracy = null;
        }
        if (fields.contains(Field.USERCREATOR)) {
            this.userCreator = null;
        }
        if (fields.contains(Field.MEDIASHAS)) {
            this.mediasHas = null;
        }
        if (fields.contains(Field.SESSIONSHAS)) {
            this.sessionsHas = null;
        }
    }

    //assume leaf already pruned
    public static List<Topic> util_pruneTopicList(List<Topic> topicList, QueryFilterOfRelation relationFilter) {
        List<Topic> rt = topicList;
        if (topicList == null || relationFilter == null) {
            return rt;
        }
        Iterator<Topic> iterTopic = rt.iterator();
        while (iterTopic.hasNext()) {
            Topic topic = iterTopic.next();
            topic.sessionsHas = Session.util_pruneSessionList(topic.sessionsHas, relationFilter);
            if (topic.sessionsHas == null || topic.sessionsHas.size() == 0) {
                //filter topic itself using relationFilter
                String relationField = relationFilter.field;
                Long fieldValueToEval = null;
                if (Topic.Field.TIMECREATE.equals(relationField)) {
                    fieldValueToEval = topic.timeCreate;
                } else if (Topic.Field.TIMEUPDATE.equals(relationField)) {
                    fieldValueToEval = topic.timeUpdate;
                } else if (Topic.Field.TIMEEXPIRE.equals(relationField)) {
                    fieldValueToEval = topic.timeExpire;
                }
                if (fieldValueToEval != null) {
                    if (!relationFilter.eval(fieldValueToEval)) {
                        iterTopic.remove();
                    }
                }
            }
        }
        return rt;
    }

    public static Topic makeNullableTopicFromObject(Object entityObj) throws Exception {

        Topic entity = null;

        if (entityObj != null) {
            entity = new Topic(entityObj);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    public static List<Topic> makeTopicListFromEntityListOfObject(Object entityListOfObject) throws Exception {

        List<Topic> entityList = null;

        if (entityListOfObject == null) {
            return entityList;
        }

        List<Object> entityOfObjectList = null;
        //assume it is a jas, not handling list string
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
            String message = ListException.createExceptionMessage(null, "unsupported Object for topic list deserialization");
            throw new ListException(message);
        }

        if (entityOfObjectList == null) {
            return null;
        }

        entityList = new ArrayList<Topic>();
        for (int i=0; i< entityOfObjectList.size(); i++) {
            Object entityOfObject = entityOfObjectList.get(i);
            Topic entity = new Topic(entityOfObject);
            entityList.add(entity);
        }
        return entityList;
    }

    //used to deserialize list<list<Topic>> for query results
    @SuppressWarnings("unchecked")
    public static List<List<Topic>> makeTopicListListFromEntityListListOfObject(Object entityListListOfObject) throws Exception {

        List<List<Topic>> entityListList = null;

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
            String message = ListException.createExceptionMessage(null, "unsupported Object for topic list list deserialization");
            throw new ListException(message);
        }

        if (entityListOfObjectList == null) {
            return null;
        }

        entityListList = new ArrayList<List<Topic>>();
        for (int i=0; i< entityListOfObjectList.size(); i++) {
            Object entityListOfObject = entityListOfObjectList.get(i);
            List<Topic> entityList = Topic.makeTopicListFromEntityListOfObject(entityListOfObject);
            entityListList.add(entityList);
        }
        return entityListList;
    }

    public static List<Topic> util_pruneTopicRelationsForTopicList(List<Topic> topicList, QueryFilterOfRelation relationFilter) {
        List<Topic> rt = topicList;
        if (topicList == null || relationFilter == null) {
            return rt;
        }
        Iterator<Topic> iterTopic = rt.iterator();
        while (iterTopic.hasNext()) {
            Topic topic = iterTopic.next();
            topic.sessionsHas = Session.util_pruneSessionList(topic.sessionsHas, relationFilter);
        }
        return rt;
    }
}
