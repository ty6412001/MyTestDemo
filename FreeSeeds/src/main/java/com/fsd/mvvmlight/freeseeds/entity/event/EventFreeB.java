package com.fsd.mvvmlight.freeseeds.entity.event;


import org.json.JSONObject;

import java.util.Map;
import library.helper.JSONHelper;
import library.helper.JSONHelper.*;
import library.helper.MapHelper;

public abstract class EventFreeB extends Event implements EventFreeI {
	
	public static final class Field {
		public static final String AUTHID = "authId";		
		public static final String AUTHTOKEN = "authToken";
		public static final String AUTHALIASID = "authAliasId"; 
		public static final String AUTHALIASIDTYPE = "authAliasIdType"; 
		public static final String AUTHPASSWORD = "authPassword";
		public static final String AUTHPASSWORDOLD = "authPasswordOld";//only for changePassword event 
		public static final String AUTHVERIFICATIONCODE = "authVerificationCode"; //only for userRegister, userMerge, userChangePassword, not a common field 
		public static final String OPTIONS = "options";
		public static final String ENTITIES = "entities";//for operation events including query operation events
		public static final String QUERIES = "queries";//for search/retrieve events 
		public static final String QUERY = "query";//used in messageCreate which retrieve messages from a time point before the message created 		
	}
	
	public static final class EventType {

		public static final String USERCREATE = "userCreate";
		public static final String USERCREATERETURN = "userCreateReturn";

		public static final String USERREGISTER = "userRegister";
		public static final String USERREGISTERRETURN = "userRegisterReturn";

		public static final String USERMERGE = "userMerge";
		public static final String USERMERGERETURN = "userMergeReturn";

		public static final String USERUPDATE = "userUpdate";
		public static final String USERUPDATERETURN = "userUpdateReturn";
		
		public static final String USERSEARCH = "userSearch";
		public static final String USERSEARCHRETURN = "userSearchReturn";

		public static final String USERRETRIEVE = "userRetrieve";
		public static final String USERRETRIEVERETURN = "userRetrieveReturn";

		public static final String USERLOGIN = "userLogin";
		public static final String USERLOGINRETURN = "userLoginReturn"; 
		
		public static final String USERCHECKIN = "userCheckin"; //used to renew token 
		public static final String USERCHECKINRETURN = "userCheckinReturn"; 

		public static final String USERCHANGEPASSWORD = "userChangePassword";
		public static final String USERCHANGEPASSWORDRETURN = "userChangePasswordReturn"; 
		
		public static final String USERUPDATEVERIFICATIONCODE = "userUpdateVerificationCode";
		public static final String USERUPDATEVERIFICATIONCODERETURN = "userUpdateVerificationCodeReturn"; 

		public static final String TOPICCREATE = "topicCreate";
		public static final String TOPICCREATERETURN = "topicCreateReturn";

		public static final String TOPICSEARCH = "topicSearch";
		public static final String TOPICSEARCHRETURN = "topicSearchReturn";

		public static final String TOPICRETRIEVE = "topicRetrieve";
		public static final String TOPICRETRIEVERETURN = "topicRetrieveReturn";

		public static final String TOPICUPDATE = "topicUpdate";
		public static final String TOPICUPDATERETURN = "topicUpdateReturn";

		public static final String TOPICDELETE = "topicDelete";
		public static final String TOPICDELETERETURN = "topicDeleteReturn";

		public static final String SESSIONCREATE = "sessionCreate";
		public static final String SESSIONCREATERETURN = "sessionCreateReturn";
		
//		public static final String SESSIONSEARCH = "sessionSearch";
//		public static final String SESSIONSEARCHRETURN = "sessionSearchReturn";

		public static final String SESSIONRETRIEVE = "sessionRetrieve";
		public static final String SESSIONRETRIEVERETURN = "sessionRetrieveReturn";

		public static final String SESSIONDELETE = "sessionDelete";
		public static final String SESSIONDELETERETURN = "sessionDeleteReturn";

		public static final String SESSIONUPDATE = "sessionUpdate";//lifecycle//membership change?
		public static final String SESSIONUPDATERETURN = "sessionUpdateReturn"; 

		public static final String MESSAGECREATE = "messageCreate";
		public static final String MESSAGECREATERETURN = "messageCreateReturn";
		
		public static final String MESSAGERETRIEVE = "messageRetrieve";
		public static final String MESSAGERETRIEVERETURN = "messageRetrieveReturn";
		
		public static final String MESSAGEDELETE = "messageDelete";
		public static final String MESSAGEDELETERETURN = "messageDeleteReturn";

		public static final String MEDIACREATE = "mediaCreate";
		public static final String MEDIACREATERETURN = "mediaCreateReturn";
		public static final String MEDIAUPDATE = "mediaUpdate";
		public static final String MEDIAUPDATERETURN = "mediaUpdateReturn";
		public static final String MEDIADELETE = "mediaDelete";
		public static final String MEDIADELETERETURN = "mediaDeleteReturn";
		
