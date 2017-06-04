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

public class User extends Entity {

    public static final class Field extends Entity.Field {

        public static final String LIFECYCLE = "lifecycle";

        public static final String ALIASID = "aliasId";
        public static final String ALIASIDTYPE = "aliasIdType"; //this field is to identify whether the user registers as a native account or registers via third-party like facebook. The password/token validation processes are different
        public static final String GROUP = "group";
        public static final String NAME = "name";
        public static final String FIRSTNAME = "firstName";
        public static final String LASTNAME = "lastName";
        public static final String EMAIL = "email";
        public static final String CELL = "cell";
        public static final String SCORE = "score";

        public static final String AVATAR = "avatar";
        public static final String TYPE = "type";
        public static final String CATEGORY = "category";
        public static final String INTRO = "intro";

        public static final String TIMECREATE = "timeCreate";
        public static final String TIMEUPDATE = "timeUpdate";

        //TODO may separate region, country, street address etc. for fine-grained control
        public static final String SPACEPOSITIONX = "spacePositionX";
        public static final String SPACEPOSITIONY = "spacePositionY";
        public static final String SPACEPOSITIONADDRESS = "spacePositionAddress";

        public static final String TOPICSCREATED = "topicsCreated";
        public static final String TOPICSWATCHING = "topicsWatching";
        public static final String SESSIONSCREATED = "sessionsCreated";
        public static final String QUERIESCREATED = "queriesCreated";
        public static final String USERSFOLLOWEE = "usersFollowee";
        public static final String USERSFOLLOWER = "usersFollower";

        public static final String APPLICATIONDATA = "applicationData";
    }

    public static final class Class {
        public static final String value = "user";
    }

    public static final class Id {
        public static final String ANY = "_anyId_";
    }

    public static final class Type {
        public static final String INDIVIDUAL = "individual";
        public static final String OFFICIAL = "official";
    }

    public class View extends Entity.View {

    }

    public static final class State extends Entity.State {
        public static final String REGISTER = "register";
        public static final String MERGE = "merge";
        public static final String UPDATEPASSWORD = "updatePassword";
        public static final String UPDATETOKEN = "updateToken";//not used
    }

    public static final class AliasIdType {
        public static final String NATIVE = "native";
        public static final String FACEBOOK = "facebook";
        public static final String GOOGLE = "google";
    }

    public class Lifecycle {
        public static final String ACTIVE = "active";
        public static final String INACTIVE = "inactive";
        public static final String ABSENT = "absent";
    }

//	public class ApplicationData {
//		public static final String VALUE_NULL = "__nullValue__";//used to indicate removing the element from applicationData map//deprecated, use control field
//	}

    public String lifecycle;

    public String aliasId;
    public String aliasIdType;
    public String group;
    public String name;
    public String firstName;
    public String lastName;
    public String email;
    public String cell;
    public Double score;

    public Media avatar;
    public String type;// = Values.TYPE_PERSON;//person, organization, government//use class instead//no//if not in the specified types set to default value, still need to validate this field in event to make sure users specify proper type
    public String category;// = Values.CATEGORY_OTHERS;//entertainment, charity etc...
    public String intro;

    public Long timeCreate;
    public Long timeUpdate;

    public Double spacePositionX;//TODO can be nil then how to deal with it in spatial filter...
    public Double spacePositionY;
    public String spacePositionAddress;//if positionXY not specified but this specified, system should geenrate spacePositionX if needed...

    public List<Topic> topicsCreated;//represented as class externally
    public List<Topic> topicsWatching;
    public List<Session> sessionsCreated;//sessionsCreated instead of topicMarked
    public List<Query> queriesCreated;
    public List<User> usersFollowee;
    public List<User> usersFollower;

    public Map<String, String> applicationData; //extendable map to store any custom user data. To facilitate archive, restrict value type to be string. If the logic value needs to be boolean, number etc., application should handle type cast.

    public User() {
        super();
    }

    public User(String id, Boolean dummySignatureForInitById) {
        super(id, dummySignatureForInitById);
    }

