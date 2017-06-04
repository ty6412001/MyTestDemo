package com.fsd.mvvmlight.freeseeds.entity;


import com.fsd.mvvmlight.freeseeds.entity.query.QueryComponent;
import com.fsd.mvvmlight.freeseeds.entity.query.QueryFilterOfField;
import com.fsd.mvvmlight.freeseeds.entity.query.QueryFilterOfRelation;
import com.fsd.mvvmlight.freeseeds.entity.query.QueryFilterOfSpace;
import com.fsd.mvvmlight.freeseeds.entity.query.QueryFilterOfTime;
import com.fsd.mvvmlight.freeseeds.entity.query.QuerySort;
import com.fsd.mvvmlight.freeseeds.exception.ListException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import library.helper.JSONHelper;
import library.helper.JSONHelper.JSONObjectWrapper;



public class Query extends Entity {

    public static final class Field extends Entity.Field {

        public static final String LIFECYCLE = "lifecycle";

        public static final String NAME = "name";

        public static final String TIMECREATE = "timeCreate";
        public static final String TIMEUPDATE = "timeUpdate";
        public static final String TIMEEXPIRE = "timeExpire";

        public static final String TARGETCLASS = "targetClass";
        public static final String FIELDFILTERS = "fieldFilters";
        public static final String SPACEFILTER = "spaceFilter";
        public static final String TIMEFILTER = "timeFilter";
        public static final String RELATIONFILTER = "relationFilter";
        public static final String SORT = "sort";
        public static final String START = "start";
        public static final String ROWS = "rows";
        public static final String VIEW = "view";//this view is for the query result entities but not for query itself
        //not used for now - field specific view; too complex
        public static final String FIELDVIEWMAP = "fieldViewMap";

        public static final String USERCREATOR = Role.USERCREATOR;
    }

    public String lifecycle;

    public String name;

    public Long timeCreate;
    public Long timeUpdate;
    public Long timeExpire;

    public String targetClass;
    public List<QueryFilterOfField> fieldFilters;
    public QueryFilterOfSpace spaceFilter;
    public QueryFilterOfTime timeFilter;
    public QueryFilterOfRelation relationFilter;
    public QuerySort sort;
    public Integer start;
    public Integer rows;
    public String view;
    public Map<String, String> fieldViewMap;//query for id, abstract, original info for a field

    public User userCreator;

    public static final class Class {
        public static final String value = "query";
    }

    public class Lifecycle {
        public static final String ACTIVE = "active";
        public static final String INACTIVE = "inactive";
        public static final String ABSENT = "absent";
    }

    public static final class Role {
        public static final String USERCREATOR = "userCreator";
        public static final String USERSUNKNOWN = "usersUnknown";
    }

    public Query() {
        super();
    }

    public Query(String id, Boolean dummySignatureForInitById) {
        super(id, dummySignatureForInitById);
    }

    public Query(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                 String lifecycle,
                 String name,
                 Long timeCreate, Long timeUpdate, Long timeExpire,
                 String targetClass, List<QueryFilterOfField> fieldFilters, QueryFilterOfSpace spaceFilter, QueryFilterOfTime timeFilter, QueryFilterOfRelation relationFilter, QuerySort sort, Integer start, Integer rows, String view, Map<String, String> fieldViewMap,
                 User userCreator) throws Exception {
        this.self_installFields(clazz, id, label, state, control, note, lifecycle, name, timeCreate, timeUpdate, timeExpire, targetClass, fieldFilters, spaceFilter, timeFilter, relationFilter, sort, start, rows, view, fieldViewMap, userCreator);
        this.sub_validateFields();
    }

    public Query(Object entityOfObject) throws Exception {
        super(entityOfObject);
    }

