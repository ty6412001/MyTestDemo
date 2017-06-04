package com.fsd.mvvmlight.freeseeds.client;

public class ErrorContext { 
			//Code structure XXXYYYYY, where XXX is error group code YYYYY is error detail code 
			//Error group (temporary, only auth error code 101 used): 000-099 reserved, 200 data validation error, 201 authentication error, 202 authorization error  
			public class Code { 					
				public static final int UNKNOWN = 10000000;

				public static final int VALIDATE_INVALIDEVENTFIELD_EVENTTYPE = 1000;

				public static final int VALIDATE_EXCESSENTITIES = 1000;
				public static final int VALIDATE_EXCESSQUERIES = 1000;

				public static final int VALIDATE_INSUFFICIENTENTITIES = 1000;

				// public static final int VALIDATE_INVALIDSTATE_MEDIA = 1100;
				// public static final int VALIDATE_INVALIDSTATE_USER = 1101;
				public static final int VALIDATE_INVALIDTYPE_USER = 1002;
				public static final int VALIDATE_INVALIDSPACEPOSITION = 1003;

				public static final int VALIDATE_MISSINGEVENTFIELD_AUTH = 1007;

				public static final int VALIDATE_MISSINGALIASID_USER = 1104; 
				public static final int VALIDATE_MISSINGALIASIDTYPE_USER = 1104;
				public static final int VALIDATE_UNPERMITTEDENTITYFIELD_ALIASID = 1104;
				public static final int VALIDATE_UNPERMITTEDENTITYFIELD_ID = 1104;
				public static final int VALIDATE_MISSINGID_USER = 1104;

				public static final int VALIDATE_INCONSISTENTAUTHINFO = 1105;

				public static final int VALIDATE_AUTHIDNOTMATCH = 1001;

				//TODO now both entity and query use these codes, either change to thing or separate 
				public static final int VALIDATE_INVALIDENTITYSTATE = 1005; 
				public static final int VALIDATE_INVALIDENTITYLIFECYCLE = 1005; 
				public static final int VALIDATE_INVALIDENTITYFIELD = 1005;
				
				public static final int VALIDATE_MISSINGEVENTFIELD_ENTITIES = 1005;

				public static final int VALIDATE_MISSINGEVENTFIELD_QUERIES = 1005;

				public static final int VALIDATE_MISSINGENTITYFIELD_ID = 1006;

				public static final int VALIDATE_MISSINGENTITYFIELD_SPACEPOSITION = 1007;

				public static final int VALIDATE_UNPERMITTEDENTITYFIELD_USERCREATOR = 1002;

				public static final int VALIDATE_UNPERMITTEDENTITYFIELD_TIMECREATE = 1003;

//				public static final int VALIDATE_MISSINGQUERYFIELD_ENTITYCLASS = 1007;

				public static final int VALIDATE_MISSINGQUERYFIELD_FIELDFILTERS = 1007;

				public static final int VALIDATE_INVALIDEVENTFIELD = 1007; 

				public static final int VALIDATE_INVALIDEVENTFIELD_QUERIES = 1007;

				public static final int JUSTIFY_UNPERMITTEDQUERYVIEW = 1103;

				public static final int AUTHENTICATION_FIELDSMISSING = 20110000; 
				public static final int AUTHENTICATION_PASSWORDINVALID = 20110010; 
				public static final int AUTHENTICATION_PASSWORDINAPPROPRIATE = 20110011; 
				public static final int AUTHENTICATION_SSOPASSTOKENINVALID = 20110012; 
				public static final int AUTHENTICATION_TOKENINVALID = 20110020;
				public static final int AUTHENTICATION_IDEXISTENT = 20110030;// TODO why in auth... can be user id topic id etc... 
				public static final int AUTHENTICATION_IDABSENT = 20110031;
				public static final int AUTHENTICATION_ALIASIDEXISTENT = 20110040; 
				public static final int AUTHENTICATION_ALIASIDABSENT = 20110041; 
				public static final int AUTHENTICATION_ALIASIDTYPEINVALID = 20110050; 
				public static final int AUTHENTICATION_VERIFICATIONCODESENDFAILED = 20110060; 
				public static final int AUTHENTICATION_VERIFICATIONCODEINVALID = 20110061; 
				