		public static final String QUERYCREATE = "queryCreate";
		public static final String QUERYCREATERETURN = "queryCreateReturn";
		public static final String QUERYUPDATE = "queryUpdate";
		public static final String QUERYUPDATERETURN = "queryUpdateReturn";
		public static final String QUERYDELETE = "queryDelete";
		public static final String QUERYDELETERETURN = "queryDeleteReturn";
		public static final String QUERYRETRIEVE = "queryRetrieve";//This event may not be useful but for testing. There seems no scenario where we need to retrieve queries by id
		public static final String QUERYRETRIEVERETURN = "queryRetrieveReturn";
		
		//???what use
		public static final String COMMENTRETRIEVE = "commentRetrieve";
	}
	
	//Most of the options below will be overwritten at server side  
	public static class OptionsField {
		public static final String STORAGEWRITEMODE = "storageWriteMode"; 
		public static final String STORAGEBUCKETCLOUDS3 = "storageBucketCloudS3";
		public static final String STORAGEBUCKETLOCALFILESYSTEM = "storageBucketLocalFileSystem";
		public static final String STORAGEBUCKETCLOUDS3DEFAULT = "storageBucketCloudS3Default";
		public static final String STORAGEBUCKETLOCALFILESYSTEMDEFAULT = "storageBucketLocalFileSystemDefault";
		public static final String TOPICTTL = "topicTTL"; 
		public static final String TOPICTTLMAXFORUPDATE = "topicTTLMaxForUpdate"; 
		public static final String TOPICTTLDISTANCEMAXFORUPDATE = "topicTTLDistanceMaxForUpdate"; 
		public static final String INDEXERINDEX = "indexerIndex";
		public static final String ARCHIVERDATABASE = "archiverDatabase";
		public static final String ARCHIVERTABLETOPIC = "archiverTableTopic";
		public static final String ARCHIVERTABLESESSION = "archiverTableSession";
		public static final String ARCHIVERTABLEMESSAGE = "archiverTableMessage";
		public static final String ARCHIVERTABLEQUERY = "archiverTableQuery";
		public static final String ARCHIVERTABLEUSER = "archiverTableUser"; 
		public static final String DODELETERELATIONS = "doDeleteRelations"; 
		public static final String UPDATERELATIONSSESSIONSHASLIFECYCLE = "updateRelationsSessionsHasLifecycle"; 
//		public static final String REGISTERCODEDIGITCOUNT = "registerCodeDigitCount";  
//		public static final String REGISTERCODETTLINSECOND = "registerCodeTTLInSecond"; 
		public static final String AUTHENTICATORVERIFICATIONCODETYPE = "authenticatorVerificationCodeType"; 
		public static final String AUTHORIZERREGISTEREDUSERONLY = "authorizerRegisteredUserOnly"; //true or false 
	} 
	
	public static final class OptionsValue {
		public static final String STORAGEWRITEMODE_UPDATE = "update";
		public static final String STORAGEWRITEMODE_KEEP = "keep";
		public static final String STORAGEWRITEMODE_ERROR = "error"; 
		
		public static final String AUTHENTICATORVERIFICATIONCODETYPE_REGISTER = "register"; 
		public static final String AUTHENTICATORVERIFICATIONCODETYPE_CHANGEPASSWORD = "changePassword"; 
	} 

	public String authId;//public or use method
	public String authToken;
	public String authAliasId;//use aliasId in User entity instead 
	public String authAliasIdType; 
	public String authPassword;
	public String authPasswordOld;//changed from authPasswordNew  
	protected Map<String, Object> options;

	public EventFreeB() {
		super();
	}

	//this constructor is for client
	public EventFreeB(String eventType, String eventLabel, Long eventTime,
						 String authId, String authToken, String authAliasId, String authAliasIdType, String authPassword, String authPasswordOld,
						 Map<String, Object> options) throws Exception {

		this.self_installFields(eventType, eventLabel, eventTime,
				authId, authToken, authAliasId, authAliasIdType, authPassword, authPasswordOld,
				options);

		this.sub_validateFields();
	}

	public EventFreeB(Object eventOfObject) throws Exception {
		super(eventOfObject);
	}

	protected void self_installFields(String eventType, String eventLabel, Long eventTime,
									  String authId, String authToken, String authAliasId, String authAliasIdType, String authPassword, String authPasswordOld,
									  Map<String, Object> options) {

		super.self_installFields(eventType, null, eventLabel, eventTime, null, null, null, null, null);

		this.authId = authId;
		this.authToken = authToken;
		this.authAliasId = authAliasId;
		this.authAliasIdType = authAliasIdType;
		this.authPassword = authPassword;
		this.authPasswordOld = authPasswordOld;
		this.options = options;
//		//make sure options not null to avoid check//use maphelper to get with null check or just check null first
//		if (this.options == null) {
//			this.options = new HashMap<String, Object>();
//		}
	}

