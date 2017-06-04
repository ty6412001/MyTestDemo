package com.fsd.mvvmlight.freeseeds.entity.query;


		import org.json.JSONObject;

		import java.util.ArrayList;
		import java.util.List;

		import library.helper.JSONHelper.JSONObjectWrapper;


public class QueryFilterOfRelation extends QueryComponent {

	public static final class Field extends QueryComponent.Field {
		public static final String FIELD = "field";//always timeUpdate?
		public static final String EARLIEST = "earliest";
		public static final String EARLIESTINCLUDED = "earliestIncluded";
		public static final String LATEST = "latest";
		public static final String LATESTINCLUDED = "latestIncluded";
		public static final String ISGLOBAL = "isGlobal";
	}

	public String field;
	public Long earliest;
	public Boolean earliestIncluded;
	public Long latest;
	public Boolean latestIncluded;
	public Boolean isGlobal; //apply filter to all nodes in a relation branch or any node //???

	public QueryFilterOfRelation() {
		super();
	}

	public QueryFilterOfRelation(Object filterOfObject) {
		super(filterOfObject);
	}

	public QueryFilterOfRelation(String field, Long earliest, Boolean earliestIncluded, Long latest, Boolean latestIncluded, Boolean isGlobal) {
		this.self_installFields(field, earliest, earliestIncluded, latest, latestIncluded, isGlobal);
		this.sub_validateFields();
	}

	protected void self_installFields(String field, Long earliest, Boolean earliestIncluded, Long latest, Boolean latestIncluded, Boolean isGlobal) {
		super.self_installFields(null);
		this.field = field;
		this.earliest = earliest;
		this.earliestIncluded = earliestIncluded;
		this.latest = latest;
		this.latestIncluded = latestIncluded;
		this.isGlobal = isGlobal;
	}

	@Override
	protected void sub_decodeFields(JSONObjectWrapper filterJwo) {
		super.sub_decodeFields(filterJwo);
		try {
			if (filterJwo == null) {
				return;
			} else {
				this.field = filterJwo.getString(Field.FIELD);
				this.earliest = filterJwo.getLong(Field.EARLIEST);
				this.earliestIncluded = filterJwo.getBoolean(Field.EARLIESTINCLUDED);
				this.latest = filterJwo.getLong(Field.LATEST);
				this.latestIncluded = filterJwo.getBoolean(Field.LATESTINCLUDED);
				this.isGlobal = filterJwo.getBoolean(Field.ISGLOBAL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//TODO
	@Override
	protected void sub_validateFields() {

	}

	@Override
	public JSONObject toJSONObject() {

		JSONObject filterJoo = super.toJSONObject();

		try {
			if (filterJoo == null) {
				filterJoo = new JSONObject();
			}
			filterJoo.put(Field.FIELD, this.field);
			filterJoo.put(Field.EARLIEST, this.earliest);
			filterJoo.put(Field.EARLIESTINCLUDED, this.earliestIncluded);
			filterJoo.put(Field.LATEST, this.latest);
			filterJoo.put(Field.LATESTINCLUDED, this.latestIncluded);
			filterJoo.put(Field.ISGLOBAL, this.isGlobal);

		} catch (Exception e) {
			e.printStackTrace();
			filterJoo = new JSONObject();
		}
		return filterJoo;
	}

	@Override
	public int hashCode() {
		int fieldHashCode = this.field == null ? 0 : this.field.hashCode();
		int earliestHashCode = this.earliest == null ? 0 : this.earliest.hashCode();
		int earliestIncludedHashCode = this.earliestIncluded == null ? 0 : this.earliestIncluded.hashCode();
		int latestHashCode = this.latest == null ? 0 : this.latest.hashCode();
		int latestIncludedHashCode = this.latestIncluded == null ? 0 : this.latestIncluded.hashCode();
		int isGlobalHashCode = this.isGlobal == null ? 0 : this.isGlobal.hashCode();

		int result = 1;//17;
		result = 31 * result + fieldHashCode;
		result = 31 * result + earliestHashCode;
		result = 31 * result + earliestIncludedHashCode;
		result = 31 * result + latestHashCode;
		result = 31 * result + latestIncludedHashCode;
		result = 31 * result + isGlobalHashCode;
		return result;
	}

	@Override
	public boolean equals(Object relationFilterObject) {
		if (relationFilterObject instanceof QueryFilterOfRelation) {
			QueryFilterOfRelation relationFilter = (QueryFilterOfRelation) relationFilterObject;
			if (((this.field == null && relationFilter.field == null) || this.field.equals(relationFilter.field)) &&
					((this.earliest == null && relationFilter.earliest == null) || this.earliest.equals(relationFilter.earliest)) &&
					((this.earliestIncluded == null && relationFilter.earliestIncluded == null) || this.earliestIncluded.equals(relationFilter.earliestIncluded)) &&
					((this.latest == null && relationFilter.latest == null) || this.latest.equals(relationFilter.latest)) &&
					((this.latestIncluded == null && relationFilter.latestIncluded == null) || this.latestIncluded.equals(relationFilter.latestIncluded)) &&
					((this.isGlobal == null && relationFilter.isGlobal == null) || this.isGlobal.equals(relationFilter.isGlobal))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public QueryFilterOfRelation makeCopy() {
		return new QueryFilterOfRelation(this.field, this.earliest, this.earliestIncluded, this.latest, this.latestIncluded, this.isGlobal);
	}

	public static List<QueryFilterOfRelation> makeCopyList(List<QueryFilterOfRelation> relationFilterList) {
		List<QueryFilterOfRelation> copyList = null;
		if (relationFilterList != null) {
			copyList = new ArrayList<QueryFilterOfRelation>();
			for (QueryFilterOfRelation relationFilter : relationFilterList) {
				copyList.add(relationFilter.makeCopy());
			}
		}
		return copyList;
	}

	public boolean eval(Long fieldValue) {
		boolean rt = true;
		if (this.earliest != null && this.earliestIncluded != null) {
			if (this.earliestIncluded && fieldValue < this.earliest) {
				rt = false;
				return rt;
			}
			if (!this.earliestIncluded && fieldValue <= this.earliest) {
				rt = false;
				return rt;
			}
		}
		if (this.latest != null && this.latestIncluded != null) {
			if (this.latestIncluded && fieldValue > this.latest) {
				rt = false;
				return rt;
			}
			if (!this.latestIncluded && fieldValue >= this.latest) {
				rt = false;
				return rt;
			}
		}
		return rt;
	}
}