				public static final int AUTHORIZATION_OPERATIONUNPERMITTED = 20210000; 
				public static final int AUTHORIZATION_ENTITYABSENT = 20210010; //entity not exist to operate, normally ignore? 
				public static final int AUTHORIZATION_PREREQUISITEENTITYABSENT = 20210020; //error for session create etc.
				public static final int AUTHORIZATION_PREREQUISITEENTITYLIFECYCLEINVALID = 20210021; 
				public static final int AUTHORIZATION_PREREQUISITEENTITYEXPIRED = 20210022; //error for session create etc. 
				public static final int AUTHORIZATION_PREREQUISITEPREREQUISITEENTITYABSENT = 20210030; //error for message create etc. 
				public static final int AUTHORIZATION_PREREQUISITEPREREQUISITEENTITYLIFECYCLEINVALID = 20210031; 
				public static final int AUTHORIZATION_PREREQUISITEPREREQUISITEENTITYEXPIRED = 20210032; 
				public static final int AUTHORIZATION_ENTITYTIMEEXPIRETOOEARLYTOUPDATE = 20210040; //error for session create etc.
				public static final int AUTHORIZATION_ENTITYTIMEEXPIRETOOLONGTOUPDATE = 20210041; 

				public static final int STORAGE_ITEMEXISTENT = 20310000; 
				public static final int STORAGE_ITEMABSENT = 20310001; 
				
				public static final int ARCHIVER_OPERATIONUNPERMITTED = 20410000;
				public static final int ARCHIVER_RECORDABSENT = 20410010;
				public static final int ARCHIVER_RECORDEXISTENT = 20410011;
				public static final int ARCHIVER_RECORDDUPLICATE = 20410012;
				public static final int ARCHIVER_RECORDIDINVALID = 20410013; 
				
				public static final int INDEXER_DOCABSENT = 20510000; 
				public static final int INDEXER_DOCIDINVALID = 20510001; 
				public static final int INDEXER_DOCCONTENTUNSUPPORTED = 20510002; 
				public static final int INDEXER_INDEXINVALID = 20510010; 
				public static final int INDEXER_INDEXERROR = 20510011; 
				
				public static final int OPERATE_INVALIDSERVERMODULE = 2010;
				public static final int OPERATE_MODULEERROR = 2011; 
				
			}

			public class Description {
					
				public static final String UNKNOWN = "unknown error";
					
				public static final String VALIDATE_INVALIDEVENTFIELD_EVENTTYPE = "invalid eventType field in event";// check/replace at servlet???

				public static final String VALIDATE_EXCESSENTITIES = "excess entities in event";
				public static final String VALIDATE_EXCESSQUERIES = "excess queries in event";

				public static final String VALIDATE_INSUFFICIENTENTITIES = "insufficient entities in event";

				// public static final String VALIDATE_INVALIDSTATE_MEDIA =
				// "invalid media state in event";
				// public static final String VALIDATE_INVALIDSTATE_USER =
				// "invalid user state in event";
				public static final String VALIDATE_INVALIDTYPE_USER = "invalud user type in event";
				public static final String VALIDATE_INVALIDSPACEPOSITION = "invalid spacePositionX or spacePositionY in event";

				public static final String VALIDATE_MISSINGEVENTFIELD_AUTH = "missing auth info in event";

				public static final String VALIDATE_MISSINGALIASID_USER = "missing user aliasId in event";// used for user register and merge 
				public static final String VALIDATE_MISSINGALIASIDTYPE_USER = "missing user aliasIdType in event"; 
				public static final String VALIDATE_UNPERMITTEDENTITYFIELD_ALIASID = "unpermitted user aliasId in entity";
				public static final String VALIDATE_UNPERMITTEDENTITYFIELD_ID = "unpermitted user id in entity";
				public static final String VALIDATE_MISSINGID_USER = "missing user id in event";

				public static final String VALIDATE_INCONSISTENTAUTHINFO = "inconsistent auth id/aliasId with user id/aliasId in event";

				public static final String VALIDATE_AUTHIDNOTMATCH = "auth id/aliasId not match user entity id/aliasId";

				public static final String VALIDATE_INVALIDENTITYSTATE = "invalid entity state in event"; 				
				public static final String VALIDATE_INVALIDENTITYLIFECYCLE = "invalid entity lifecycle in event"; 
				public static final String VALIDATE_INVALIDENTITYFIELD = "invalid entity field in event";
				
				public static final String VALIDATE_MISSINGEVENTFIELD_ENTITIES = "missing entities field in event";

				public static final String VALIDATE_MISSINGEVENTFIELD_QUERIES = "missing queries field in event";

				public static final String VALIDATE_MISSINGENTITYFIELD_ID = "missing id field in entity";

				public static final String VALIDATE_MISSINGENTITYFIELD_SPACEPOSITION = "missing spaceposition field in entity";

				public static final String VALIDATE_UNPERMITTEDENTITYFIELD_USERCREATOR = "userCreator field not allowed to update";

				public static final String VALIDATE_UNPERMITTEDENTITYFIELD_TIMECREATE = "timeCreate field not allowed to update";

//				public static final String VALIDATE_MISSINGQUERYFIELD_ENTITYCLASS = "missing entityClass field in query";