	@Override
	protected void sub_decodeFields(JSONObjectWrapper eventJwo) throws Exception {
		super.sub_decodeFields(eventJwo);
		try {
			if (eventJwo == null) {
				//TOCHECK neet set options to empty map?
				return;
			} else {
				this.authId = eventJwo.getString(Field.AUTHID);
				this.authToken = eventJwo.getString(Field.AUTHTOKEN);
				this.authAliasId = eventJwo.getString(Field.AUTHALIASID);
				this.authAliasIdType = eventJwo.getString(Field.AUTHALIASIDTYPE);
				this.authPassword = eventJwo.getString(Field.AUTHPASSWORD);
				this.authPasswordOld = eventJwo.getString(Field.AUTHPASSWORDOLD);
				this.options = eventJwo.getValueAsMap(Field.OPTIONS);
//				//make sure options not null to avoid check or just check null first
//				if (this.options == null) {
//					this.options = new HashMap<String, Object>();
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void sub_validateFields() throws Exception {

	}

	@Override
	public synchronized JSONObject toJSONObject() {//for json, probably return a copy instead, immutable, sychronize on access method if needed

		JSONObject eventJoo = super.toJSONObject();

		try {
			if (eventJoo == null) {
				eventJoo = new JSONObject();
			}
			eventJoo.put(Field.AUTHID, this.authId);
			eventJoo.put(Field.AUTHTOKEN, this.authToken);
			eventJoo.put(Field.AUTHALIASID, this.authAliasId);
			eventJoo.put(Field.AUTHALIASIDTYPE, this.authAliasIdType);
			eventJoo.put(Field.AUTHPASSWORD, this.authPassword);
			eventJoo.put(Field.AUTHPASSWORDOLD, this.authPasswordOld);

			JSONObject optionJoo = JSONHelper.transformMapToJSONObject(this.options);
			eventJoo.put(Field.OPTIONS, optionJoo);

		} catch (Exception e) {
			e.printStackTrace();
			eventJoo = new JSONObject();
		}
		return eventJoo;
	}

	//common options
	public String getOptionStorageWriteMode() {
		String option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.STORAGEWRITEMODE)) {
				option = (String) this.options.get(OptionsField.STORAGEWRITEMODE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			option = null;
		}
		return option;

	}

	public Map<String, String> getOptionStorageBucketCloudS3() {//maybe use for String... parameter
		return MapHelper.getValueAsMap(this.options, OptionsField.STORAGEBUCKETCLOUDS3);
	}

	public Map<String, String> getOptionStorageBucketLocalFileSystem() {
		return MapHelper.getValueAsMap(this.options, OptionsField.STORAGEBUCKETLOCALFILESYSTEM);
	}

	public String getOptionStorageBucketCloudS3Default() {//maybe use for String... parameter
		String option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.STORAGEBUCKETCLOUDS3DEFAULT)) {
				option = (String) this.options.get(OptionsField.STORAGEBUCKETCLOUDS3DEFAULT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}

	public String getOptionStorageBucketLocalFileSystemDefault() {
		String option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.STORAGEBUCKETLOCALFILESYSTEMDEFAULT)) {
				option = (String) this.options.get(OptionsField.STORAGEBUCKETLOCALFILESYSTEMDEFAULT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
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

	public String getOptionArchiverTableQuery() {
		String option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.ARCHIVERTABLEQUERY)) {
				option = (String) this.options.get(OptionsField.ARCHIVERTABLEQUERY);
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
				Object optionObj = this.options.get(OptionsField.TOPICTTL);
				if (optionObj instanceof Long) {
					option = (Long) optionObj;
				} else {
					option = Long.parseLong(optionObj.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}

	public Long getOptionTopicTTLMaxForUpdate() {
		Long option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.TOPICTTLMAXFORUPDATE)) {
				Object optionObj = this.options.get(OptionsField.TOPICTTLMAXFORUPDATE);
				if (optionObj instanceof Long) {
					option = (Long) optionObj;
				} else {
					option = Long.parseLong(optionObj.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}

	public Long getOptionTopicTTLDistanceMaxForUpdate() {
		Long option = null;
		try {
			if (this.options != null && this.options.containsKey(OptionsField.TOPICTTLDISTANCEMAXFORUPDATE)) {
				Object optionObj = this.options.get(OptionsField.TOPICTTLDISTANCEMAXFORUPDATE);
				if (optionObj instanceof Long) {
					option = (Long) optionObj;
				} else {
					option = Long.parseLong(optionObj.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}



}
