package com.fsd.mvvmlight.freeseeds.entity;

import com.fsd.mvvmlight.freeseeds.exception.ListException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import library.helper.JSONHelper;
import library.helper.JSONHelper.JSONObjectWrapper;


public class Media extends Entity {

    public static final class Field extends Entity.Field {
        public static final String TYPE = "type";
        public static final String NAME = "name";
        public static final String ORDER = "order";
        public static final String URLS = "urls";
        public static final String DATA = "data";
        //dimensions
        public static final String WIDTHS = "widths";
        public static final String HEIGHTS = "heights";
    }

    public static final class Class {
        public static final String value = "media";
    }

    //not used
    public static final class Type {
        public static final String IMAGE = "image";
        public static final String DOCUMENT = "document";
    }

    public String type;
    public String name;
    public Integer order;
    public Map<String, String> urls;
    public Map<String, Object> data;
    public Map<String, Double> widths;
    public Map<String, Double> heights;

    public Media () {
        super();
    }

    public Media(String id, Boolean dummySignatureForInitById) {
        super(id, dummySignatureForInitById);
    }

    public Media(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                 String type, String name, Integer order,
                 Map<String, String> urls, Map<String, Object> data, Map<String, Double> widths, Map<String, Double> heights) throws Exception {

        this.self_installFields(clazz, id, label, state, control, note,
                type, name, order,
                urls, data, widths, heights);

        this.sub_validateFields();
    }

    public Media(Object entityOfObject) throws Exception {
        super(entityOfObject);
    }

    protected void self_installFields(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,
                                      String type, String name, Integer order, Map<String, String> urls, Map<String, Object> data, Map<String, Double> widths, Map<String, Double> heights) {

        super.self_installFields(clazz, id, label, state, control, note);

        this.type = type;
        this.name = name;
        this.order = order;
        this.urls = urls;
        this.data = data;
        this.widths = widths;
        this.heights = heights;
    }

