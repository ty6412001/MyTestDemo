package com.fsd.mvvmlight.freeseeds.entity.event;


import com.fsd.mvvmlight.freeseeds.entity.DomainError;
import com.fsd.mvvmlight.freeseeds.entity.Query;
import com.fsd.mvvmlight.freeseeds.exception.ListException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import library.helper.JSONHelper.*;

public class EventFreeRetrieveB extends EventFreeB {

	public List<Query> queries;
	public EventFreeRetrieveB() {
		super();
	}

	public EventFreeRetrieveB(Object eventOfObject) throws Exception {
		super(eventOfObject);
	}

	//this constructor is required for client sdk//login considered as retrieve so need authAliasId authAliasIdType and password here...
	public EventFreeRetrieveB(String eventType, String eventLabel, Long eventTime,
								 String authId, String authToken, String authAliasId, String authAliasIdType, String authPassword,
								 Map<String, Object> options,
								 List<Query> queries) throws Exception {
		this.self_installFields(eventType, eventLabel, eventTime,
				authId, authToken, authAliasId, authAliasIdType, authPassword,
				options,
				queries);
		this.sub_validateFields();
	}

	protected void self_installFields(String eventType, String eventLabel, Long eventTime,
									  String authId, String authToken, String authAliasId, String authAliasIdType, String authPassword,
									  Map<String, Object> options,
									  List<Query> queries) throws Exception {

		super.self_installFields(eventType, eventLabel, eventTime,
				authId, authToken, authAliasId, authAliasIdType, authPassword, null,
				options);

		this.queries = queries;
	}

	@Override
	protected void sub_decodeFields(JSONObjectWrapper eventJwo) throws Exception {
		super.sub_decodeFields(eventJwo);
		if (eventJwo == null) {
			return;
		} else {
			List<Object> queryOfObjectList= eventJwo.getValueAsList(Field.QUERIES);
			this.queries = Query.makeQueryListFromEntityListOfObject(queryOfObjectList);
		}
	}

	@Override
	protected void sub_validateFields() throws Exception {
		if (this.queries != null) {
			for (Query query : this.queries) {
				if (query.targetClass == null) {
					String exceptionMessage = ListException.createExceptionMessage(DomainError.Code.VALIDATE_DOMAINQUERY_MISSINGFIELD_ENTITYCLASS, DomainError.Description.VALIDATE_DOMAINQUERY_MISSINGFIELD_ENTITYCLASS);
					throw new ListException(exceptionMessage);
				}
			}
		}
		//move to individual since search does not needs this
//		if (this.authId == null || this.authToken == null) {
//			String exceptionMessage = ListException.createExceptionMessage(Error.ErrorConstants.VALIDATE_EVENTINVALIDFIELDAUTH_CODE, Error.ErrorConstants.VALIDATE_EVENTINVALIDFIELDAUTH_DESCRIPTION);
//			throw new ListException(exceptionMessage);
//		}

		//user login does not need this check...
//		if (this.queries == null) {
//	    	String message = ListException.createExceptionMessage(Error.ErrorConstants.VALIDATE_EVENTINVALIDFIELDQUERIES_CODE, Error.ErrorConstants.VALIDATE_EVENTINVALIDFIELDQUERIES_DESCRIPTION);//to do message
//    		throw new ListException(message);
//	    }
		//put to individual subclass to set default sicne they may extends view or may not require view
//		for (DomainQuery query: this.queries) {
//			if (!EntityB.getViewValues().contains(query.view)) {
//				query.view = EntityB.View.INFO;
//			}
//		}
	}

	@Override
	public synchronized JSONObject toJSONObject() {//for json, probably return a copy instead, immutable, sychronize on access method if needed
		JSONObject eventJoo = super.toJSONObject();
		try {
			if (eventJoo == null) {
				eventJoo = new JSONObject();
			}

			JSONArray queriesJao = Query.makeJSONArrayFromEntityList(this.queries);
			eventJoo.put(Field.QUERIES, queriesJao);

		} catch (Exception e) {
			e.printStackTrace();
			eventJoo = new JSONObject();
		}
		return eventJoo;
	}

	public String getOptionArchiverDatabase() {
		String option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.ARCHIVERDATABASE)) {
				option = (String) this.options.get(OptionsField.ARCHIVERDATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}

	public String getOptionArchiverTableTopic() {
		String option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.ARCHIVERTABLETOPIC)) {
				option = (String) this.options.get(OptionsField.ARCHIVERTABLETOPIC);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}

	public String getOptionArchiverTableSession() {
		String option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.ARCHIVERTABLESESSION)) {
				option = (String) this.options.get(OptionsField.ARCHIVERTABLESESSION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}

	public String getOptionArchiverTableMessage() {
		String option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.ARCHIVERTABLEMESSAGE)) {
				option = (String) this.options.get(OptionsField.ARCHIVERTABLEMESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}

	public String getOptionArchiverTableUser() {
		String option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.ARCHIVERTABLEUSER)) {
				option = (String) this.options.get(OptionsField.ARCHIVERTABLEUSER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}

	public String getOptionIndexerIndex() {
		String option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.INDEXERINDEX)) {
				option = (String) this.options.get(OptionsField.INDEXERINDEX);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;

	}

	public Long getOptionTopicTTL() {
		Long option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.TOPICTTL)) {
				option = (Long) this.options.get(OptionsField.TOPICTTL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}

	//TODO contains valid ?
	public boolean containsDomainQueryWithRelationFilter() {
		for (Query query: this.queries) {
			if (query.relationFilter != null) {
				return true;
			}
		}
		return false;
	}
}