    protected void self_installFields(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                                      String lifecycle,
                                      String name,
                                      Long timeCreate, Long timeUpdate, Long timeExpire,
                                      String targetClass, List<QueryFilterOfField> fieldFilters, QueryFilterOfSpace spaceFilter, QueryFilterOfTime timeFilter, QueryFilterOfRelation relationFilter, QuerySort sort, Integer start, Integer rows, String view, Map<String, String> fieldViewMap,
                                      User userCreator) throws Exception {

        super.self_installFields(clazz, id, label, state, control, note);

        this.lifecycle = lifecycle;
        this.name = name;
        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;
        this.timeExpire = timeExpire;
        this.targetClass = targetClass;
        this.fieldFilters = fieldFilters;
        this.spaceFilter = spaceFilter;
        this.timeFilter = timeFilter;
        this.relationFilter = relationFilter;
        this.sort = sort;
        this.start = start;
        this.rows = rows;
        this.view = view;
        this.fieldViewMap = fieldViewMap;
        this.userCreator = userCreator;
    }

    protected void sub_decodeFields(JSONObjectWrapper entityJwo) throws Exception {
        super.sub_decodeFields(entityJwo);
        try {
            if (entityJwo == null) {
                return;
            } else {
                this.lifecycle = entityJwo.getString(Field.LIFECYCLE);
                this.name = entityJwo.getString(Field.NAME);

                this.timeCreate = entityJwo.getLong(Field.TIMECREATE);
                this.timeUpdate = entityJwo.getLong(Field.TIMEUPDATE);
                this.timeExpire = entityJwo.getLong(Field.TIMEEXPIRE);

                this.targetClass = entityJwo.getString(Field.TARGETCLASS);

                List<Object> fieldFilterOfObjectList = entityJwo.getValueAsList(Field.FIELDFILTERS);
                this.fieldFilters = QueryFilterOfField.makeFilterListFromQueryComponentListOfObject(fieldFilterOfObjectList);

                if (entityJwo.has(Field.SPACEFILTER)) {
                    this.spaceFilter = new QueryFilterOfSpace(entityJwo.get(Field.SPACEFILTER));
                }

                if (entityJwo.has(Field.TIMEFILTER)) {
                    this.timeFilter = new QueryFilterOfTime(entityJwo.get(Field.TIMEFILTER));
                }

                if (entityJwo.has(Field.RELATIONFILTER)) {
                    this.relationFilter = new QueryFilterOfRelation(entityJwo.get(Field.RELATIONFILTER));
                }

                if (entityJwo.has(Field.SORT)) {
                    this.sort = new QuerySort(entityJwo.get(Field.SORT));
                }

                this.start = entityJwo.getInt(Field.START);
                this.rows = entityJwo.getInt(Field.ROWS);

                this.view = entityJwo.getString(Field.VIEW);

                this.fieldViewMap = entityJwo.getValueAsMap(Field.FIELDVIEWMAP);

                if (entityJwo.has(Field.USERCREATOR)) {
                    this.userCreator = new User(entityJwo.get(Field.USERCREATOR));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void sub_validateFields() throws Exception {
        if (this.state != null && !Query.getStateValues().contains(this.state.toLowerCase())) {
            this.state = null;
        }
//		if (this.lifecycle != null && !DomainQuery.getLifecycleValues().contains(this.lifecycle.toLowerCase())) {
//			this.lifecycle = null;
//		}
        //moved to EventVARFreeRetrieveB. Should not concern application level requirements but allow fields not been specified here
//		if (this.entityClass == null && (Query.State.UPDATE != this.state || Query.State.DELETE != this.state)) {
//			String exceptionMessage = ListException.createExceptionMessage(Error.Code.VALIDATE_DOMAINQUERY_MISSINGFIELD_ENTITYCLASS, Error.Description.VALIDATE_DOMAINQUERY_MISSINGFIELD_ENTITYCLASS);
//			throw new ListException(exceptionMessage);
//		}
    }

    @Override
    public JSONObject toJSONObject() {//for json, probably return a copy instead, immutable, sychronize on access method if needed
        JSONObject entityJoo = super.toJSONObject();
        try {
            if (entityJoo == null) {
                entityJoo = new JSONObject();
            }

            entityJoo.put(Field.LIFECYCLE, this.lifecycle);
            entityJoo.put(Field.NAME, this.name);

            entityJoo.put(Field.TIMECREATE, this.timeCreate);
            entityJoo.put(Field.TIMEUPDATE, this.timeUpdate);
            entityJoo.put(Field.TIMEEXPIRE, this.timeExpire);

            entityJoo.put(Field.TARGETCLASS, this.targetClass);

            JSONArray fieldFiltersJao = QueryComponent.makeJSONArrayFromComponentList(this.fieldFilters);
            entityJoo.put(Field.FIELDFILTERS, fieldFiltersJao);

            if (this.spaceFilter != null) {
                entityJoo.put(Field.SPACEFILTER, this.spaceFilter.toJSONObject());
            }

            if (this.timeFilter != null) {
                entityJoo.put(Field.TIMEFILTER, this.timeFilter.toJSONObject());
            }

            if (this.relationFilter != null) {
                entityJoo.put(Field.RELATIONFILTER, this.relationFilter.toJSONObject());
            }

            if (this.sort != null) {
                entityJoo.put(Field.SORT, this.sort.toJSONObject());
            }

            entityJoo.put(Field.START, this.start);
            entityJoo.put(Field.ROWS, this.rows);

            entityJoo.put(Field.VIEW, this.view);

            JSONObject fieldViewMapJoo = JSONHelper.transformMapToJSONObject(this.fieldViewMap);
            entityJoo.put(Field.FIELDVIEWMAP, fieldViewMapJoo);

            if (this.userCreator!=null) {
                entityJoo.put(Field.USERCREATOR, this.userCreator.toJSONObject());
            }

//    		if (entityJoo.length() == 0) {
//    			entityJoo = null;
//			}

        } catch (Exception e) {
            e.printStackTrace();
            entityJoo = new JSONObject();
        }
        return entityJoo;
    }

    @Override
    public boolean equals(Object object) {
        boolean rt = false;
        if (object instanceof Query) {
            Query query = (Query) object;
            if (this.id != null) {
                rt = this.id.equals(query.id);
            } else if (query.id == null) {
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

        fieldKeys.add(Query.Field.CLASS);
        fieldKeys.add(Query.Field.ID);
        fieldKeys.add(Query.Field.LABEL);
        fieldKeys.add(Query.Field.STATE);
        fieldKeys.add(Query.Field.CONTROL);
        fieldKeys.add(Query.Field.NOTE);

        fieldKeys.add(Query.Field.LIFECYCLE);

        fieldKeys.add(Query.Field.NAME);

        fieldKeys.add(Query.Field.TIMECREATE);
        fieldKeys.add(Query.Field.TIMEUPDATE);
        fieldKeys.add(Query.Field.TIMEEXPIRE);

        fieldKeys.add(Query.Field.TARGETCLASS);
        fieldKeys.add(Query.Field.FIELDFILTERS);
        fieldKeys.add(Query.Field.SPACEFILTER);
        fieldKeys.add(Query.Field.TIMEFILTER);
        fieldKeys.add(Query.Field.RELATIONFILTER);
        fieldKeys.add(Query.Field.SORT);
        fieldKeys.add(Query.Field.START);
        fieldKeys.add(Query.Field.ROWS);
        fieldKeys.add(Query.Field.VIEW);
        fieldKeys.add(Query.Field.FIELDVIEWMAP);

        fieldKeys.add(Query.Field.USERCREATOR);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForView(String view) {
        if (Query.View.ID.equals(view)) {
            return Query.getFieldKeysForViewId();
        }
        if (Query.View.ABSTRACT.equals(view)) {
            return Query.getFieldKeysForViewAbstract();
        }
        if (Query.View.INFO.equals(view)) {
            return Query.getFieldKeysForViewInfo();
        }
        if (Query.View.ORIGINAL.equals(view)) {
            return Query.getFieldKeysForViewOriginal();
        }
        return new ArrayList<String>();
    }

    public static List<String> getFieldKeysForViewId() {
        List<String> fieldKeys = new ArrayList<String>();
        fieldKeys.add(Query.Field.ID);
        return fieldKeys;
    }

    //Note: different from other entities, query's abstract view is different from info view. It only contains id field and fields necessary to execute the query
    public static List<String> getFieldKeysForViewAbstract() {
        List<String> fieldKeys = new ArrayList<String>();

//    	fieldKeys.add(Query.Field.CLASS);
        fieldKeys.add(Query.Field.ID);
//        fieldKeys.add(Query.Field.LABEL);
//        fieldKeys.add(Query.Field.STATE);
//        fieldKeys.add(Query.Field.CONTROL);
//        fieldKeys.add(Query.Field.NOTE);

//        fieldKeys.add(Query.Field.LIFECYCLE);

//        fieldKeys.add(Query.Field.NAME);

//        fieldKeys.add(Query.Field.TIMECREATE);
//        fieldKeys.add(Query.Field.TIMEUPDATE);
//        fieldKeys.add(Query.Field.TIMEEXPIRE);

        fieldKeys.add(Query.Field.TARGETCLASS);
        fieldKeys.add(Query.Field.FIELDFILTERS);
        fieldKeys.add(Query.Field.SPACEFILTER);
        fieldKeys.add(Query.Field.TIMEFILTER);
        fieldKeys.add(Query.Field.RELATIONFILTER);
        fieldKeys.add(Query.Field.SORT);
        fieldKeys.add(Query.Field.START);
        fieldKeys.add(Query.Field.ROWS);
        fieldKeys.add(Query.Field.VIEW);
        fieldKeys.add(Query.Field.FIELDVIEWMAP);

//        fieldKeys.add(Query.Field.USERCREATOR);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewInfo() {
        List<String> fieldKeys = new ArrayList<String>();

//    	fieldKeys.add(Query.Field.CLASS);
        fieldKeys.add(Query.Field.ID);
//        fieldKeys.add(Query.Field.LABEL);
//        fieldKeys.add(Query.Field.STATE);
        fieldKeys.add(Query.Field.CONTROL);
//        fieldKeys.add(Query.Field.NOTE);

        fieldKeys.add(Query.Field.LIFECYCLE);

        fieldKeys.add(Query.Field.NAME);

        fieldKeys.add(Query.Field.TIMECREATE);
        fieldKeys.add(Query.Field.TIMEUPDATE);
        fieldKeys.add(Query.Field.TIMEEXPIRE);

        fieldKeys.add(Query.Field.TARGETCLASS);
        fieldKeys.add(Query.Field.FIELDFILTERS);
        fieldKeys.add(Query.Field.SPACEFILTER);
        fieldKeys.add(Query.Field.TIMEFILTER);
        fieldKeys.add(Query.Field.RELATIONFILTER);
        fieldKeys.add(Query.Field.SORT);
        fieldKeys.add(Query.Field.START);
        fieldKeys.add(Query.Field.ROWS);
        fieldKeys.add(Query.Field.VIEW);
        fieldKeys.add(Query.Field.FIELDVIEWMAP);

        fieldKeys.add(Query.Field.USERCREATOR);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewOriginal() {
        List<String> fieldKeys = new ArrayList<String>();

        fieldKeys.add(Query.Field.CLASS);
        fieldKeys.add(Query.Field.ID);
        fieldKeys.add(Query.Field.LABEL);
        fieldKeys.add(Query.Field.STATE);
        fieldKeys.add(Query.Field.CONTROL);
        fieldKeys.add(Query.Field.NOTE);

        fieldKeys.add(Query.Field.LIFECYCLE);

        fieldKeys.add(Query.Field.NAME);

        fieldKeys.add(Query.Field.TIMECREATE);
        fieldKeys.add(Query.Field.TIMEUPDATE);
        fieldKeys.add(Query.Field.TIMEEXPIRE);

        fieldKeys.add(Query.Field.TARGETCLASS);
        fieldKeys.add(Query.Field.FIELDFILTERS);
        fieldKeys.add(Query.Field.SPACEFILTER);
        fieldKeys.add(Query.Field.TIMEFILTER);
        fieldKeys.add(Query.Field.RELATIONFILTER);
        fieldKeys.add(Query.Field.SORT);
        fieldKeys.add(Query.Field.START);
        fieldKeys.add(Query.Field.ROWS);
        fieldKeys.add(Query.Field.VIEW);
        fieldKeys.add(Query.Field.FIELDVIEWMAP);

        fieldKeys.add(Query.Field.USERCREATOR);

        return fieldKeys;
    }

    //Note: different from other entities, try make deep copy here
    @Override
    public Query makeCopyOfFields(List<String> fieldKeys) {

        List<String> fieldKeysForEntityCopy = Query.getFieldKeys();
        if (fieldKeys != null) {
            fieldKeysForEntityCopy = fieldKeys;
        }

        Query entityCopy = new Query();

        if (fieldKeysForEntityCopy.contains(Query.Field.CLASS)) {
            entityCopy.clazz = this.clazz;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.ID)) {
            entityCopy.id = this.id;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.LABEL)) {
            entityCopy.label = this.label;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.STATE)) {
            entityCopy.state = this.state;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.CONTROL)) {
            entityCopy.control = this.control;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.NOTE)) {
            entityCopy.note = this.note;
        }

        if (fieldKeysForEntityCopy.contains(Query.Field.LIFECYCLE)) {
            entityCopy.lifecycle = this.lifecycle;
        }

        if (fieldKeysForEntityCopy.contains(Query.Field.NAME)) {
            entityCopy.name = this.name;
        }

        if (fieldKeysForEntityCopy.contains(Query.Field.TIMECREATE)) {
            entityCopy.timeCreate = this.timeCreate;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.TIMEUPDATE)) {
            entityCopy.timeUpdate = this.timeUpdate;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.TIMEEXPIRE)) {
            entityCopy.timeExpire = this.timeExpire;
        }

        if (fieldKeysForEntityCopy.contains(Query.Field.TARGETCLASS)) {
            entityCopy.targetClass = this.targetClass;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.FIELDFILTERS)) {
            entityCopy.fieldFilters = QueryFilterOfField.makeCopyList(this.fieldFilters);
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.SPACEFILTER)) {
            entityCopy.spaceFilter = this.spaceFilter == null ? null : this.spaceFilter.makeCopy();
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.TIMEFILTER)) {
            entityCopy.timeFilter = this.timeFilter == null ? null : this.timeFilter.makeCopy();
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.RELATIONFILTER)) {
            entityCopy.relationFilter = this.relationFilter == null ? null : this.relationFilter.makeCopy();
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.SORT)) {
            entityCopy.sort = this.sort == null ? null : this.sort.makeCopy();
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.START)) {
            entityCopy.start = this.start;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.ROWS)) {
            entityCopy.rows = this.rows;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.VIEW)) {
            entityCopy.view = this.view;
        }
        if (fieldKeysForEntityCopy.contains(Query.Field.FIELDVIEWMAP)) {
            entityCopy.fieldViewMap = this.fieldViewMap == null ? null : new HashMap<String, String>(this.fieldViewMap);
        }

        if (fieldKeysForEntityCopy.contains(Query.Field.USERCREATOR)) {
            entityCopy.userCreator = this.userCreator == null ? null : this.userCreator.makeCopyOfViewOriginal();
        }

        return entityCopy;
    }

    @Override
    public Query makeCopyOfView(String view) {
        if (Query.View.ID.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewId();
        }
        if (Query.View.ABSTRACT.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewAbstract();
        }
        if (Query.View.INFO.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewInfo();
        }
        if (Query.View.ORIGINAL.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewOriginal();
        }
        return null;
    }

    @Override
    public Query makeCopyOfViewId() {
        return this.makeCopyOfFields(Query.getFieldKeysForViewId());
    }

    @Override
    public Query makeCopyOfViewAbstract() {
        return this.makeCopyOfFields(Query.getFieldKeysForViewAbstract());
    }

    @Override
    public Query makeCopyOfViewInfo() {
        return this.makeCopyOfFields(Query.getFieldKeysForViewInfo());
    }

    //Note: different from other entities, try make deep copy here
    @Override
    public Query makeCopyOfViewOriginal() {
        return this.makeCopyOfFields(Query.getFieldKeysForViewOriginal());
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
        List<String> fieldKeysToKeep = Query.getFieldKeysForViewId();
        List<String> fieldKeysToSetNil = Query.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewAbstract() {
        List<String> fieldKeysToKeep = Query.getFieldKeysForViewAbstract();
        List<String> fieldKeysToSetNil = Query.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewInfo() {
        List<String> fieldKeysToKeep = Query.getFieldKeysForViewInfo();
        List<String> fieldKeysToSetNil = Query.getFieldKeysForViewOriginal();
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
        if (fields.contains(Field.NAME)) {
            this.name = null;
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
        if (fields.contains(Field.TARGETCLASS)) {
            this.targetClass = null;
        }
        if (fields.contains(Field.FIELDFILTERS)) {
            this.fieldFilters = null;
        }
        if (fields.contains(Field.SPACEFILTER)) {
            this.spaceFilter = null;
        }
        if (fields.contains(Field.TIMEFILTER)) {
            this.timeFilter = null;
        }
        if (fields.contains(Field.RELATIONFILTER)) {
            this.relationFilter = null;
        }
        if (fields.contains(Field.SORT)) {
            this.sort = null;
        }
        if (fields.contains(Field.START)) {
            this.start = null;
        }
        if (fields.contains(Field.ROWS)) {
            this.rows = null;
        }
        if (fields.contains(Field.VIEW)) {
            this.view = null;
        }
        if (fields.contains(Field.FIELDVIEWMAP)) {
            this.fieldViewMap = null;
        }
        if (fields.contains(Field.USERCREATOR)) {
            this.userCreator = null;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Query> makeQueryListFromEntityListOfObject(Object entityListOfObject) throws Exception {

        List<Query> entityList = null;

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
            String message = ListException.createExceptionMessage(null, "unsupported Object for query list deserialization");
            throw new ListException(message);
        }

        if (entityOfObjectList == null) {
            return null;
        }

        entityList = new ArrayList<Query>();
        for (int i=0; i< entityOfObjectList.size(); i++) {
            Object entityOfObject = entityOfObjectList.get(i);
            Query entity = new Query(entityOfObject);
            entityList.add(entity);
        }
        return entityList;
    }

    //used to deserialize list<list<Query>> for query results
    @SuppressWarnings("unchecked")
    public static List<List<Query>> makeQueryListListFromEntityListListOfObject(Object entityListListOfObject) throws Exception {

        List<List<Query>> entityListList = null;

        if (entityListListOfObject == null) {
            return entityListList;
        }

        List<Object> entityListOfObjectList = null;
        //assume it is a jas, not handling list string
        if (entityListListOfObject instanceof String) {
            JSONArray queryListListOfJao = new JSONArray((String) entityListListOfObject);
            entityListOfObjectList = JSONHelper.transformJSONArrayToList(queryListListOfJao);
        } else if (entityListListOfObject instanceof JSONArray) {
            JSONArray queryListListOfJao = (JSONArray) entityListListOfObject;
            entityListOfObjectList = JSONHelper.transformJSONArrayToList(queryListListOfJao);
        } else if (entityListListOfObject instanceof List) {
            entityListOfObjectList = (List<Object>) entityListListOfObject;
        } else {
            //raise unsupported exception?
            String message = ListException.createExceptionMessage(null, "unsupported Object for query list list deserialization");
            throw new ListException(message);
        }

        if (entityListOfObjectList == null) {
            return null;
        }

        entityListList = new ArrayList<List<Query>>();
        for (int i=0; i< entityListOfObjectList.size(); i++) {
            Object entityListOfObject = entityListOfObjectList.get(i);
            List<Query> queryList = Query.makeQueryListFromEntityListOfObject(entityListOfObject);
            entityListList.add(queryList);
        }
        return entityListList;
    }

    public static Query makeDomainQueryForRetrieveById(String entityClass, String idField, String idValue, String entityView) throws Exception {
        Query rt = null;

        if (idField != null) {
            List<QueryFilterOfField> fieldFilters = new ArrayList<QueryFilterOfField>();
            QueryFilterOfField filterOfFieldForId = new QueryFilterOfField(QueryFilterOfField.Operator.EQUAL, idField, idValue, null);
            fieldFilters.add(filterOfFieldForId);
            rt = new Query(null, null, null, null, null, null,
                    null,
                    null,
                    null, null, null,
                    entityClass, fieldFilters, null, null, null, null, null, null, entityView, null,
                    null);
        }

        return rt;
    }

    //same as retrieve by id
    public static Query makeDomainQueryForSearchById(String entityClass, String idField, String idValue, String entityView) throws Exception {
        Query rt = null;

        if (idField != null) {
            List<QueryFilterOfField> fieldFilters = new ArrayList<QueryFilterOfField>();
            QueryFilterOfField filterOfFieldForId = new QueryFilterOfField(QueryFilterOfField.Operator.EQUAL, idField, idValue, null);
            fieldFilters.add(filterOfFieldForId);
            rt = new Query(null, null, null, null, null, null,
                    null,
                    null,
                    null, null, null,
                    entityClass, fieldFilters, null, null, null, null, null, null, entityView, null,
                    null);
        }

        return rt;
    }

    public static Query makeDomainQueryForRetrieveByIdAndTimeRange(String entityClass, String idField, String idValue, String timeField, Long timeLowerBound, Boolean timeLowerBoundIncluded, Long timeUpperBound, Boolean timeUpperBoundIncluded, String entityView) throws Exception {
        Query rt = null;

        List<QueryFilterOfField> fieldFilters = new ArrayList<QueryFilterOfField>();
        if (idField != null) {
            QueryFilterOfField filterOfFieldForId = new QueryFilterOfField(QueryFilterOfField.Operator.EQUAL, idField, idValue, null);
            fieldFilters.add(filterOfFieldForId);
        }

        if (timeField!= null && timeLowerBound != null && timeLowerBoundIncluded != null) {
            QueryFilterOfField filterOfFieldForTimeLowerBound = new QueryFilterOfField(QueryFilterOfField.Operator.GREATERTHAN, timeField, timeLowerBound, null);
            if (timeLowerBoundIncluded) {
                filterOfFieldForTimeLowerBound.operator = QueryFilterOfField.Operator.GREATERTHANEQUAL;
            }
            fieldFilters.add(filterOfFieldForTimeLowerBound);
        }
        if (timeField!= null && timeUpperBound != null && timeUpperBoundIncluded != null) {
            QueryFilterOfField filterOfFieldForTimeUpperBound = new QueryFilterOfField(QueryFilterOfField.Operator.LESSERTHAN, timeField, timeUpperBound, null);
            if (timeUpperBoundIncluded) {
                filterOfFieldForTimeUpperBound.operator = QueryFilterOfField.Operator.LESSERTHANEQUAL;
            }
            fieldFilters.add(filterOfFieldForTimeUpperBound);
        }

        if (fieldFilters.size() > 0) {
            rt = new Query(null, null, null, null, null, null,
                    null,
                    null,
                    null, null, null,
                    entityClass, fieldFilters, null, null, null, null, null, null, entityView, null,
                    null);
        }

        return rt;
    }

    public static Query makeDomainQueryForMessageRetrieve(String sessionOfId, String timeField, Long timeEarliest, Boolean timeEarliestIncluded, Long timeLatest, Boolean timeLatestIncluded, String view) throws Exception {
        Query rt = null;

        List<QueryFilterOfField> fieldFilters = new ArrayList<QueryFilterOfField>();
        if (sessionOfId != null) {
            QueryFilterOfField filterOfFieldForSessionOf = new QueryFilterOfField(QueryFilterOfField.Operator.EQUAL, Message.Field.SESSIONOF, sessionOfId, null);
            fieldFilters.add(filterOfFieldForSessionOf);
        }

        if (timeField!= null && timeEarliest != null && timeEarliestIncluded != null) {
            QueryFilterOfField filterOfFieldForTimeEarliest = new QueryFilterOfField(QueryFilterOfField.Operator.GREATERTHAN, timeField, timeEarliest, null);
            if (timeEarliestIncluded) {
                filterOfFieldForTimeEarliest.operator = QueryFilterOfField.Operator.GREATERTHANEQUAL;
            }
            fieldFilters.add(filterOfFieldForTimeEarliest);
        }
        if (timeField!= null && timeLatest != null && timeLatestIncluded != null) {
            QueryFilterOfField filterOfFieldForTimeLatest = new QueryFilterOfField(QueryFilterOfField.Operator.LESSERTHAN, timeField, timeLatest, null);
            if (timeLatestIncluded) {
                filterOfFieldForTimeLatest.operator = QueryFilterOfField.Operator.LESSERTHANEQUAL;
            }
            fieldFilters.add(filterOfFieldForTimeLatest);
        }

        //TODO client/server handle error when no filter specified....//?
        if (fieldFilters.size() > 0) {
            rt = new Query(null, null, null, null, null, null,
                    null,
                    null,
                    null, null, null,
                    Message.Class.value, fieldFilters, null, null, null, null, null, null, view, null,
                    null);
        }

        return rt;
    }

    //TODO client not updated
    public boolean util_hasFieldFilterForField(String field) {
        if (this.fieldFilters != null) {
            for (QueryFilterOfField fieldFilter: this.fieldFilters) {
                if (field == null) {
                    if (fieldFilter.field == null) {
                        return true;
                    }
                }
                else if (field.equals(fieldFilter.field)) {//cant use ==...
                    return true;
                }
            }
        }
        return false;
    }

    public Integer util_countOfFieldFiltersForFieldAndOperator(String field, String operator) {
        Integer count = 0;
        if (this.fieldFilters != null) {
            for (QueryFilterOfField fieldFilter: this.fieldFilters) {
                boolean matched = true;
                if (field == null) {
                    if (fieldFilter.field != null) {
                        matched = false;
                    }
                } else if (!field.equals(fieldFilter.field)) {
                    matched = false;
                }
                if (operator == null) {
                    if (fieldFilter.operator != null) {
                        matched = false;
                    }
                } else if (!operator.equals(fieldFilter.operator)) {
                    matched = false;
                }
                if (matched) {
                    count ++;
                }
            }
        }
        return count;
    }

    public Object util_valueOfFirstFieldFilterForFieldAndOperator(String field, String operator) {
        if (this.fieldFilters != null) {
            for (QueryFilterOfField fieldFilter: this.fieldFilters) {
                boolean matched = true;
                if (field == null) {
                    if (fieldFilter.field != null) {
                        matched = false;
                    }
                } else if (!field.equals(fieldFilter.field)) {
                    matched = false;
                }
                if (operator == null) {
                    if (fieldFilter.operator != null) {
                        matched = false;
                    }
                } else if (!operator.equals(fieldFilter.operator)) {
                    matched = false;
                }
                if (matched) {
                    return fieldFilter.value;
                }
            }
        }
        return null;
    }

    public static List<Query> util_pruneQueryList(List<Query> queryList, QueryFilterOfRelation relationFilter) {
        List<Query> rt = queryList;
        if (queryList == null || relationFilter == null) {
            return rt;
        }
        Iterator<Query> iterQuery = rt.iterator();
        while (iterQuery.hasNext()) {
            Query query = iterQuery.next();
            //filter query itself using relationFilter
            String relationField = relationFilter.field;
            Long fieldValueToEval = null;
            if (Query.Field.TIMECREATE.equals(relationField)) {
                fieldValueToEval = query.timeCreate;
            } else if (Query.Field.TIMEUPDATE.equals(relationField)) {
                fieldValueToEval = query.timeUpdate;
            } else if (Query.Field.TIMEEXPIRE.equals(relationField)) {
                fieldValueToEval = query.timeExpire;
            }
            if (fieldValueToEval != null) {
                if (!relationFilter.eval(fieldValueToEval)) {
                    iterQuery.remove();
                }
            }
        }
        return rt;
    }
}