    @Override
    protected void sub_decodeFields(JSONObjectWrapper entityJwo) throws Exception {
        super.sub_decodeFields(entityJwo);
        try {
            if (entityJwo == null) {
                return;
            } else {
                this.type = entityJwo.getString(Field.TYPE);
                this.name = entityJwo.getString(Field.NAME);
                this.order = entityJwo.getInt(Field.ORDER);

                this.urls = entityJwo.getValueAsMap(Field.URLS);
                this.data = entityJwo.getValueAsMap(Field.DATA);
                this.widths = entityJwo.getValueAsMap(Field.WIDTHS);
                this.heights = entityJwo.getValueAsMap(Field.HEIGHTS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void sub_validateFields() throws Exception {
        //set default if not valid etc.
        if (this.state != null && !Media.getStateValues().contains(this.state.toLowerCase())) {
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
            entityJoo.put(Field.TYPE, type);
            entityJoo.put(Field.NAME, name);
            entityJoo.put(Field.ORDER, order);

            entityJoo.put(Field.URLS, JSONHelper.transformMapToJSONObject(this.urls));
            entityJoo.put(Field.DATA, JSONHelper.transformMapToJSONObject(this.data));
            entityJoo.put(Field.WIDTHS, JSONHelper.transformMapToJSONObject(this.widths));
            entityJoo.put(Field.HEIGHTS, JSONHelper.transformMapToJSONObject(this.heights));
        } catch (Exception e) {
            e.printStackTrace();
            entityJoo = new JSONObject();
        }
        return entityJoo;
    }

    @Override
    public boolean equals(Object object) {
        boolean rt = false;
        if (object instanceof Media) {
            Media media = (Media) object;
            if (this.id != null) {
                rt = this.id.equals(media.id);
            } else if (media.id == null) {
                rt = true;
            }
        }
        return rt;
    }

    public static List<String> getFieldKeys() {
        List<String> fieldKeys = new ArrayList<String>();

        fieldKeys.add(Media.Field.CLASS);
        fieldKeys.add(Media.Field.ID);
        fieldKeys.add(Media.Field.LABEL);
        fieldKeys.add(Media.Field.STATE);
        fieldKeys.add(Topic.Field.CONTROL);
        fieldKeys.add(Media.Field.NOTE);

        fieldKeys.add(Media.Field.TYPE);
        fieldKeys.add(Media.Field.NAME);
        fieldKeys.add(Media.Field.ORDER);
        fieldKeys.add(Media.Field.URLS);
        fieldKeys.add(Media.Field.DATA);
        fieldKeys.add(Media.Field.WIDTHS);
        fieldKeys.add(Media.Field.HEIGHTS);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForView(String view) {
        if (User.View.ID.equals(view)) {
            return Media.getFieldKeysForViewId();
        }
        if (User.View.ABSTRACT.equals(view)) {
            return Media.getFieldKeysForViewAbstract();
        }
        if (User.View.INFO.equals(view)) {
            return Media.getFieldKeysForViewInfo();
        }
        if (User.View.ORIGINAL.equals(view)) {
            return Media.getFieldKeysForViewOriginal();
        }
        return new ArrayList<String>();
    }

    public static List<String> getFieldKeysForViewId() {
        List<String> fieldKeys = new ArrayList<String>();
        fieldKeys.add(Media.Field.ID);
        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewAbstract() {

        List<String> fieldKeys = new ArrayList<String>();

        //        fieldKeys.add(Media.Field.CLASS);
        fieldKeys.add(Media.Field.ID);
        //        fieldKeys.add(Media.Field.LABEL);
        //        fieldKeys.add(Media.Field.STATE);
        fieldKeys.add(Media.Field.CONTROL);
        //        fieldKeys.add(Media.Field.NOTE);

        fieldKeys.add(Media.Field.TYPE);
        fieldKeys.add(Media.Field.NAME);
        fieldKeys.add(Media.Field.ORDER);
        fieldKeys.add(Media.Field.URLS);
        fieldKeys.add(Media.Field.DATA);
        fieldKeys.add(Media.Field.WIDTHS);
        fieldKeys.add(Media.Field.HEIGHTS);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewInfo() {

        List<String> fieldKeys = new ArrayList<String>();

        //        fieldKeys.add(Media.Field.CLASS);
        fieldKeys.add(Media.Field.ID);
        //        fieldKeys.add(Media.Field.LABEL);
        //        fieldKeys.add(Media.Field.STATE);
        fieldKeys.add(Topic.Field.CONTROL);
        //        fieldKeys.add(Media.Field.NOTE);

        fieldKeys.add(Media.Field.TYPE);
        fieldKeys.add(Media.Field.NAME);
        fieldKeys.add(Media.Field.ORDER);
        fieldKeys.add(Media.Field.URLS);
        fieldKeys.add(Media.Field.DATA);
        fieldKeys.add(Media.Field.WIDTHS);
        fieldKeys.add(Media.Field.HEIGHTS);

        return fieldKeys;
    }

    public static List<String> getFieldKeysForViewOriginal() {
        List<String> fieldKeys = new ArrayList<String>();

        fieldKeys.add(Media.Field.CLASS);
        fieldKeys.add(Media.Field.ID);
        fieldKeys.add(Media.Field.LABEL);
        fieldKeys.add(Media.Field.STATE);
        fieldKeys.add(Topic.Field.CONTROL);
        fieldKeys.add(Media.Field.NOTE);

        fieldKeys.add(Media.Field.TYPE);
        fieldKeys.add(Media.Field.NAME);
        fieldKeys.add(Media.Field.ORDER);
        fieldKeys.add(Media.Field.URLS);
        fieldKeys.add(Media.Field.DATA);
        fieldKeys.add(Media.Field.WIDTHS);
        fieldKeys.add(Media.Field.HEIGHTS);

        return fieldKeys;
    }

    @Override
    public Media makeCopyOfFields(List<String> fieldKeys) {

        List<String> fieldKeysForEntityCopy = Media.getFieldKeys();
        if (fieldKeys != null) {
            fieldKeysForEntityCopy = fieldKeys;
        }

        Media entityCopy = new Media();

        if (fieldKeysForEntityCopy.contains(Media.Field.CLASS)) {
            entityCopy.clazz = this.clazz;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.ID)) {
            entityCopy.id = this.id;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.LABEL)) {
            entityCopy.label = this.label;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.STATE)) {
            entityCopy.state = this.state;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.CONTROL)) {
            entityCopy.control = this.control;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.NOTE)) {
            entityCopy.note = this.note;
        }

        if (fieldKeysForEntityCopy.contains(Media.Field.TYPE)) {
            entityCopy.type = this.type;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.NAME)) {
            entityCopy.name = this.name;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.ORDER)) {
            entityCopy.order = this.order;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.URLS)) {
            entityCopy.urls = this.urls;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.DATA)) {
            entityCopy.data = this.data;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.WIDTHS)) {
            entityCopy.widths = this.widths;
        }
        if (fieldKeysForEntityCopy.contains(Media.Field.HEIGHTS)) {
            entityCopy.heights = this.heights;
        }

        return entityCopy;
    }

    @Override
    public Media makeCopyOfView(String view) {
        if (Media.View.ID.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewId();
        }
        if (Media.View.ABSTRACT.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewAbstract();
        }
        if (Media.View.INFO.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewInfo();
        }
        if (Media.View.ORIGINAL.equalsIgnoreCase(view)) {
            return this.makeCopyOfViewOriginal();
        }
        return null;
    }

    @Override
    public Media makeCopyOfViewId() {
        return this.makeCopyOfFields(Media.getFieldKeysForViewId());
    }

    @Override
    public Media makeCopyOfViewAbstract() {
        return this.makeCopyOfFields(Media.getFieldKeysForViewAbstract());
    }

    @Override
    public Media makeCopyOfViewInfo() {
        return this.makeCopyOfFields(Media.getFieldKeysForViewInfo());
    }

    @Override
    public Media makeCopyOfViewOriginal() {
        return this.makeCopyOfFields(Media.getFieldKeysForViewOriginal());
    }

    @Override
    public void madeToView(String view) {
        if (Media.View.ID.equals(view)) {
            this.madeToViewId();
        }
        if (Media.View.ABSTRACT.equals(view)) {
            this.madeToViewAbstract();
        }
        if (Media.View.INFO.equals(view)) {
            this.madeToViewInfo();
        }
    }

    @Override
    public void madeToViewId() {
        List<String> fieldKeysToKeep = Media.getFieldKeysForViewId();
        List<String> fieldKeysToSetNil = Media.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewAbstract() {
        List<String> fieldKeysToKeep = Media.getFieldKeysForViewAbstract();
        List<String> fieldKeysToSetNil = Media.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    public void madeToViewInfo() {
        List<String> fieldKeysToKeep = Media.getFieldKeysForViewInfo();
        List<String> fieldKeysToSetNil = Media.getFieldKeysForViewOriginal();
        fieldKeysToSetNil.removeAll(fieldKeysToKeep);
        this.util_setNilForFields(fieldKeysToSetNil);
    }

    @Override
    protected void util_setNilForFields(List<String> fields) {
        super.util_setNilForFields(fields);

        if (fields == null) {
            return;
        }

        if (fields.contains(Field.TYPE)) {
            this.type = null;
        }
        if (fields.contains(Field.NAME)) {
            this.name = null;
        }
        if (fields.contains(Field.ORDER)) {
            this.order = null;
        }
        if (fields.contains(Field.URLS)) {
            this.urls = null;
        }
        if (fields.contains(Field.DATA)) {
            this.data = null;
        }
        if (fields.contains(Field.WIDTHS)) {
            this.widths = null;
        }
        if (fields.contains(Field.HEIGHTS)) {
            this.heights = null;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Media> makeMediaListFromEntityListOfObject(Object entityListOfObject) throws Exception {

        List<Media> entityList = null;

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

        entityList = new ArrayList<Media>();
        for (int i=0; i< entityOfObjectList.size(); i++) {
            Object entityOfObject = entityOfObjectList.get(i);
            Media entity = new Media(entityOfObject);
            entityList.add(entity);
        }
        return entityList;
    }
}
