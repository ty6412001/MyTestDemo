package com.fsd.mvvmlight.freeseeds.entity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import library.helper.JSONHelper;


//Error is too common to be used as a user class name
public class DomainError extends Entity {

	public static final class Field extends Entity.Field {
		public static final String CODE = "code";
		public static final String DESCRIPTION = "description";		
	}
	
	public static final class Class {
		public static final String value = "error";
	} 
	
	public class Code { 
		public static final int UNKNOWN = 1; 
		public static final int VALIDATE_DOMAINQUERY_MISSINGFIELD_ENTITYCLASS = 1007; 
	}
	
	public class Description { 		
		public static final String UNKNOWN = "unknown error"; 
		public static final String VALIDATE_DOMAINQUERY_MISSINGFIELD_ENTITYCLASS = "missing entityClass field in domain query"; 
	} 

	public Integer code;
	public String description;
	
	public DomainError() {
		super();
	}
	
	//TODO still may conflict with the second constructor when the second parameter was set to null
	public DomainError(String id, Boolean dummySignatureForInitById) {
		super(id, dummySignatureForInitById);
	}
	
	public DomainError(Integer code, String description) {
		this.code = code;
		this.description = description;
		this.sub_validateFields();
	}
	
	public DomainError(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note,  
			Integer code, String description) {
		
		this.self_installFields(clazz, id, label, state, control, note,  
				code, description);
		
		this.sub_validateFields();
	}
	
	public DomainError(Object entityOfObject) { 
		super(entityOfObject); 	 
	}
	
	protected void self_installFields(String clazz, String id, String label, String state, Map<String, String> control, Map<String, Object> note, 
			Integer code, String description) {
		
		super.self_installFields(clazz, id, label, state, control, note);
		
		this.code = code;
		this.description = description;
	}
	
