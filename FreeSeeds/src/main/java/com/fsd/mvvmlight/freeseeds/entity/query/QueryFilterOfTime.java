package com.fsd.mvvmlight.freeseeds.entity.query;


		import org.json.JSONObject;

		import java.util.ArrayList;
		import java.util.List;

		import library.helper.JSONHelper.JSONObjectWrapper;


//may not needed, use field filter
public class QueryFilterOfTime extends QueryComponent {

	public static final class Field extends QueryComponent.Field {
		public static final String FIELD = "field";
		public static final String EARLIEST = "earliest";
		public static final String EARLIESTINCLUDED = "earliestIncluded";
		public static final String LATEST = "latest";
		public static final String LATESTINCLUDED = "latestIncluded";
	}

	public String field;
	public Long earliest;
	public Boolean earliestIncluded;
	public Long latest;
	public Boolean latestIncluded;

	public QueryFilterOfTime() {
		super();
	}

	public QueryFilterOfTime(Object filterOfObject) {
		super(filterOfObject);
	}

	public QueryFilterOfTime(String field, Long earliest, Boolean earliestIncluded, Long latest, Boolean latestIncluded) {
		this.self_installFields(field, earliest, earliestIncluded, latest, latestIncluded);
		this.sub_validateFields();
	}

	protected void self_installFields(String field, Long earliest, Boolean earliestIncluded, Long latest, Boolean latestIncluded) {
		super.self_installFields(null);
		this.field = field;
		this.earliest = earliest;
		this.earliestIncluded = earliestIncluded;
		this.latest = latest;
		this.latestIncluded = latestIncluded;
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

		int result = 1;//17;
		result = 31 * result + fieldHashCode;
		result = 31 * result + earliestHashCode;
		result = 31 * result + earliestIncludedHashCode;
		result = 31 * result + latestHashCode;
		result = 31 * result + latestIncludedHashCode;
		return result;
	}

	@Override
	public boolean equals(Object timeFilterObject) {
		if (timeFilterObject instanceof QueryFilterOfTime) {
			QueryFilterOfTime timeFilter = (QueryFilterOfTime) timeFilterObject;
			if (((this.field == null && timeFilter.field == null) || this.field.equals(timeFilter.field)) &&
					((this.earliest == null && timeFilter.earliest == null) || this.earliest.equals(timeFilter.earliest)) &&
					((this.earliestIncluded == null && timeFilter.earliestIncluded == null) || this.earliestIncluded.equals(timeFilter.earliestIncluded)) &&
					((this.latest == null && timeFilter.latest == null) || this.latest.equals(timeFilter.latest)) &&
					((this.latestIncluded == null && timeFilter.latestIncluded == null) || this.latestIncluded.equals(timeFilter.latestIncluded))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public QueryFilterOfTime makeCopy() {
		return new QueryFilterOfTime(this.field, this.earliest, this.earliestIncluded, this.latest, this.latestIncluded);
	}

	public static List<QueryFilterOfTime> makeCopyList(List<QueryFilterOfTime> timeFilterList) {
		List<QueryFilterOfTime> copyList = null;
		if (timeFilterList != null) {
			copyList = new ArrayList<QueryFilterOfTime>();
			for (QueryFilterOfTime timeFilter : timeFilterList) {
				copyList.add(timeFilter.makeCopy());
			}
		}
		return copyList;
	}
}
