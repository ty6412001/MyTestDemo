package com.fsd.mvvmlight.freeseeds.entity.query;


		import org.json.JSONObject;

		import java.util.ArrayList;
		import java.util.List;

		import library.helper.JSONHelper.JSONObjectWrapper;


public class QueryFilterOfSpace extends QueryComponent {

	public static final class Field extends QueryComponent.Field {
		public static final String TYPE = "type";
		public static final String CENTERX = "centerX";
		public static final String CENTERY = "centerY";
		public static final String CENTERADDRESS = "centerAddress";
		public static final String RADIUS = "radius";
	}

	public static final class Type {
		public static final String CIRCLE = "circle";//not used yet
	}

	public String type;
	public Double centerX;
	public Double centerY;
	public String centerAddress;
	public Double radius;

	public QueryFilterOfSpace() {
		super();
	}

	public QueryFilterOfSpace(Object filterOfObject) {
		super(filterOfObject);
	}

	public QueryFilterOfSpace(String type, Double centerX, Double centerY, String centerAddress, Double radius) {
		this.self_installFields(type, centerX, centerY, centerAddress, radius);
		this.sub_validateFields();
	}

	protected void self_installFields(String type, Double centerX, Double centerY, String centerAddress, Double radius) {
		super.self_installFields(null);
		this.type = type;
		this.centerX = centerX;
		this.centerY = centerY;
		this.centerAddress = centerAddress;
		this.radius = radius;
	}

	@Override
	protected void sub_decodeFields(JSONObjectWrapper filterJwo) {
		super.sub_decodeFields(filterJwo);
		try {
			if (filterJwo == null) {
				return;
			} else {
				this.type = filterJwo.getString(Field.TYPE);
				this.centerX = filterJwo.getDouble(Field.CENTERX);
				this.centerY = filterJwo.getDouble(Field.CENTERY);
				this.centerAddress = filterJwo.getString(Field.CENTERADDRESS);
				this.radius = filterJwo.getDouble(Field.RADIUS);
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
			filterJoo.put(Field.TYPE, this.type);
			filterJoo.put(Field.CENTERX, this.centerX);
			filterJoo.put(Field.CENTERY, this.centerY);
			filterJoo.put(Field.CENTERADDRESS, this.centerAddress);
			filterJoo.put(Field.RADIUS, this.radius);

		} catch (Exception e) {
			e.printStackTrace();
			filterJoo = new JSONObject();
		}
		return filterJoo;
	}

	@Override
	public int hashCode() {
		int typeHashCode = this.type == null ? 0 : this.type.hashCode();
		int centerXHashCode = this.centerX == null ? 0 : this.centerX.hashCode();
		int centerYHashCode = this.centerY == null ? 0 : this.centerY.hashCode();
//		int centerAddressHashCode = this.centerAddress == null ? 0 : this.centerAddress.hashCode();
		int radiusHashCode = this.radius == null ? 0 : this.radius.hashCode();

		int result = 1;//17;
		result = 31 * result + typeHashCode;
		result = 31 * result + centerXHashCode;
		result = 31 * result + centerYHashCode;
//        result = 31 * result + centerAddressHashCode;
		result = 31 * result + radiusHashCode;
		return result;
	}

	@Override
	public boolean equals(Object spaceFilterObject) {
		if (spaceFilterObject instanceof QueryFilterOfSpace) {
			QueryFilterOfSpace spaceFilter = (QueryFilterOfSpace) spaceFilterObject;
			if (((this.type == null && spaceFilter.type == null) || this.type.equals(spaceFilter.type)) &&
					((this.centerX == null && spaceFilter.centerX == null) || this.centerX.equals(spaceFilter.centerX)) &&
					((this.centerY == null && spaceFilter.centerY == null) || this.centerY.equals(spaceFilter.centerY)) &&
					((this.radius == null && spaceFilter.radius == null) || this.radius.equals(spaceFilter.radius))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public QueryFilterOfSpace makeCopy() {
		return new QueryFilterOfSpace(this.type, this.centerX, this.centerY, this.centerAddress, this.radius);
	}

	public static List<QueryFilterOfSpace> makeCopyList(List<QueryFilterOfSpace> spaceFilterList) {
		List<QueryFilterOfSpace> copyList = null;
		if (spaceFilterList != null) {
			copyList = new ArrayList<QueryFilterOfSpace>();
			for (QueryFilterOfSpace spaceFilter : spaceFilterList) {
				copyList.add(spaceFilter.makeCopy());
			}
		}
		return copyList;
	}
}