	//this is different from other entities
	@Override
	protected void sub_decodeFields(JSONHelper.JSONObjectWrapper entityJwo) {
		try {
			super.sub_decodeFields(entityJwo);
			if (entityJwo == null) {
				return;
			} else {
				this.code = entityJwo.getInt(Field.CODE);
				this.description = entityJwo.getString(Field.DESCRIPTION);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void sub_validateFields() {
		if (this.code == null) {
			this.code = Code.UNKNOWN;//may conflict with application codes 
		}
		if (this.description == null) {			
			this.description = Description.UNKNOWN;
		}
	}
	 
	@Override
	public JSONObject toJSONObject() {
		
		JSONObject entityJoo = super.toJSONObject();
		
		try {
			if (entityJoo == null) {
				entityJoo = new JSONObject();
    		}
			entityJoo.put(Field.CODE, this.code);
			entityJoo.put(Field.DESCRIPTION, this.description);
			
		} catch (Exception e) {
			e.printStackTrace();
			entityJoo = new JSONObject();
		}
		return entityJoo;
	} 
	
	@Override 
	public boolean equals(Object object) {
		boolean rt = false;
		if (object instanceof DomainError) {
			DomainError domainError = (DomainError) object;
			if (this.code != null) {
				rt = this.code.equals(domainError.code);
			} else if (domainError.code == null) {
				rt = true;
			}
		}
		return rt;
	} 
	
	public static List<String> getFieldKeys() {
		List<String> fieldKeys = new ArrayList<String>();
    	
        fieldKeys.add(DomainError.Field.CLASS);
        fieldKeys.add(DomainError.Field.ID);
        fieldKeys.add(DomainError.Field.LABEL);
        fieldKeys.add(DomainError.Field.STATE); 
        fieldKeys.add(DomainError.Field.CONTROL);
        fieldKeys.add(DomainError.Field.NOTE);
        
        fieldKeys.add(DomainError.Field.CODE);
        fieldKeys.add(DomainError.Field.DESCRIPTION);
        
        return fieldKeys;
	}
	
	public static List<String> getFieldKeysForView(String view) {
        if (DomainError.View.ID.equals(view)) {
            return DomainError.getFieldKeysForViewId(); 
        }
        if (DomainError.View.ABSTRACT.equals(view)) {
            return DomainError.getFieldKeysForViewAbstract(); 
        }
        if (DomainError.View.INFO.equals(view)) {
            return DomainError.getFieldKeysForViewInfo(); 
        }
        if (DomainError.View.ORIGINAL.equals(view)) {
            return DomainError.getFieldKeysForViewOriginal(); 
        }
        return new ArrayList<String>(); 
    }
    
    public static List<String> getFieldKeysForViewId() {
        List<String> fieldKeys = new ArrayList<String>(); 
        
//        fieldKeys.add(DomainError.Field.CLASS);
        fieldKeys.add(DomainError.Field.ID);
//        fieldKeys.add(DomainError.Field.LABEL);
//        fieldKeys.add(DomainError.Field.STATE); 
//        fieldKeys.add(DomainError.Field.CONTROL);
//        fieldKeys.add(DomainError.Field.NOTE);
        
        fieldKeys.add(DomainError.Field.CODE); 
        fieldKeys.add(DomainError.Field.DESCRIPTION); 
        
        return fieldKeys; 
    }
    
    //for now abstract view is the same as info view, use case for abstract view is not clear
    public static List<String> getFieldKeysForViewAbstract() {
    	List<String> fieldKeys = new ArrayList<String>(); 
        
//    	fieldKeys.add(DomainError.Field.CLASS);
        fieldKeys.add(DomainError.Field.ID);
//        fieldKeys.add(DomainError.Field.LABEL);
//        fieldKeys.add(DomainError.Field.STATE); 
//        fieldKeys.add(DomainError.Field.CONTROL);
//        fieldKeys.add(DomainError.Field.NOTE); 
        
        fieldKeys.add(DomainError.Field.CODE); 
        fieldKeys.add(DomainError.Field.DESCRIPTION); 
        
        return fieldKeys; 
    }
    
    public static List<String> getFieldKeysForViewInfo() {
    	List<String> fieldKeys = new ArrayList<String>();
    	
//    	fieldKeys.add(DomainError.Field.CLASS);
        fieldKeys.add(DomainError.Field.ID);
//        fieldKeys.add(DomainError.Field.LABEL);
//        fieldKeys.add(DomainError.Field.STATE); 
//        fieldKeys.add(DomainError.Field.CONTROL);
//        fieldKeys.add(DomainError.Field.NOTE);
        
        fieldKeys.add(DomainError.Field.CODE);
        fieldKeys.add(DomainError.Field.DESCRIPTION); 
        return fieldKeys; 
    }
    
    public static List<String> getFieldKeysForViewOriginal() {
    	List<String> fieldKeys = new ArrayList<String>();
    	
        fieldKeys.add(DomainError.Field.CLASS);
        fieldKeys.add(DomainError.Field.ID);
        fieldKeys.add(DomainError.Field.LABEL);
        fieldKeys.add(DomainError.Field.STATE); 
        fieldKeys.add(DomainError.Field.CONTROL);
        fieldKeys.add(DomainError.Field.NOTE);
        
        fieldKeys.add(DomainError.Field.CODE);
        fieldKeys.add(DomainError.Field.DESCRIPTION);
        
        return fieldKeys; 
    }
    
    @Override
	public DomainError makeCopyOfFields(List<String> fieldKeys) {
		
    	List<String> fieldKeysForEntityCopy = DomainError.getFieldKeys();
    	if (fieldKeys != null) {
    		fieldKeysForEntityCopy = fieldKeys;
    	}
    	
    	DomainError entityCopy = new DomainError();
    	
    	if (fieldKeysForEntityCopy.contains(DomainError.Field.CLASS)) {
    		entityCopy.clazz = this.clazz;
    	}
    	if (fieldKeysForEntityCopy.contains(DomainError.Field.ID)) {
    		entityCopy.id = this.id;
    	}
    	if (fieldKeysForEntityCopy.contains(DomainError.Field.LABEL)) {
    		entityCopy.label = this.label;
    	} 
    	if (fieldKeysForEntityCopy.contains(DomainError.Field.STATE)) {
    		entityCopy.state = this.state;
    	} 
    	if (fieldKeysForEntityCopy.contains(DomainError.Field.CONTROL)) {
    		entityCopy.control = this.control;
    	}
    	if (fieldKeysForEntityCopy.contains(DomainError.Field.NOTE)) {
    		entityCopy.note = this.note;
    	}
    	
    	if (fieldKeysForEntityCopy.contains(DomainError.Field.CODE)) {
    		entityCopy.code = this.code;
    	}    	
    	if (fieldKeysForEntityCopy.contains(DomainError.Field.DESCRIPTION)) {
    		entityCopy.description = this.description;
    	}
    	
    	return entityCopy;
    }
	
	@Override
	public DomainError makeCopyOfView(String view) {
		if (DomainError.View.ID.equalsIgnoreCase(view)) {
			return this.makeCopyOfViewId();
		}
		if (DomainError.View.ABSTRACT.equalsIgnoreCase(view)) {
			return this.makeCopyOfViewAbstract();
		}
		if (DomainError.View.INFO.equalsIgnoreCase(view)) {
			return this.makeCopyOfViewInfo();
		}
		if (DomainError.View.ORIGINAL.equalsIgnoreCase(view)) {
			return this.makeCopyOfViewOriginal();
		}
		return null;
	}
	
	@Override
	public DomainError makeCopyOfViewId() {
		return this.makeCopyOfFields(DomainError.getFieldKeysForViewId());
	}

	@Override
	public DomainError makeCopyOfViewAbstract() {
		return this.makeCopyOfFields(DomainError.getFieldKeysForViewAbstract());
	}
	
	@Override
	public DomainError makeCopyOfViewInfo() {
		return this.makeCopyOfFields(DomainError.getFieldKeysForViewInfo());
	}
	
	@Override
	public DomainError makeCopyOfViewOriginal() {
		return this.makeCopyOfFields(DomainError.getFieldKeysForViewOriginal());
	} 
	
	@Override 
	public void madeToView(String view) { 
    	if (DomainError.View.ID.equals(view)) {
            this.madeToViewId();  
        }
        if (DomainError.View.ABSTRACT.equals(view)) {
            this.madeToViewAbstract();  
        }
        if (DomainError.View.INFO.equals(view)) {
            this.madeToViewInfo();  
        }         
    }
    
	@Override 
    public void madeToViewId() { 
    	List<String> fieldKeysToKeep = DomainError.getFieldKeysForViewId(); 
    	List<String> fieldKeysToSetNil = DomainError.getFieldKeysForViewOriginal(); 
    	fieldKeysToSetNil.removeAll(fieldKeysToKeep);  
    	this.util_setNilForFields(fieldKeysToSetNil);
    } 
    
	@Override 
    public void madeToViewAbstract() { 
    	List<String> fieldKeysToKeep = DomainError.getFieldKeysForViewAbstract(); 
    	List<String> fieldKeysToSetNil = DomainError.getFieldKeysForViewOriginal(); 
    	fieldKeysToSetNil.removeAll(fieldKeysToKeep);  
    	this.util_setNilForFields(fieldKeysToSetNil);
    } 
    
	@Override 
    public void madeToViewInfo() { 
    	List<String> fieldKeysToKeep = DomainError.getFieldKeysForViewInfo(); 
    	List<String> fieldKeysToSetNil = DomainError.getFieldKeysForViewOriginal(); 
    	fieldKeysToSetNil.removeAll(fieldKeysToKeep);  
    	this.util_setNilForFields(fieldKeysToSetNil);
    } 
	
	@Override 
	protected void util_setNilForFields(List<String> fields) { 
		super.util_setNilForFields(fields); 
		
		if (fields == null) { 
			return; 
		} 
		
		if (fields.contains(Field.CODE)) { 
			this.code = null; 
		}
		if (fields.contains(Field.DESCRIPTION)) { 
			this.description = null; 
		} 	
	} 
}