    //introduced additional fields and has to call sub_validateFields explicitly
    public User(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                String lifecycle,
                String aliasId, String aliasIdType, String group, String name, String firstName, String lastName, String email, String cell, Double score,
                Media avatar, String type,  String category, String intro,
                Long timeCreate, Long timeUpdate,
                Double spacePositionX, Double spacePositionY, String spacePositionAddress,
                List<Topic> topicsCreated, List<Topic> topicsWatching, List<Session> sessionsCreated, List<Query> queriesCreated, List<User> usersFollowee, List<User> usersFollower,
                Map<String, String> applicationData) throws Exception {

        this.self_installFields(clazz, id, label, state, control, note,
                lifecycle,
                aliasId, aliasIdType, group, name, firstName, lastName, email, cell, score,
                avatar, type, category, intro,
                timeCreate, timeUpdate,
                spacePositionX, spacePositionY, spacePositionAddress,
                topicsCreated, topicsWatching, sessionsCreated, queriesCreated, usersFollowee, usersFollower, applicationData);

        this.sub_validateFields();
    }

    public User(Object entityOfObject) throws Exception {
        super(entityOfObject);
    }

    protected void self_installFields(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                                      String lifecycle,
                                      String aliasId, String aliasIdType, String group, String name, String firstName, String lastName, String email, String cell, Double score,
                                      Media avatar, String type,  String category, String intro,
                                      Long timeCreate, Long timeUpdate,
                                      Double spacePositionX, Double spacePositionY, String spacePositionAddress,
                                      List<Topic> topicsCreated, List<Topic> topicsWatching, List<Session> sessionsCreated, List<Query> queriesCreated, List<User> usersFollowee, List<User> usersFollower,
                                      Map<String, String> applicationData) {

        //init super fields//do not use super() because we need to call local validate after all variables inited
        super.self_installFields(clazz, id, label, state, control, note);

        //init local fields
        this.lifecycle = lifecycle;

        this.aliasId = aliasId;
        this.aliasIdType = aliasIdType;
        this.group = group;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cell = cell;
        this.score = score;

        this.avatar = avatar;
        this.type = type;
        this.category = category;
        this.intro = intro;

        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;

        this.spacePositionX = spacePositionX;
        this.spacePositionY = spacePositionY;
        this.spacePositionAddress = spacePositionAddress;

        this.topicsCreated = topicsCreated;
        this.topicsWatching = topicsWatching;
        this.sessionsCreated = sessionsCreated;
        this.queriesCreated = queriesCreated;
        this.usersFollowee = usersFollowee;
        this.usersFollower = usersFollower;

        this.applicationData = applicationData;
    }

