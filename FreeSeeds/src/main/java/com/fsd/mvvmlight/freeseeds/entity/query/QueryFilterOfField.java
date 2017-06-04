package com.fsd.mvvmlight.freeseeds.entity.query;


		import com.fsd.mvvmlight.freeseeds.exception.ListException;

		import org.json.JSONArray;
		import org.json.JSONObject;

		import java.util.ArrayList;
		import java.util.List;

		import library.helper.JSONHelper;
		import library.helper.StringHelper;
		import library.helper.JSONHelper.JSONObjectWrapper;


public class QueryFilterOfField extends QueryComponent {

	public static final class Field extends QueryComponent.Field {
		public static final String OPERATOR = "operator";
		public static final String FIELD = "field";
		public static final String VALUE = "value";
		public static final String VALUETYPE = "valueType";
	}

	public static final class Operator {
		public static final String GREATERTHAN = "gt";
		public static final String GREATERTHANEQUAL= "gte";
		public static final String LESSERTHAN = "lt";
		public static final String LESSERTHANEQUAL = "lte";
		public static final String EQUAL = "eq";
		public static final String IN = "in";
		public static final String CONTAIN = "ctn";//set/list contains another set/list
		public static final String LIKE = "like";//search pattern
	}

	public static final class ValueType {
		public static final String STRING = "string";//when used for in, it is the type of result list of items
		public static final String UUID = "uuid";//not implemented yet
		public static final String INT = "int";
		public static final String LONG = "long";
		public static final String DOUBLE = "double";
	}

	public String operator;
	public String field;
	public Object value;
	public String valueType;

	public QueryFilterOfField() {
		super();
	}

	public QueryFilterOfField(Object filterOfObject) {
		super(filterOfObject);
	}

	public QueryFilterOfField(String operator, String field, Object value, String valueType) {
		this.self_installFields(operator, field, value, valueType);
		this.sub_validateFields();
	}

	protected void self_installFields(String operator, String field, Object value, String valueType) {

		super.self_installFields(null);

		this.operator = operator;
		this.field = field;
		this.value = value;
		this.valueType = valueType;

	}

