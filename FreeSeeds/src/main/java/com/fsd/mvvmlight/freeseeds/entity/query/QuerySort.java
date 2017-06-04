package com.fsd.mvvmlight.freeseeds.entity.query;


		import org.json.JSONObject;

		import java.util.ArrayList;
		import java.util.Arrays;
		import java.util.List;

		import library.helper.JSONHelper.JSONObjectWrapper;


public class QuerySort extends QueryComponent {

	//TODO change field to avoid conflict
	public static final class Field extends QueryComponent.Field {
		public static final String FIELD = "field";
		public static final String DIRECTION = "direction";
	}

	public static final class Direction {
		public static final String ASCEND = "ascend";
		public static final String DESCEND= "descend";
	}

	public static final class SortField {
		public static final String RELEVANCE = "_relevance_";
		public static final String DISTANCE = "_distance_";//this is only for indexers... should move else where. can be sorted by arbitrary field
	}

	public String field;
	public String direction;

	public QuerySort() {
		super();
	}

	public QuerySort(Object sortOfObject) {
		super(sortOfObject);
	}

	public QuerySort(String field, String direction) {
		this.self_installFields(field, direction);
		this.sub_validateFields();
	}

	protected void self_installFields(String field, String direction) {

		super.self_installFields(null);

		this.field = field;
		this.direction = direction;
	}

	@Override
	protected void sub_decodeFields(JSONObjectWrapper sortJwo) {
		super.sub_decodeFields(sortJwo);
		try {
			if (sortJwo == null) {
				return;
			} else {
				this.field = sortJwo.getString(Field.FIELD);
				this.direction = sortJwo.getString(Field.DIRECTION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//set default//throw exception if some fields are null?
	@Override
	protected void sub_validateFields() {
		String[] directions = {Direction.ASCEND, Direction.DESCEND};
		//null also checked
		if (!Arrays.asList(directions).contains(this.direction)) {
			this.direction = Direction.DESCEND;
		}

	}

	@Override
	public JSONObject toJSONObject() {

		JSONObject sortJoo = super.toJSONObject();

		try {
			if (sortJoo == null) {
				sortJoo = new JSONObject();
			}

			sortJoo.put(Field.FIELD, field);
			sortJoo.put(Field.DIRECTION, direction);

		} catch (Exception e) {
			e.printStackTrace();
			sortJoo = new JSONObject();
		}
		return sortJoo;
	}

	@Override
	public int hashCode() {
		int fieldHashCode = this.field == null ? 0 : this.field.hashCode();
		int directionHashCode = this.direction == null ? 0 : this.direction.hashCode();

		int result = 1;//17;
		result = 31 * result + fieldHashCode;
		result = 31 * result + directionHashCode;
		return result;
	}

	@Override
	public boolean equals(Object sortObject) {
		if (sortObject instanceof QuerySort) {
			QuerySort sort = (QuerySort) sortObject;
			if (((this.field == null && sort.field == null) || this.field.equals(sort.field)) &&
					((this.direction == null && sort.direction == null) || this.direction.equals(sort.direction))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public QuerySort makeCopy() {
		return new QuerySort(field, direction);
	}

	public static List<QuerySort> makeCopyList(List<QuerySort> sortList) {
		List<QuerySort> copyList = null;
		if (sortList != null) {
			copyList = new ArrayList<QuerySort>();
			for (QuerySort sort : sortList) {
				copyList.add(sort.makeCopy());
			}
		}
		return copyList;
	}
}