    @Override
    protected void sub_decodeFields(JSONObjectWrapper entityJwo) throws Exception {
        super.sub_decodeFields(entityJwo);
        try {
            if (entityJwo == null) {
                return;
            } else {
                this.lifecycle = entityJwo.getString(Field.LIFECYCLE);
                this.aliasId = entityJwo.getString(Field.ALIASID);
                this.aliasIdType = entityJwo.getString(Field.ALIASIDTYPE);
                this.group = entityJwo.getString(Field.GROUP);
                this.name = entityJwo.getString(Field.NAME);
                this.firstName = entityJwo.getString(Field.FIRSTNAME);
                this.lastName = entityJwo.getString(Field.LASTNAME);
                this.email = entityJwo.getString(Field.EMAIL);
                this.cell = entityJwo.getString(Field.CELL);
                this.score = entityJwo.getDouble(Field.SCORE);

                if (entityJwo.has(Field.AVATAR)) {
                    this.avatar = new Media(entityJwo.get(Field.AVATAR));
                }

                this.type = entityJwo.getString(Field.TYPE);
                this.category = entityJwo.getString(Field.CATEGORY);
                this.intro = entityJwo.getString(Field.INTRO);
                this.timeCreate = entityJwo.getLong(Field.TIMECREATE);
                this.timeUpdate = entityJwo.getLong(Field.TIMEUPDATE);
                this.spacePositionX = entityJwo.getDouble(Field.SPACEPOSITIONX);
                this.spacePositionY = entityJwo.getDouble(Field.SPACEPOSITIONY);
                this.spacePositionAddress = entityJwo.getString(Field.SPACEPOSITIONADDRESS);

                this.topicsCreated = Topic.makeTopicListFromEntityListOfObject(entityJwo.get(Field.TOPICSCREATED));
                this.topicsWatching = Topic.makeTopicListFromEntityListOfObject(entityJwo.get(Field.TOPICSWATCHING));
                this.sessionsCreated = Session.makeSessionListFromEntityListOfObject(entityJwo.get(Field.SESSIONSCREATED));
                this.queriesCreated = Query.makeQueryListFromEntityListOfObject(entityJwo.get(Field.QUERIESCREATED));
                this.usersFollowee = User.makeUserListFromEntityListOfObject(entityJwo.get(Field.USERSFOLLOWEE));
                this.usersFollower = User.makeUserListFromEntityListOfObject(entityJwo.get(Field.USERSFOLLOWER));

                this.applicationData = entityJwo.getValueAsMap(Field.APPLICATIONDATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void sub_validateFields() throws Exception {
        if (this.state != null && !User.getStateValues().contains(this.state.toLowerCase())) {
            this.state = null;
        }
        //move this check to event
//		if (this.aliasIdType == null || !User.getAliasIdTypeValues().contains(this.aliasIdType.toLowerCase())) {
//
//		}
        //may allow custom types handled at cli side
//		if (this.type != null && !User.getTypeValues().contains(this.type.toLowerCase())) {
//			this.type = null;
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
            entityJoo.put(Field.ALIASID, this.aliasId);
            entityJoo.put(Field.ALIASIDTYPE, this.aliasIdType);
            entityJoo.put(Field.NAME, this.name);
            entityJoo.put(Field.FIRSTNAME, this.firstName);
            entityJoo.put(Field.LASTNAME, this.lastName);
            entityJoo.put(Field.GROUP, this.group);
            entityJoo.put(Field.EMAIL, this.email);
            entityJoo.put(Field.CELL, this.cell);
            entityJoo.put(Field.SCORE, this.score);
            entityJoo.put(Field.TYPE, this.type);
            entityJoo.put(Field.CATEGORY, this.category);
            entityJoo.put(Field.INTRO, this.intro);
            entityJoo.put(Field.TIMECREATE, this.timeCreate);
            entityJoo.put(Field.TIMEUPDATE, this.timeUpdate);
            entityJoo.put(Field.SPACEPOSITIONX, this.spacePositionX);
            entityJoo.put(Field.SPACEPOSITIONY, this.spacePositionY);
            entityJoo.put(Field.SPACEPOSITIONADDRESS, this.spacePositionAddress);
            if (this.avatar != null) {
                entityJoo.put(Field.AVATAR, this.avatar.toJSONObject());
            }

            //TODO apply view copy here or rely on appropriate user init
            entityJoo.put(Field.TOPICSCREATED, Topic.makeJSONArrayFromEntityList(this.topicsCreated));
            entityJoo.put(Field.TOPICSWATCHING, Topic.makeJSONArrayFromEntityList(this.topicsWatching));

            entityJoo.put(Field.SESSIONSCREATED, Session.makeJSONArrayFromEntityList(this.sessionsCreated));

            entityJoo.put(Field.QUERIESCREATED, Query.makeJSONArrayFromEntityList(this.queriesCreated));

            entityJoo.put(Field.USERSFOLLOWEE, User.makeJSONArrayFromEntityList(this.usersFollowee));
            entityJoo.put(Field.USERSFOLLOWER, User.makeJSONArrayFromEntityList(this.usersFollower));

            entityJoo.put(Field.APPLICATIONDATA, JSONHelper.transformMapToJSONObject(this.applicationData));

        } catch (Exception e) {
            e.printStackTrace();
            entityJoo = new JSONObject();
        }
        return entityJoo;
    }

    @Override
    public boolean equals(Object object) {
        boolean rt = false;
        if (object instanceof User) {
            User user = (User) object;
            if (this.id != null) {
                rt = this.id.equals(user.id);
            } else if (user.id == null) {
                rt = true;
            }
        }
        return rt;
    }

    public boolean isRegisteredUser() {
        return !(this.aliasId == null || this.aliasIdType == null);
    }

    //TODO treat group of user as a high level user...//not used but keep
    public static User makePredefinedUserAnyUser() {
        return new User(Id.ANY, true);
    }

    public static User makeNullableUserFromObject(Object entityObj) throws Exception {

        User entity = null;

        if (entityObj != null) {
            entity = new User(entityObj);
        }
        return entity;
    }

    //this function is useful at server side to test the uniqueness of the aliasId (for now native aliasId only supports email address. When comparing email aliasId it should be considered as case insensitive, but dots are now not stripped.)
    public static String aliasIdGetAliasIdNormalized(String aliasId, String aliasIdType) {
        if (aliasId == null || aliasIdType == null) {
            return aliasId;
        } else if (AliasIdType.NATIVE.equals(aliasIdType)) {
            return aliasId.toLowerCase();
        } else {
            return aliasId;
        }
    }
    public String aliasIdGetAliasIdNormalized() {
        return User.aliasIdGetAliasIdNormalized(this.aliasId, this.aliasIdType);
    }

    public static List<String> getStateValues() {
        List<String> stateValues = new ArrayList<String>();
        stateValues.add(State.CREATE.toLowerCase());
        stateValues.add(State.UPDATE.toLowerCase());
        stateValues.add(State.DELETE.toLowerCase());
        stateValues.add(State.REGISTER.toLowerCase());
        stateValues.add(State.MERGE.toLowerCase());
        stateValues.add(State.UPDATEPASSWORD.toLowerCase());
        stateValues.add(State.UPDATETOKEN.toLowerCase());
        return stateValues;
    }

    //	@Override
    public static List<String> getViewValues() {
        List<String> viewValues = new ArrayList<String>();
        viewValues.add(View.ID.toLowerCase());
        viewValues.add(View.ABSTRACT.toLowerCase());
        viewValues.add(View.INFO.toLowerCase());
        viewValues.add(View.ORIGINAL.toLowerCase());
        return viewValues;
    }

    public static List<String> getTypeValues() {
        List<String> typeValues = new ArrayList<String>();
        typeValues.add(Type.INDIVIDUAL.toLowerCase());
        typeValues.add(Type.OFFICIAL.toLowerCase());
        return typeValues;
    }

    public static List<String> getAliasIdTypeValues() {
        List<String> aliasIdTypeValues = new ArrayList<String>();
        aliasIdTypeValues.add(AliasIdType.NATIVE.toLowerCase());
        aliasIdTypeValues.add(AliasIdType.FACEBOOK.toLowerCase());
        aliasIdTypeValues.add(AliasIdType.GOOGLE.toLowerCase());
        return aliasIdTypeValues;
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

        fieldKeys.add(User.Field.CLASS);
        fieldKeys.add(User.Field.ID);
        fieldKeys.add(User.Field.LABEL);
        fieldKeys.add(User.Field.STATE);
        fieldKeys.add(User.Field.CONTROL);
        fieldKeys.add(User.Field.NOTE);

        fieldKeys.add(User.Field.LIFECYCLE);

        fieldKeys.add(User.Field.ALIASID);
        fieldKeys.add(User.Field.ALIASIDTYPE);
        fieldKeys.add(User.Field.GROUP);
        fieldKeys.add(User.Field.NAME);
        fieldKeys.add(User.Field.FIRSTNAME);
        fieldKeys.add(User.Field.LASTNAME);
        fieldKeys.add(User.Field.EMAIL);
        fieldKeys.add(User.Field.CELL);
        fieldKeys.add(User.Field.SCORE);

        fieldKeys.add(User.Field.AVATAR);
        fieldKeys.add(User.Field.TYPE);
        fieldKeys.add(User.Field.CATEGORY);
        fieldKeys.add(User.Field.INTRO);

        fieldKeys.add(User.Field.TIMECREATE);
        fieldKeys.add(User.Field.TIMEUPDATE);

        fieldKeys.add(User.Field.SPACEPOSITIONX);
        fieldKeys.add(User.Field.SPACEPOSITIONY);
        fieldKeys.add(User.Field.SPACEPOSITIONADDRESS);

        fieldKeys.add(User.Field.TOPICSCREATED);
        fieldKeys.add(User.Field.TOPICSWATCHING);
        fieldKeys.add(User.Field.SESSIONSCREATED);
        fieldKeys.add(User.Field.QUERIESCREATED);
        fieldKeys.add(User.Field.USERSFOLLOWEE);
        fieldKeys.add(User.Field.USERSFOLLOWER);

        fieldKeys.add(User.Field.APPLICATIONDATA);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForView(String view) {
        if (User.View.ID.equals(view)) {
            return User.getFieldKeysForViewId();
        }
        if (User.View.ABSTRACT.equals(view)) {
            return User.getFieldKeysForViewAbstract();
        }
        if (User.View.INFO.equals(view)) {
            return User.getFieldKeysForViewInfo();
        }
        if (User.View.ORIGINAL.equals(view)) {
            return User.getFieldKeysForViewOriginal();
        }
        return new ArrayList<String>();
    }

    public static List<String> getFieldKeysForViewId() {
        List<String> fieldKeys = new ArrayList<String>();
        fieldKeys.add(User.Field.ID);
        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewAbstract() {

        List<String> fieldKeys = new ArrayList<String>();

        //        fieldKeys.add(User.Field.CLASS);
        fieldKeys.add(User.Field.ID);
        //        fieldKeys.add(User.Field.LABEL);
        //        fieldKeys.add(User.Field.STATE);
        fieldKeys.add(User.Field.CONTROL);
        //        fieldKeys.add(User.Field.NOTE);

        fieldKeys.add(User.Field.LIFECYCLE);

        //        fieldKeys.add(User.Field.ALIASID);
        //        fieldKeys.add(User.Field.ALIASIDTYPE);
        fieldKeys.add(User.Field.GROUP);
        fieldKeys.add(User.Field.NAME);
        fieldKeys.add(User.Field.FIRSTNAME);
        fieldKeys.add(User.Field.LASTNAME);
        //        fieldKeys.add(User.Field.EMAIL);
        //        fieldKeys.add(User.Field.CELL);
        fieldKeys.add(User.Field.SCORE);

        fieldKeys.add(User.Field.AVATAR);
        fieldKeys.add(User.Field.TYPE);
        fieldKeys.add(User.Field.CATEGORY);
        fieldKeys.add(User.Field.INTRO);

        fieldKeys.add(User.Field.TIMECREATE);
        fieldKeys.add(User.Field.TIMEUPDATE);

        //        fieldKeys.add(User.Field.SPACEPOSITIONX);
        //        fieldKeys.add(User.Field.SPACEPOSITIONY);
        //        fieldKeys.add(User.Field.SPACESTREETADDRESS);

        fieldKeys.add(User.Field.TOPICSCREATED);
        //        fieldKeys.add(User.Field.TOPICSWATCHING);
        //        fieldKeys.add(User.Field.SESSIONSCREATED);
        //        fieldKeys.add(User.Field.QUERIESCREATED);
        //        fieldKeys.add(User.Field.USERSFOLLOWEE);
        //        fieldKeys.add(User.Field.USERSFOLLOWER);

        //        fieldKeys.add(User.Field.APPLICATIONDATA);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewInfo() {

        List<String> fieldKeys = new ArrayList<String>();

        //        fieldKeys.add(User.Field.CLASS);
        fieldKeys.add(User.Field.ID);
        //        fieldKeys.add(User.Field.LABEL);
        //        fieldKeys.add(User.Field.STATE);
        fieldKeys.add(User.Field.CONTROL);
        //        fieldKeys.add(User.Field.NOTE);

        fieldKeys.add(User.Field.LIFECYCLE);

        //        fieldKeys.add(User.Field.ALIASID);
        //        fieldKeys.add(User.Field.ALIASIDTYPE);
        fieldKeys.add(User.Field.GROUP);
        fieldKeys.add(User.Field.NAME);
        fieldKeys.add(User.Field.FIRSTNAME);
        fieldKeys.add(User.Field.LASTNAME);
        //        fieldKeys.add(User.Field.EMAIL);
        //        fieldKeys.add(User.Field.CELL);
        fieldKeys.add(User.Field.SCORE);

        fieldKeys.add(User.Field.AVATAR);
        fieldKeys.add(User.Field.TYPE);
        fieldKeys.add(User.Field.CATEGORY);
        fieldKeys.add(User.Field.INTRO);

        fieldKeys.add(User.Field.TIMECREATE);
        fieldKeys.add(User.Field.TIMEUPDATE);

        //        fieldKeys.add(User.Field.SPACEPOSITIONX);
        //        fieldKeys.add(User.Field.SPACEPOSITIONY);
        //        fieldKeys.add(User.Field.SPACESTREETADDRESS);

        fieldKeys.add(User.Field.TOPICSCREATED);
        //        fieldKeys.add(User.Field.TOPICSWATCHING);
        //        fieldKeys.add(User.Field.SESSIONSCREATED);
        //        fieldKeys.add(User.Field.QUERIESCREATED);
        //        fieldKeys.add(User.Field.USERSFOLLOWEE);
        //        fieldKeys.add(User.Field.USERSFOLLOWER);

        //        fieldKeys.add(User.Field.APPLICATIONDATA);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewOriginal() {
        List<String> fieldKeys = new ArrayList<String>();

        fieldKeys.add(User.Field.CLASS);
        fieldKeys.add(User.Field.ID);
        fieldKeys.add(User.Field.LABEL);
        fieldKeys.add(User.Field.STATE);
        fieldKeys.add(User.Field.CONTROL);
        fieldKeys.add(User.Field.NOTE);

        fieldKeys.add(User.Field.LIFECYCLE);

        fieldKeys.add(User.Field.ALIASID);
        fieldKeys.add(User.Field.ALIASIDTYPE);
        fieldKeys.add(User.Field.GROUP);
        fieldKeys.add(User.Field.NAME);
        fieldKeys.add(User.Field.FIRSTNAME);
        fieldKeys.add(User.Field.LASTNAME);
        fieldKeys.add(User.Field.EMAIL);
        fieldKeys.add(User.Field.CELL);
        fieldKeys.add(User.Field.SCORE);

        fieldKeys.add(User.Field.AVATAR);
        fieldKeys.add(User.Field.TYPE);
        fieldKeys.add(User.Field.CATEGORY);
        fieldKeys.add(User.Field.INTRO);

        fieldKeys.add(User.Field.TIMECREATE);
        fieldKeys.add(User.Field.TIMEUPDATE);

        fieldKeys.add(User.Field.SPACEPOSITIONX);
        fieldKeys.add(User.Field.SPACEPOSITIONY);
        fieldKeys.add(User.Field.SPACEPOSITIONADDRESS);

        fieldKeys.add(User.Field.TOPICSCREATED);
        fieldKeys.add(User.Field.TOPICSWATCHING);
        fieldKeys.add(User.Field.SESSIONSCREATED);
        fieldKeys.add(User.Field.QUERIESCREATED);
        fieldKeys.add(User.Field.USERSFOLLOWEE);
        fieldKeys.add(User.Field.USERSFOLLOWER);

        fieldKeys.add(User.Field.APPLICATIONDATA);

        return fieldKeys;
    }

    @Override
    public User makeCopyOfFields(List<String> fieldKeys) {
        List<String> fieldKeysForEntityCopy = User.getFieldKeys();
        if (fieldKeys != null) {
            fieldKeysForEntityCopy = fieldKeys;
        }

        User entityCopy = new User();

        if (fieldKeysForEntityCopy.contains(User.Field.CLASS)) {
            entityCopy.clazz = this.clazz;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.ID)) {
            entityCopy.id = this.id;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.LABEL)) {
            entityCopy.label = this.label;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.STATE)) {
            entityCopy.state = this.state;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.CONTROL)) {
            entityCopy.control = this.control;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.NOTE)) {
            entityCopy.note = this.note;
        }

        if (fieldKeysForEntityCopy.contains(User.Field.LIFECYCLE)) {
            entityCopy.lifecycle = this.lifecycle;
        }

        if (fieldKeysForEntityCopy.contains(User.Field.ALIASID)) {
            entityCopy.aliasId = this.aliasId;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.ALIASIDTYPE)) {
            entityCopy.aliasIdType = this.aliasIdType;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.GROUP)) {
            entityCopy.group = this.group;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.NAME)) {
            entityCopy.name = this.name;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.FIRSTNAME)) {
            entityCopy.firstName = this.firstName;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.LASTNAME)) {
            entityCopy.lastName = this.lastName;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.EMAIL)) {
            entityCopy.email = this.email;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.CELL)) {
            entityCopy.cell = this.cell;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.SCORE)) {
            entityCopy.score = this.score;
        }

        if (fieldKeysForEntityCopy.contains(User.Field.AVATAR)) {
            entityCopy.avatar = this.avatar;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.TYPE)) {
            entityCopy.type = this.type;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.CATEGORY)) {
            entityCopy.category = this.category;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.INTRO)) {
            entityCopy.intro = this.intro;
        }

        if (fieldKeysForEntityCopy.contains(User.Field.TIMECREATE)) {
            entityCopy.timeCreate = this.timeCreate;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.TIMEUPDATE)) {
            entityCopy.timeUpdate = this.timeUpdate;
        }

        if (fieldKeysForEntityCopy.contains(User.Field.SPACEPOSITIONX)) {
            entityCopy.spacePositionX = this.spacePositionX;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.SPACEPOSITIONY)) {
            entityCopy.spacePositionY = this.spacePositionY;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.SPACEPOSITIONADDRESS)) {
            entityCopy.spacePositionAddress = this.spacePositionAddress;
        }

        if (fieldKeysForEntityCopy.contains(User.Field.TOPICSCREATED)) {
            entityCopy.topicsCreated = this.topicsCreated;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.TOPICSWATCHING)) {
            entityCopy.topicsWatching = this.topicsWatching;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.SESSIONSCREATED)) {
            entityCopy.sessionsCreated = this.sessionsCreated;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.QUERIESCREATED)) {
            entityCopy.queriesCreated = this.queriesCreated;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.USERSFOLLOWEE)) {
            entityCopy.usersFollowee = this.usersFollowee;
        }
        if (fieldKeysForEntityCopy.contains(User.Field.USERSFOLLOWER)) {
            entityCopy.usersFollower = this.usersFollower;
        }

        if (fieldKeysForEntityCopy.contains(User.Field.APPLICATIONDATA)) {
            entityCopy.applicationData = this.applicationData;
        }

        return entityCopy;
    }

    @Override
    public User makeCopyOfView(String view) {
        if (User.View.ID.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewId();
        }
        if (User.View.ABSTRACT.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewAbstract();
        }
        if (User.View.INFO.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewInfo();
        }
        if (User.View.ORIGINAL.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewOriginal();
        }
        return null;
    }

    @Override
    public User makeCopyOfViewId() {
        return this.makeCopyOfFields(User.getFieldKeysForViewId());
    }

    @Override
    public User makeCopyOfViewAbstract() {
        return this.makeCopyOfFields(User.getFieldKeysForViewAbstract());
    }

    @Override
    public User makeCopyOfViewInfo() {
        return this.makeCopyOfFields(User.getFieldKeysForViewInfo());
    }

    @Override
    public User makeCopyOfViewOriginal() {
        return this.makeCopyOfFields(User.getFieldKeysForViewOriginal());
    }

    @Override
    public void madeToView(String view) {
        if (User.View.ID.equals(view)) {
            this.madeToViewId();
        }
        if (User.View.ABSTRACT.equals(view)) {
            this.madeToViewAbstract();
        }
        if (User.View.INFO.equals(view)) {
            this.madeToViewInfo();
        }
    }

    @Override
    public void madeToViewId() {
        List<String> fieldKeysToKeep = User.getFieldKeysForViewId();
        List<String> fieldKeysToSetNil = User.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewAbstract() {
        List<String> fieldKeysToKeep = User.getFieldKeysForViewAbstract();
        List<String> fieldKeysToSetNil = User.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewInfo() {
        List<String> fieldKeysToKeep = User.getFieldKeysForViewInfo();
        List<String> fieldKeysToSetNil = User.getFieldKeysForViewOriginal();
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
        if (fields.contains(Field.ALIASID)) {
            this.aliasId = null;
        }
        if (fields.contains(Field.ALIASIDTYPE)) {
            this.aliasIdType = null;
        }
        if (fields.contains(Field.GROUP)) {
            this.group = null;
        }
        if (fields.contains(Field.NAME)) {
            this.name = null;
        }
        if (fields.contains(Field.FIRSTNAME)) {
            this.firstName = null;
        }
        if (fields.contains(Field.LASTNAME)) {
            this.lastName = null;
        }
        if (fields.contains(Field.EMAIL)) {
            this.email = null;
        }
        if (fields.contains(Field.CELL)) {
            this.cell = null;
        }
        if (fields.contains(Field.SCORE)) {
            this.score = null;
        }
        if (fields.contains(Field.AVATAR)) {
            this.avatar = null;
        }
        if (fields.contains(Field.TYPE)) {
            this.type = null;
        }
        if (fields.contains(Field.CATEGORY)) {
            this.category = null;
        }
        if (fields.contains(Field.INTRO)) {
            this.intro = null;
        }
        if (fields.contains(Field.TIMECREATE)) {
            this.timeCreate = null;
        }
        if (fields.contains(Field.TIMEUPDATE)) {
            this.timeUpdate = null;
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
        if (fields.contains(Field.TOPICSCREATED)) {
            this.topicsCreated = null;
        }
        if (fields.contains(Field.TOPICSWATCHING)) {
            this.topicsWatching = null;
        }
        if (fields.contains(Field.SESSIONSCREATED)) {
            this.sessionsCreated = null;
        }
        if (fields.contains(Field.QUERIESCREATED)) {
            this.queriesCreated = null;
        }
        if (fields.contains(Field.USERSFOLLOWEE)) {
            this.usersFollowee = null;
        }
        if (fields.contains(Field.USERSFOLLOWER)) {
            this.usersFollower = null;
        }
        if (fields.contains(Field.APPLICATIONDATA)) {
            this.applicationData = null;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<User> makeUserListFromEntityListOfObject(Object entityListOfObject) throws Exception {

        List<User> entityList = null;

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
            String message = ListException.createExceptionMessage(null, "unsupported object for user list deserialization");
            throw new ListException(message);
        }

        if (entityOfObjectList == null) {
            return null;
        }

        entityList = new ArrayList<User>();
        for (int i=0; i< entityOfObjectList.size(); i++) {
            Object entityOfObject = entityOfObjectList.get(i);
            User entity = new User(entityOfObject);
            entityList.add(entity);
        }
        return entityList;
    }

    @SuppressWarnings("unchecked")
    public static List<List<User>> makeUserListListFromEntityListListOfObject(Object entityListListOfObject) throws Exception {

        List<List<User>> entityListList = null;

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
            String message = ListException.createExceptionMessage(null, "unsupported object for user list list deserialization");
            throw new ListException(message);
        }

        if (entityListOfObjectList == null) {
            return null;
        }

        entityListList = new ArrayList<List<User>>();
        for (int i=0; i< entityListOfObjectList.size(); i++) {
            Object entityListOfObject = entityListOfObjectList.get(i);
            List<User> entityList = User.makeUserListFromEntityListOfObject(entityListOfObject);
            entityListList.add(entityList);
        }
        return entityListList;
    }

    //assume leaf already pruned
    public static List<User> util_pruneUserList(List<User> userList, QueryFilterOfRelation relationFilter) {
        List<User> rt = userList;
        if (userList == null || relationFilter == null) {
            return rt;
        }
        Iterator<User> iterUser = rt.iterator();
        while (iterUser.hasNext()) {
            User user = iterUser.next();
            user.topicsCreated = Topic.util_pruneTopicList(user.topicsCreated, relationFilter);
            user.topicsWatching = Topic.util_pruneTopicList(user.topicsWatching, relationFilter);
            user.sessionsCreated = Session.util_pruneSessionList(user.sessionsCreated, relationFilter);
            user.queriesCreated = Query.util_pruneQueryList(user.queriesCreated, relationFilter);
            user.usersFollowee = User.util_pruneUserList(user.usersFollowee, relationFilter);
            user.usersFollower = User.util_pruneUserList(user.usersFollower, relationFilter);
            if ((user.topicsCreated == null || user.topicsCreated.size() == 0)
                    && (user.topicsWatching == null || user.topicsWatching.size() == 0)
                    && (user.sessionsCreated == null || user.sessionsCreated.size() == 0)
                    && (user.queriesCreated == null || user.queriesCreated.size() == 0)
                    && (user.usersFollowee == null || user.usersFollowee.size() == 0)
                    && (user.usersFollower == null || user.usersFollower.size() == 0)) {
                //filter user itself using relationFilter
                String relationField = relationFilter.field;
                Long fieldValueToEval = null;
                if (User.Field.TIMECREATE.equals(relationField)) {
                    fieldValueToEval = user.timeCreate;
                } else if (User.Field.TIMEUPDATE.equals(relationField)) {
                    fieldValueToEval = user.timeUpdate;
                }
                if (fieldValueToEval != null) {
                    if (!relationFilter.eval(fieldValueToEval)) {
                        iterUser.remove();
                    }
                }
            }
        }
        return rt;
    }

    public static List<User> util_pruneUserRelationsForUserList(List<User> userList, QueryFilterOfRelation relationFilter) {
        List<User> rt = userList;
        if (userList == null || relationFilter == null) {
            return rt;
        }
        Iterator<User> iterUser = rt.iterator();
        while (iterUser.hasNext()) {
            User user = iterUser.next();
            user.topicsCreated = Topic.util_pruneTopicList(user.topicsCreated, relationFilter);
            user.topicsWatching = Topic.util_pruneTopicList(user.topicsWatching, relationFilter);
            user.sessionsCreated = Session.util_pruneSessionList(user.sessionsCreated, relationFilter);
            user.queriesCreated = Query.util_pruneQueryList(user.queriesCreated, relationFilter);
            user.usersFollowee = User.util_pruneUserList(user.usersFollowee, relationFilter);
            user.usersFollower = User.util_pruneUserList(user.usersFollower, relationFilter);
        }
        return rt;
    }
}