	@Override
	protected void sub_decodeFields(JSONObjectWrapper filterJwo) {
		super.sub_decodeFields(filterJwo);
		try {
			if (filterJwo == null) {
				return;
			} else {
				this.operator = filterJwo.getString(Field.OPERATOR);
				this.field = filterJwo.getString(Field.FIELD);
				this.valueType = filterJwo.getString(Field.VALUETYPE);
				//may change original collection to list, but still better than JSONArray representation for using collection contains etc. method for evaluating some operators like contain, in
				Object valueOfObject = filterJwo.get(Field.VALUE);
				if (valueOfObject instanceof JSONArray) {
					this.value = QueryFilterOfField.getFilterValueAsList(valueOfObject, this.valueType);
				} else {
					this.value = valueOfObject;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void sub_validateFields() {
		//throw exception if some fields are null here?
	}

	@Override
	public JSONObject toJSONObject() {

		JSONObject filterJoo = super.toJSONObject();

		try {
			if (filterJoo == null) {
				filterJoo = new JSONObject();
			}

			filterJoo.put(Field.OPERATOR, this.operator);
			filterJoo.put(Field.FIELD, this.field);
			JSONHelper.putObjectWithClassCheck(filterJoo, Field.VALUE, this.value);//if simply put object, list will become string when deserializing
			filterJoo.put(Field.VALUETYPE, this.valueType);

//			if (filterJoo.length() == 0) {
//				filterJoo = null;
//			}
		} catch (Exception e) {
			e.printStackTrace();
			filterJoo = new JSONObject();
		}
		return filterJoo;
	}

	@Override
	public int hashCode() {
		int operatorHashCode = this.operator == null ? 0 : this.operator.hashCode();
		int fieldHashCode = this.field == null ? 0 : this.field.hashCode();
		int valueHashCode = this.value == null ? 0 : this.value.hashCode();

		int result = 1;//17;
		result = 31 * result + operatorHashCode;
		result = 31 * result + fieldHashCode;
		result = 31 * result + valueHashCode;
		return result;
	}

	@Override
	public boolean equals(Object fieldFilterObject) {
		if (fieldFilterObject instanceof QueryFilterOfField) {
			QueryFilterOfField fieldFilter = (QueryFilterOfField) fieldFilterObject;
			if (((this.operator == null && fieldFilter.operator == null) || this.operator.equals(fieldFilter.operator)) &&
					((this.field == null && fieldFilter.field == null) || this.field.equals(fieldFilter.field)) &&
					((this.value == null && fieldFilter.value == null) || this.value.equals(fieldFilter.value))) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static List<QueryFilterOfField> makeFilterListFromQueryComponentListOfObject(Object queryComponentListOfObject) throws Exception {

		List<QueryFilterOfField> queryComponentList = null;

		if (queryComponentListOfObject == null) {
			return queryComponentList;
		}

		List<Object> queryComponentOfObjectList = null;
		//assume it is a jas, not handling list string
		if (queryComponentListOfObject instanceof String) {
			JSONArray entityListOfJao = new JSONArray((String) queryComponentListOfObject);
			queryComponentOfObjectList = JSONHelper.transformJSONArrayToList(entityListOfJao);
		} else if (queryComponentListOfObject instanceof JSONArray) {
			JSONArray entityListOfJao = (JSONArray) queryComponentListOfObject;
			queryComponentOfObjectList = JSONHelper.transformJSONArrayToList(entityListOfJao);
		} else if (queryComponentListOfObject instanceof List) {
			queryComponentOfObjectList = (List<Object>) queryComponentListOfObject;
		} else {
			//raise unsupported exception?
			String message = ListException.createExceptionMessage(null, "unsupported Object for filter list deserialization");
			throw new ListException(message);
		}

		if (queryComponentOfObjectList == null) {
			return null;
		}

		queryComponentList = new ArrayList<QueryFilterOfField>();
		for (int i=0; i< queryComponentOfObjectList.size(); i++) {
			Object filterOfObject = queryComponentOfObjectList.get(i);
			QueryFilterOfField filter = new QueryFilterOfField(filterOfObject);
			queryComponentList.add(filter);
		}
		return queryComponentList;
	}

	//for in clause
	//TOTEST
	@SuppressWarnings("unchecked")
	public static List<Object> getFilterValueAsList(Object valueOfObject, String valueType) {

		List<Object> valueOfList = null;

		if (valueOfObject == null) {
			return valueOfList;
		}

		if (valueOfObject instanceof List) {
			valueOfList = (List<Object>) valueOfObject;
		}
		else if (valueOfObject instanceof JSONArray) {
			valueOfList = JSONHelper.transformJSONArrayToList((JSONArray) valueOfObject);
		}
		else if (valueOfObject instanceof String) {
			try {
				JSONArray valueOfJao = new JSONArray((String) valueOfObject);
				valueOfList = JSONHelper.transformJSONArrayToList(valueOfJao);
			} catch (Exception e) {
				valueOfList = null;
			}
		}

		if (valueOfList == null) {
			String[] array = valueOfObject.toString().split(",");//may have bug if a parsable number treated as string in table
			valueOfList = new ArrayList<Object>();
			if (valueType == null) {
				for (int i=0; i< array.length; i++) {
					String valueItemStr = array[i].trim();
					if (StringHelper.isIntString(valueItemStr)) {
						valueOfList.add(Integer.parseInt(valueItemStr));
					}
					else if (StringHelper.isLongString(valueItemStr)) {
						valueOfList.add(Long.parseLong(valueItemStr));
					}
					else if (StringHelper.isDoubleString(valueItemStr)) {
						valueOfList.add(Double.parseDouble(valueItemStr));
					}
					else {
						valueOfList.add(valueItemStr);
					}
				}
			} else if (valueType.equalsIgnoreCase(ValueType.DOUBLE)) {
				for (int i=0; i< array.length; i++) {
					String valueItemStr = array[i].trim();
					if (StringHelper.isDoubleString(valueItemStr)) {//still check, may throw exception at this point
						valueOfList.add(Double.parseDouble(valueItemStr));
					}
				}
			} else if (valueType.equalsIgnoreCase(ValueType.INT)) {
				for (int i=0; i< array.length; i++) {
					String valueItemStr = array[i].trim();
					if (StringHelper.isIntString(valueItemStr)) {//still check, may throw exception at this point
						valueOfList.add(Integer.parseInt(valueItemStr));
					}
				}
			} else if (valueType.equalsIgnoreCase(QueryFilterOfField.ValueType.LONG)) {
				for (int i=0; i< array.length; i++) {
					String valueItemStr = array[i].trim();
					if (StringHelper.isLongString(valueItemStr)) {//still check, may throw exception at this point
						valueOfList.add(Long.parseLong(valueItemStr));
					}
				}
			} else {//other cases treat as string
				for (int i=0; i< array.length; i++) {
					String valueItemStr = array[i].trim();
					valueOfList.add(valueItemStr);
				}
			}
		}
		return valueOfList;
	}

	@Override
	public QueryFilterOfField makeCopy() {
		return new QueryFilterOfField(this.operator, this.field, this.value, this.valueType);//assume value object is passed by value...
	}

	public static List<QueryFilterOfField> makeCopyList(List<QueryFilterOfField> fieldFilterList) {
		List<QueryFilterOfField> copyList = null;
		if (fieldFilterList != null) {
			copyList = new ArrayList<QueryFilterOfField>();
			for (QueryFilterOfField fieldFilter : fieldFilterList) {
				copyList.add(fieldFilter.makeCopy());
			}
		}
		return copyList;
	}
}