				public static final String VALIDATE_MISSINGQUERYFIELD_FIELDFILTERS = "missing fieldFilters field in query";

				public static final String VALIDATE_INVALIDEVENTFIELD = "invalid field in event";

				public static final String VALIDATE_INVALIDEVENTFIELD_QUERIES = "invalid queries field in event";

				public static final String JUSTIFY_UNPERMITTEDQUERYVIEW = "unpermitted query view for auth user";

				public static final String AUTHENTICATION_FIELDSMISSING = "information required for authentication is missing"; 
				public static final String AUTHENTICATION_PASSWORDINVALID = "password is invalid"; 
				public static final String AUTHENTICATION_PASSWORDINAPPROPRIATE = "password is inappropriate"; 
				public static final String AUTHENTICATION_SSOPASSTOKENINVALID = "sso passtoken is invalid";
				public static final String AUTHENTICATION_TOKENINVALID = "token is invalid"; 
				public static final String AUTHENTICATION_IDEXISTENT = "user has already been created"; 
				public static final String AUTHENTICATION_IDABSENT = "user has not been created"; 
				public static final String AUTHENTICATION_ALIASIDEXISTENT = "user has already been registered"; 
				public static final String AUTHENTICATION_ALIASIDABSENT = "user has not been registered"; 
				public static final String AUTHENTICATION_ALIASIDTYPEINVALID = "user is invalid";//native, fb, gl etc. 
				public static final String AUTHENTICATION_VERIFICATIONCODESENDFAILED = "sending user verification code failed"; 
				public static final String AUTHENTICATION_VERIFICATIONCODEINVALID = "user verification code is invalid";

				public static final String AUTHORIZATION_OPERATIONUNPERMITTED = "user has no permission to perform the operation"; 
				public static final String AUTHORIZATION_ENTITYABSENT = "the operation target entity does not exist"; 
				public static final String AUTHORIZATION_PREREQUISITEENTITYABSENT = "the operation target prerequisite entity does not exist"; //error for session create etc.    
				public static final String AUTHORIZATION_PREREQUISITEENTITYLIFECYCLEINVALID = ""; //fill in actual lifecycle 
				public static final String AUTHORIZATION_PREREQUISITEENTITYEXPIRED = "the operation target prerequisite entity has expired"; //error for session create etc. 
				public static final String AUTHORIZATION_PREREQUISITEPREREQUISITEENTITYABSENT = "the operation target prerequisite prerequisite entity does not exist"; //error for message create etc.    
				public static final String AUTHORIZATION_PREREQUISITEPREREQUISITEENTITYLIFECYCLEINVALID = ""; //fill in actual lifecycle 
				public static final String AUTHORIZATION_PREREQUISITEPREREQUISITEENTITYEXPIRED = "the operation target prerequisite prerequisite entity has expired"; //error for session create etc. 
				public static final String AUTHORIZATION_ENTITYTIMEEXPIRETOOEARLYTOUPDATE = "it is too early to extend the expiration time of the entity"; //error for session create etc.
				public static final String AUTHORIZATION_ENTITYTIMEEXPIRETOOLONGTOUPDATE = "the expiration time to extend exceeds the maximum time range";
				
				public static final String ARCHIVER_OPERATIONUNPERMITTED = "target archiver records are not permited for operation";
				public static final String ARCHIVER_RECORDABSENT = "target archiver records do not exist";
				public static final String ARCHIVER_RECORDEXISTENT = "target archiver records already exist";
				public static final String ARCHIVER_RECORDDUPLICATE = "target archiver records are duplicate";
				public static final String ARCHIVER_RECORDIDINVALID = "target archiver record id is invalid"; 
				
				public static final String INDEXER_DOCABSENT = "target indexer document do not exist"; 
				public static final String INDEXER_DOCIDINVALID = "target indexer document id is invalid"; 
				public static final String INDEXER_DOCCONTENTUNSUPPORTED = "target indexer document content is not supported"; 
				public static final String INDEXER_INDEXINVALID = "indexer is invalid"; 
				public static final String INDEXER_INDEXERROR = "indexer indexing error"; 
				
//				public static final String OPERATE_UNPERMITTED = "unpermited operation";
//				public static final String OPERATE_NONEXISTENTITY = "nonexist entity to operate";
//				public static final String OPERATE_EXISTENTITY = "exist entity to operate";
//				public static final String OPERATE_MULTIPLEENTITY = "multiple entities to operate";
//				public static final String OPERATE_INVALIDENTITY = "invalid entity to operate";
				public static final String OPERATE_INVALIDSERVERMODULE = "invalid server module";

				public static final String STORAGE_ITEMEXISTENT = "target storage items already exist";
				public static final String STORAGE_ITEMABSENT = "target storage items do not exist"; 
			}
			
			
		}
