package com.fsd.mvvmlight.freeseeds.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.helper.JSONHelper;
import library.helper.JSONHelper.*;

/**
 * Created by zhangyabei on 5/6/17.
 */

public class Entity implements EntityI {
    public static class Field {
        public static final String CLASS = "class";
        public static final String ID = "id";
        public static final String LABEL = "label";
        public static final String STATE = "state";
        public static final String CONTROL = "control";
        public static final String NOTE = "note";
    }

    public static class State {
        public static final String CREATE = "create";
        public static final String UPDATE = "update";
        public static final String DELETE = "delete";
    }

    public static class Control {
        public static final String SET = "set";
        public static final String ADD = "add";
        public static final String REMOVE = "remove";
    }

    public static class View {
        public static final String ID = "id";
        public static final String ABSTRACT = "abstract";
        public static final String INFO = "info";
        public static final String ORIGINAL = "original";
    }
    protected String clazz;//reserved, not used for now
    public String id;
    public String label;
    public String state;//non-exclusive state before/after certain action is/was taken //maybe change name to something like actionstate
    public Map<String, String> control;//control to fields of the entity, use state values. Only useful for state update, control tells set, add or delete, for single valued field set/add/delete are the same, otherwise different
    public Map<String, Object> note;

    public Entity() {

    }

    //init by id, to differentiate with Object init, use a dummy second parameter
    public Entity(String id, Boolean dummySignatureForInitById) {
        this.id = id;
    }

    public Entity(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note) throws Exception {
        this.self_installFields(clazz, id, label, state, control, note);
        this.sub_validateFields();
    }

    public Entity(Object entityOfObject) {
        try {
            JSONObject entityJoo = null;
            if (entityOfObject == null) {
                //do not return here for validation purpose
            }
            else if (entityOfObject instanceof String) {
                entityJoo = new JSONObject((String) entityOfObject);
            }
            else if (entityOfObject instanceof JSONObject) {
                entityJoo = (JSONObject) entityOfObject;
            }
            else if (entityOfObject instanceof EntityI) {
                entityJoo = ((EntityI) entityOfObject).toJSONObject();
            }
            else if (entityOfObject instanceof Map) {
                entityJoo = new JSONObject((Map<?, ?>) entityOfObject);//immutable???
            }
            else {
                //raise unsupported exception?
            }
            JSONObjectWrapper entityJwo = new JSONObjectWrapper(entityJoo);
            this.sub_decodeFields(entityJwo);
            this.sub_validateFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void self_installFields(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note) {
        this.clazz = clazz;
        this.id = id;
        this.label = label;
        this.state = state;
        this.control = control;
        this.note = note;
    }

    protected void sub_decodeFields(JSONObjectWrapper entityJwo) throws Exception {
        if (entityJwo == null) {
            return;
        } else {
            this.clazz = entityJwo.getString(Field.CLASS);
            this.id = entityJwo.getString(Field.ID);
            this.label = entityJwo.getString(Field.LABEL);
            this.state = entityJwo.getString(Field.STATE);
            this.control = entityJwo.getValueAsMap(Field.CONTROL);
            this.note = entityJwo.getValueAsMap(Field.NOTE);

        }
    }

    protected void sub_validateFields() throws Exception {
        if (this.state != null && !Entity.getStateValues().contains(this.state.toLowerCase())) {
            this.state = null;
        }
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject entityJoo = null;
        try {
            entityJoo = new JSONObject();
            entityJoo.put(Field.CLASS, this.clazz);
            entityJoo.put(Field.ID, this.id);
            entityJoo.put(Field.LABEL, this.label);
            entityJoo.put(Field.STATE, this.state);
            entityJoo.put(Field.CONTROL, JSONHelper.transformMapToJSONObject(this.control));
            entityJoo.put(Field.NOTE, JSONHelper.transformMapToJSONObject(this.note));

//    		if (entityJoo.length() == 0) {
//				entityJoo = null;
//			}

        } catch (Exception e) {
            e.printStackTrace();
            entityJoo = new JSONObject();
        }
        return entityJoo;
    }

    public boolean equals(Entity otherEntity) {
        if (this.getClass().equals(otherEntity.getClass())) {
            if (this.id != null) {
                if (this.id.equals(otherEntity.id)) {
                    return true;
                }
            } else {
                if (otherEntity.id == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        JSONObject entityJoo = this.toJSONObject();
        //no need but keep below check
        if (entityJoo == null) {
            entityJoo = new JSONObject();
        }
        return entityJoo.toString();
    }

    public static JSONArray makeJSONArrayFromEntityList(List<? extends EntityI> entityList) {

        JSONArray entityJao = null;

        if (entityList == null) {
            return entityJao;
        }

        entityJao = new JSONArray();
        for (EntityI entity : entityList) {
            entityJao.put(entity.toJSONObject());
        }
        return entityJao;
    }

    public static JSONArray makeJSONArrayFromEntityListList(List<? extends List<? extends EntityI>> entityListList) {

        JSONArray entityJaoJao = null;

        if (entityListList == null) {
            return entityJaoJao;
        }

        entityJaoJao = new JSONArray();
        for (List<? extends EntityI> entityList : entityListList) {
            entityJaoJao.put(Entity.makeJSONArrayFromEntityList(entityList));
        }
        return entityJaoJao;
    }

    public static List<String> getViewValues() {
        List<String> viewValues = new ArrayList<String>();
        viewValues.add(View.ID.toLowerCase());
        viewValues.add(View.ABSTRACT.toLowerCase());
        viewValues.add(View.INFO.toLowerCase());
        viewValues.add(View.ORIGINAL.toLowerCase());
        return viewValues;
    }

    public static List<String> getStateValues() {
        List<String> stateValues = new ArrayList<String>();
        stateValues.add(State.CREATE.toLowerCase());
        stateValues.add(State.UPDATE.toLowerCase());
        stateValues.add(State.DELETE.toLowerCase());
        return stateValues;
    }

    public static List<String> getFieldKeys() {
        List<String> fieldKeys = new ArrayList<String>();
        fieldKeys.add(Entity.Field.CLASS);
        fieldKeys.add(Entity.Field.ID);
        fieldKeys.add(Entity.Field.LABEL);
        fieldKeys.add(Entity.Field.STATE);
        fieldKeys.add(Entity.Field.CONTROL);
        fieldKeys.add(Entity.Field.NOTE);
        return fieldKeys;
    }

    public static List<String> getFieldKeysForView(String view) {
        if (Entity.View.ID.equals(view)) {
            return Entity.getFieldKeysForViewId();
        }
        if (Entity.View.ABSTRACT.equals(view)) {
            return Entity.getFieldKeysForViewAbstract();
        }
        if (Entity.View.INFO.equals(view)) {
            return Entity.getFieldKeysForViewInfo();
        }
        if (Entity.View.ORIGINAL.equals(view)) {
            return Entity.getFieldKeysForViewOriginal();
        }
        return new ArrayList<String>();
    }

    public static List<String> getFieldKeysForViewId() {
        List<String> fieldKeys = new ArrayList<String>();
        fieldKeys.add(Entity.Field.ID);
        return fieldKeys;
    }

    //for now abstract view is the same as info view, use case for abstract view is not clear
    public static List<String> getFieldKeysForViewAbstract() {
        List<String> fieldKeys = new ArrayList<String>();
//    	fieldKeys.add(Entity.Field.CLASS);
        fieldKeys.add(Entity.Field.ID);
//        fieldKeys.add(Entity.Field.LABEL);
//        fieldKeys.add(Entity.Field.STATE);
//        fieldKeys.add(Entity.Field.CONTROL);
//        fieldKeys.add(Entity.Field.NOTE);
        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewInfo() {
        List<String> fieldKeys = new ArrayList<String>();
//    	fieldKeys.add(Entity.Field.CLASS);
        fieldKeys.add(Entity.Field.ID);
//        fieldKeys.add(Entity.Field.LABEL);
//        fieldKeys.add(Entity.Field.STATE);
//        fieldKeys.add(Entity.Field.CONTROL);
//        fieldKeys.add(Entity.Field.NOTE);
        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewOriginal() {
        List<String> fieldKeys = new ArrayList<String>();
        fieldKeys.add(Entity.Field.CLASS);
        fieldKeys.add(Entity.Field.ID);
        fieldKeys.add(Entity.Field.LABEL);
        fieldKeys.add(Entity.Field.STATE);
        fieldKeys.add(Entity.Field.CONTROL);
        fieldKeys.add(Entity.Field.NOTE);
        return fieldKeys;
    }

    public Entity makeCopyOfFields(List<String> fieldKeys) {
        List<String> fieldKeysForEntityCopy = Entity.getFieldKeys();
        if (fieldKeys != null) {
            fieldKeysForEntityCopy = fieldKeys;
        }
        Entity entityCopy = new Entity();
        if (fieldKeysForEntityCopy.contains(Entity.Field.CLASS)) {
            entityCopy.clazz = this.clazz;
        }
        if (fieldKeysForEntityCopy.contains(Entity.Field.ID)) {
            entityCopy.id = this.id;
        }
        if (fieldKeysForEntityCopy.contains(Entity.Field.LABEL)) {
            entityCopy.label = this.label;
        }
        if (fieldKeysForEntityCopy.contains(Entity.Field.STATE)) {
            entityCopy.state = this.state;
        }
        if (fieldKeysForEntityCopy.contains(Entity.Field.CONTROL)) {
            entityCopy.control = this.control;
        }
        if (fieldKeysForEntityCopy.contains(Entity.Field.NOTE)) {
            entityCopy.note = this.note;
        }
        return entityCopy;
    }

    public Entity makeCopyOfView(String view) {
        if (Entity.View.ID.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewId();
        }
        if (Entity.View.ABSTRACT.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewAbstract();
        }
        if (Entity.View.INFO.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewInfo();
        }
        if (Entity.View.ORIGINAL.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewOriginal();
        }
        return null;
    }

    public Entity makeCopyOfViewId() {
        return this.makeCopyOfFields(Entity.getFieldKeysForViewId());
    }

    public Entity makeCopyOfViewAbstract() {
        return this.makeCopyOfFields(Entity.getFieldKeysForViewAbstract());
    }

    public Entity makeCopyOfViewInfo() {
        return this.makeCopyOfFields(Entity.getFieldKeysForViewInfo());
    }

    public Entity makeCopyOfViewOriginal() {
        return this.makeCopyOfFields(Entity.getFieldKeysForViewOriginal());
    }

    public void madeToView(String view) {
        if (Entity.View.ID.equals(view)) {
            this.madeToViewId();
        }
        if (Entity.View.ABSTRACT.equals(view)) {
            this.madeToViewAbstract();
        }
        if (Entity.View.INFO.equals(view)) {
            this.madeToViewInfo();
        }
    }

    public void madeToViewId() {
        List<String> fieldKeysToKeep = Entity.getFieldKeysForViewId();
        List<String> fieldKeysToSetNil = Entity.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    public void madeToViewAbstract() {
        List<String> fieldKeysToKeep = Entity.getFieldKeysForViewAbstract();
        List<String> fieldKeysToSetNil = Entity.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    public void madeToViewInfo() {
        List<String> fieldKeysToKeep = Entity.getFieldKeysForViewInfo();
        List<String> fieldKeysToSetNil = Entity.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    //better use set
    protected void util_setNilForFields(List<String> fields) {
        if (fields == null) {
            return;
        }

        if (fields.contains(Field.CLASS)) {
            this.clazz = null;
        }
        if (fields.contains(Field.ID)) {
            this.id = null;
        }
        if (fields.contains(Field.LABEL)) {
            this.label = null;
        }
        if (fields.contains(Field.STATE)) {
            this.state = null;
        }
        if (fields.contains(Field.CONTROL)) {
            this.control = null;
        }
        if (fields.contains(Field.NOTE)) {
            this.note = null;
        }
    }

}
