package library.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JSONHelper { 
	
	public static JSONObject makeJSONObjectIfPossible(String jos) {
		JSONObject joo = null; 
		try {
			joo = new JSONObject(jos); 
		} catch (Exception e) { 
			return null; 
		}		
		return joo; 
	}

	//to improve: nested primary key? 	
	public static boolean contains(String jos_container, String jos_target) {
		boolean rt = false;
		try {
			if (!jos_container.trim().startsWith("{")) return rt;
			
			JSONObject jo_container = new JSONObject(jos_container);
			JSONObject jo_target = new JSONObject(jos_target);
			
			Iterator<?> iter_target = jo_target.keys();
			while (iter_target.hasNext()) {
				String key = iter_target.next().toString();
				if (!jo_container.has(key)) return false;
				else {
					if (!(jo_target.get(key) instanceof JSONObject)) {
						String container = jo_container.getString(key);
						String target = jo_target.getString(key);
						try {
							double container_double = Double.parseDouble(container);
							double target_double = Double.parseDouble(target);
							if (container_double != target_double) return false;
						} catch (Exception e) {
							if (!container.equals(target)) return false;
						}
					}
					else {
						String js_container_sub = jo_container.getString(key);
						String js_target_sub = jo_target.getString(key);
						rt = contains(js_container_sub, js_target_sub);
						if (rt==false) return false;
					}
				}
			}
			rt = true;
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	public static boolean contains(JSONObject jo_container, JSONObject jo_target) {
		boolean rt = false;
		try {
			
			
			Iterator<?> iter_target = jo_target.keys();
			while (iter_target.hasNext()) {
				String key = iter_target.next().toString();
				if (!jo_container.has(key)) return false;
				else {
					if (!(jo_target.get(key) instanceof JSONObject)) {
						String container = jo_container.getString(key);
						String target = jo_target.getString(key);
						try {
							double container_double = Double.parseDouble(container);
							double target_double = Double.parseDouble(target);
							if (container_double != target_double) return false;
						} catch (Exception e) {
							if (!container.equals(target)) return false;
						}
					}
					else {
						JSONObject jo_container_sub = jo_container.getJSONObject(key);
						JSONObject jo_target_sub = jo_target.getJSONObject(key);
						rt = contains(jo_container_sub, jo_target_sub);
						if (rt==false) return false;
					}
				}
			}
			rt = true;
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	
	public static String containsJAS(String jas_container, String jos_target) {
		
		String rt = null;
		try {
			JSONArray jao_rt = new JSONArray();
			JSONArray jao_container = new JSONArray(jas_container);
			
			for (int i=0; i< jao_container.length(); i++) {
				String jos = jao_container.getString(i);
				if (contains(jos, jos_target)) {
					jao_rt.put(new JSONObject(jos));
				}
			}
			rt = jao_rt.toString();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	
	
	public static String mergesJS(String js_container, String js_target) {
		String rt = js_container;
		try {
			if (js_container.startsWith("{") && js_target.startsWith("{")) {
				JSONObject jo_container = new JSONObject(js_container);
				JSONObject jo_target = new JSONObject(js_target);
				
				Iterator<?> iter_target = jo_target.keys();
				while (iter_target.hasNext()) {
					String key = iter_target.next().toString();
					jo_container.put(key, jo_target.getString(key));
				}
				rt = jo_container.toString();
			}
			else if (js_container.startsWith("[") && js_target.startsWith("[")) {
				JSONArray jo_container = new JSONArray(js_container);
				JSONArray jo_target = new JSONArray(js_target);
				
				for (int i=0; i< jo_target.length(); i++) {
					jo_container.put(jo_target.get(i));
				}
				rt = jo_container.toString();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	
	public static String extracts(String jos_container, String[] keys) {
		String rt = null;
		try {
			JSONObject jo_container = new JSONObject(jos_container);
			JSONObject jo_target = new JSONObject();
			
			for (int i=0; i<keys.length; i++) {
				if (jo_container.has(keys[i])) {
					jo_target.put(keys[i], jo_container.get(keys[i]));
				}
				else return null;
			}
			rt = jo_target.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	public static String extractsJAS(String jas, String[] keys) {
		String rt = null;
		try {
			JSONArray jao_rt = new JSONArray();
			JSONArray jao = new JSONArray(jas);
			for (int i=0 ; i< jao.length(); i++) {
				JSONObject joo = jao.getJSONObject(i);
				
				String jos_extracts = extracts(joo.toString(), keys);
				if (jos_extracts!=null) {
					jao_rt.put(new JSONObject(jos_extracts));
				}
			}
			rt = jao_rt.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	public static String subtracts(String jos_container, String[] keys) {
		String rt = null;
		try {
			JSONObject jo_container = new JSONObject(jos_container);
			for (int i=0; i<keys.length; i++) { 
				jo_container.remove(keys[i]); 
			}
			rt = jo_container.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	public static JSONObject subtracts(JSONObject jo_container, String[] keys, JSONObject jo_removed) {
		JSONObject rt = null;
		try {
			
			for (int i=0; i<keys.length; i++) { 
				jo_removed.put(keys[i], jo_container.remove(keys[i])); 
			}
			rt = jo_container;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	public static String subtractsJAS(String jas, String[] keys) {
		String rt = null;
		try {
			JSONArray jao_rt = new JSONArray();
			JSONArray jao = new JSONArray(jas);
			for (int i=0 ; i< jao.length(); i++) {
				JSONObject joo = jao.getJSONObject(i);
				
				String jos_subtracts = subtracts(joo.toString(), keys);
				if (jos_subtracts!=null) {
					jao_rt.put(new JSONObject(jos_subtracts));
				}
			}
			rt = jao_rt.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Integer, Object> JASToMap (String jas) {
		Map<Integer, Object> map = new LinkedHashMap<Integer, Object>();
		try {
			JSONArray jArray = new JSONArray(jas);
			//System.out.println(jArray);
			Type type = new TypeToken<LinkedHashMap<String, Object>>(){}.getType();
			Gson gson = new Gson();


			for (int i=0; i< jArray.length(); i++) {
				LinkedHashMap<String, Object> item =  gson.fromJson(jArray.getString(i), type);
				map.put(item.hashCode(), item);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}
	
	
	public static String MapValuesToJAS(String maps) {
		String rt = null;
		try {
			//JSONObject jObject = new JSONObject(jos);
			//System.out.println(jArray);
			JSONObject joo = new JSONObject(maps);
			
			//System.out.println(joo);
			JSONArray jao_rt = new JSONArray();
			Iterator<?> iter = joo.keys();
			while (iter.hasNext()) {
				JSONObject joo_temp = joo.getJSONObject(iter.next().toString());
				jao_rt.put(joo_temp);
			}
			rt = jao_rt.toString();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	public static int GetJALength(String jas) {
		int rt = -1;
		try {
			//JSONObject jObject = new JSONObject(jos);
			//System.out.println(jArray);
			JSONArray jao = new JSONArray(jas);
			
			//System.out.println(joo);
			rt=jao.length();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;		
	}
	
	public static int GetJASchemaLength(String jas) {
		int rt = -1;
		try {
			//JSONObject jObject = new JSONObject(jos);
			//System.out.println(jArray);
			JSONArray jao = new JSONArray(jas);
			JSONObject joo = jao.getJSONObject(0);
			//System.out.println(joo);
			rt=joo.length();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;		
	}
	
	
	public static String Partition(String jas, String[] partitionAttributes ) {
		
		String rt = null;
		try {
			JSONArray jao = new JSONArray(jas);
			JSONArray jao_rt = new JSONArray();
			for (int i=0; i<jao.length(); i++) {
				JSONObject joo_data = jao.getJSONObject(i);
				JSONObject joo_partition = new JSONObject();
				subtracts(joo_data, partitionAttributes, joo_partition);
				boolean doContain = false;
				for (int j=0; j<jao_rt.length(); j++) {
					if (contains(jao_rt.getJSONObject(j), joo_partition)) {
						jao_rt.getJSONObject(j).getJSONArray("data").put(joo_data);
						doContain = true;
						break;
					}
				}
				if (doContain == false) {
					JSONObject joo_rt = new JSONObject();
					Iterator<?> iter = joo_partition.keys();
					while (iter.hasNext()) {
						String key_temp = iter.next().toString();
						joo_rt.put(key_temp, joo_partition.getString(key_temp));
					}
					
					
					JSONArray jao_data = new JSONArray();
					jao_data.put(joo_data);
					joo_rt.put("data", jao_data);
					jao_rt.put(joo_rt);
				}
				
			}
			System.out.println(jao_rt.toString());
			
			rt = jao_rt.toString(); 

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	public static boolean equalsTo(String jos_1, String jos_2) {
		boolean rt = false;
		try {
			
			JSONObject jo_1 = new JSONObject(jos_1);
			JSONObject jo_2 = new JSONObject(jos_2);
			
			if (jo_1.length()!=jo_2.length()) return false;
			
			Iterator<?> iter_2 = jo_2.keys();
			while (iter_2.hasNext()) {
				String key = iter_2.next().toString();
				if (!jo_1.has(key)) return false;
				else {
					if (!(jo_2.get(key) instanceof JSONObject)) {
						if (!jo_1.getString(key).equals(jo_2.getString(key))) return false;
					}
					else {
						String js_2_sub = jo_2.getString(key);
						String js_1_sub = jo_1.getString(key);
						rt = contains(js_1_sub, js_2_sub);
						if (rt==false) return false;
					}
				}
			}
			rt = true;
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
	public static String sort(String jos) {
		
		String rt = null;
		try {
			if (!jos.trim().startsWith("{")) return jos;
			
			JSONObject joo = new JSONObject(jos);
			Iterator<?> iter = joo.keys();
			String[] keys = new String[joo.length()];
			int count=0;
			while(iter.hasNext()) {
				keys[count] = iter.next().toString();
				count++;
			}
			Arrays.sort(keys);
			
			JSONObject jo_rt = new JSONObject();
			for (int i=0; i < keys.length; i++) {
				jo_rt.put(keys[i],joo.get(keys[i]));
			}
			
			rt = jo_rt.toString();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
		
	}
	
//	@SuppressWarnings("unchecked")
//	public static Map<String, Object> JOOToMap(JSONObject joo) {
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//			mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//			mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
//
//			map =  (HashMap<String,Object>) mapper.readValue(joo.toString(), HashMap.class);
//
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//
//	}
	
//	@SuppressWarnings("unchecked")
//	public static Map<String, Object> JOSToMap(String jos) {
//		Map<String, Object> map = new LinkedHashMap<String, Object>();
//
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//			mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//			mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
//
//			map =  (LinkedHashMap<String,Object>) mapper.readValue(jos, LinkedHashMap.class);
//
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//
//	}
	
//	@SuppressWarnings("unchecked")
//	public static <X, Y> Map<X, Y> JSONObjectToMapUsingMapper(JSONObject joo) {
//		Map<X, Y> map = new HashMap<X, Y>();
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//			mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//			mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
//			map =  (HashMap<X, Y>) mapper.readValue(joo.toString(), HashMap.class);
//
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//
//	}
	
//	public static JSONObject mapToJOO(Map<String, Object> map) throws JSONException {
//		
//		
//		JSONObject joo = new JSONObject();
//	    Iterator<String> iter = map.keySet().iterator();
//	    while (iter.hasNext()) {
//	    	String key = iter.next().toString();
//	    	Object value = map.get(key);
//	    	joo.put(key, value);
//	    }
//	    return joo;
//	}
	
	public static String ObjectToJSONString(Object obj)  {
		
		String rt = null;
		try {
			Gson gson = new Gson();
			rt = gson.toJson(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
	}
	

//	public static String mapToJOS2(Map<String, Object> map)  {
//
//		String rt = null;
//		try {
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			ObjectMapper mapper = new ObjectMapper();
//
//			mapper.writeValue(out, map);
//			byte[] data = out.toByteArray();
//			rt = new String(data);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return rt;
//	}

	public static JSONObject mergeJSONObject(JSONObject primary_joo, JSONObject secondary_joo) {
		
		JSONObject rt = new JSONObject();
		
		if (primary_joo == null) return secondary_joo;
		if (secondary_joo == null) return primary_joo;
		
		Iterator<?> iter_primary = primary_joo.keys();
		Iterator<?> iter_secondary = secondary_joo.keys();
		String tmp_key;
		
		try {
			while(iter_secondary.hasNext()) {
			    tmp_key = iter_secondary.next().toString();
			    rt.put(tmp_key, secondary_joo.get(tmp_key));
			}
			while(iter_primary.hasNext()) {
			    tmp_key = iter_primary.next().toString();
			    rt.put(tmp_key, primary_joo.get(tmp_key));
			}
		} catch (Exception e) {
			
		}
		return rt;
	}
	
	public static JSONObject mergeJSONObjectInPlaceNoOverwrite(JSONObject primary_joo, JSONObject secondary_joo) throws JSONException {
		
		if (primary_joo == null) { 
			if (secondary_joo != null) {
				primary_joo = new JSONObject();
			} else {
				return primary_joo;
			}
		} else if (secondary_joo == null) { 
			return primary_joo;
		}
	
		Iterator<?> iter_secondary = secondary_joo.keys();
		String tmp_key;
		try{
			while(iter_secondary.hasNext()) {
				tmp_key = iter_secondary.next().toString();
				if (!primary_joo.has(tmp_key)) {
					primary_joo.put(tmp_key, secondary_joo.get(tmp_key));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return primary_joo;
		
	}
	
	public static void putObjectWithClassCheck(JSONObject targetJoo, String field, Object value) throws Exception {
		if (targetJoo == null) {
			return;
		}
		if (value instanceof Collection<?>) {
			targetJoo.put(field, (Collection<?>) value);
		} 
		else if (value instanceof Map<?, ?>) {
			targetJoo.put(field, (Map<?, ?>) value);
		}
		else {
			targetJoo.put(field, value);
		}
		
	}
	
	//JSONPath
	static String regexPatternStringForJSONArrayIndex = "(.*)\\[(\\d+)\\]";
	static Pattern regexPatternForJSONArrayIndex = Pattern.compile(regexPatternStringForJSONArrayIndex);
	
	public static Object JSONPathGetObjectFromJSONObject(JSONObject objectJSON, String path) throws Exception {
		ArrayList<String> pathSegments = StringHelper.getSegmentsForXMLPath(path);//JSONPath too
		Object value = getObjectByJSONPathFromPathIndex(objectJSON, pathSegments, 0);
		return value;
		
	}
	
	public static Object JSONPathGetObjectFromJSONObject(JSONObject objectJSON, List<String> pathSegments) throws Exception {
		Object value = getObjectByJSONPathFromPathIndex(objectJSON, pathSegments, 0);
		return value;
		
	}
	
	public static ArrayList<String> JSONPathConvertFromStringToArrayList(String path) {
		return StringHelper.getSegmentsForXMLPath(path);		
	}
	
//	public static String JSONPathConvertFromArrayListToString(ArrayList<String> pathSegments) {
//		return StringUtils.join(pathSegments, ".");
//	}
	
	private static Object getObjectByJSONPathFromPathIndex(Object objectIn, List<String> pathSegments, int segmentIndex) throws Exception {// what about array...array


		if (segmentIndex == pathSegments.size())
			return objectIn;

		else {
			String pathSegment = pathSegments.get(segmentIndex).trim();
			Matcher matcher = regexPatternForJSONArrayIndex.matcher(pathSegment);
			String field = null;
			Integer index = null;
			if (matcher.find()) {
				field = matcher.group(1);
				String index_temp = matcher.group(2);
				if (index_temp != null) {
					index = Integer.parseInt(matcher.group(2));
				}
			} else {
				field = pathSegment;
			}

			Object objectOut = null;
			if (index != null) {//JSONArray
				objectOut = ((JSONObject) objectIn).getJSONArray(field).getJSONObject(index);
			} else {
				if (field.trim().length() == 0) {// allow "" path which select itself
					objectOut = (JSONObject) objectIn;
				}
				else {
					objectOut = ((JSONObject) objectIn).get(field);
				}
			}
			segmentIndex++;
			return getObjectByJSONPathFromPathIndex(objectOut, pathSegments, segmentIndex);

		}
	}
	
	@SuppressWarnings("unused")
	private static Object setObjectByJSONPathFromPathIndex(Object objectIn, List<String> pathSegments, int segmentIndex, Object value) throws Exception {// what about array...array


		if (segmentIndex == pathSegments.size())
			return objectIn;

		else {
			String pathSegment = pathSegments.get(segmentIndex).trim();
			Matcher matcher = regexPatternForJSONArrayIndex.matcher(pathSegment);
			String field = null;
			Integer index = null;
			if (matcher.find()) {
				field = matcher.group(1);
				String index_temp = matcher.group(2);
				if (index_temp != null) {
					index = Integer.parseInt(matcher.group(2));
				}
			} else {
				field = pathSegment;
			}

			Object objectOut = null;
			if (index != null) {//JSONArray
				objectOut = ((JSONObject) objectIn).getJSONArray(field).getJSONObject(index);
			} else {
				if (field.trim().length() == 0) {// allow "" path which select itself
					objectOut = (JSONObject) objectIn;
				}
				else {
					objectOut = ((JSONObject) objectIn).get(field);
				}
			}
			segmentIndex++;
			return getObjectByJSONPathFromPathIndex(objectOut, pathSegments, segmentIndex);

		}
	}
	
	
	public static Object setObjectByJSONPath(JSONObject objectJSON, String path, Object value) throws Exception {
		ArrayList<String> pathSegments = StringHelper.getSegmentsForXMLPath(path);
		Object result = setObjectByJSONPathFromPathIndex(objectJSON, pathSegments, 0, value);
		return result;
		
	}
	
	// to verify
	private static Object setObjectByJSONPathFromPathIndex(JSONObject objectIn, List<String> pathSegments, int segmentIndex, Object value) throws Exception {// what about array...array


		if (segmentIndex == pathSegments.size()-1) {
			return objectIn.put(pathSegments.get(segmentIndex), value);
		} else {
			String pathSegment = pathSegments.get(segmentIndex).trim();
			Matcher matcher = regexPatternForJSONArrayIndex.matcher(pathSegment);
			String field = null;
			Integer index = null;
			if (matcher.find()) {
				field = matcher.group(1);
				String index_temp = matcher.group(2);
				if (index_temp != null) {
					index = Integer.parseInt(matcher.group(2));
				}
			} else {
				field = pathSegment;
			}

			JSONObject objectOut = null;
			if (index != null) {//JSONArray
				boolean hasField  = objectIn.has(field);
				JSONObject valueIn_joo = new JSONObject();
				if (hasField) {
					JSONArray valueIn = objectIn.getJSONArray(field);
					if (valueIn.length() > index) {
						valueIn_joo = valueIn.getJSONObject(index);
					} else {//here if index greater than the current size + 1, still just put in rather than raise exception
						valueIn.put(valueIn_joo);
					}
				} else {
					JSONArray valueNew = new JSONArray();
					objectIn.put(field, valueNew);
					valueNew.put(valueIn_joo);
				}
				objectOut = valueIn_joo;
			} else {
				if (field.trim().length() == 0) {// allow "" path which select itself
					objectOut = objectIn;
				}
				else {
					boolean hasField  = objectIn.has(field);
					JSONObject valueIn_joo = new JSONObject();
					if (hasField) {
						valueIn_joo = objectIn.getJSONObject(field);
					} else {
						objectIn.put(field, valueIn_joo);
					}
					objectOut = valueIn_joo;
				}
			}
			segmentIndex++;
			return setObjectByJSONPathFromPathIndex(objectOut, pathSegments, segmentIndex, value);

		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> transformJSONObjectToMap(JSONObject joo) {
		Map<String, T> rt = null;
		try {			
			if (joo == null) {
				return rt;
			}
			rt = new HashMap<String, T>();
			Iterator<String> iterJoo = joo.keys(); 
			while (iterJoo.hasNext()) {
				String key = iterJoo.next();
				T value = (T) joo.get(key);
				rt.put(key, value);
			}			
			return rt;
		}
		catch (Exception e) {
			e.printStackTrace();
			rt = null;
		}		
		return rt;		
	}
	
	public static <T> JSONObject transformMapToJSONObject(Map<String, T> map) {
		JSONObject rt = null;
		try {
			if (map == null) {
				return rt;
			}
			rt = new JSONObject();
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				T value = map.get(key);
				rt.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rt = null;
		}
		return rt;
	}
	
	@SuppressWarnings("unchecked")
	public static final <T> List<T> transformJSONArrayToList(JSONArray jao) {		
		List<T> rt = null;
		try {			
			if (jao == null) {
				return rt;
			}		
			rt = new ArrayList<T>();		
			for (int i=0; i< jao.length(); i++) {
				T jaoValue = (T) jao.get(i);
				rt.add(jaoValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rt = null;
		}
		return rt;
	}
	
	public static <T> JSONArray transformListToJSONArray(List<T> list) {
		JSONArray rt = null;
		try {
			if (list == null) {
				return rt;
			}
			rt = new JSONArray();
			for (int i=0; i< list.size(); i++) {
				T item = list.get(i);
				rt.put(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rt = null;
		}
		return rt;
	}
	
	public static final class JSONObjectWrapper {
		
		private JSONObject underlying;
		
		public JSONObjectWrapper(JSONObject joo) {
			this.underlying = joo;
		} 
		
		public Boolean has(String key) {
			Boolean rt = false;
			if (this.underlying != null) {
				if (this.underlying.has(key)) {
					rt = true;
				} 
			}
			return rt;
		}
		
		public Object get(String key) {
			Object rt = null;
			try {
				if (this.underlying != null) {
					if (this.underlying.has(key)) {
						rt = this.underlying.get(key);
					} 
				}
			} catch (Exception e) {
				rt = null;
			}
			return rt;
		}
		
		public String getString(String key) {
			String rt = null;
			try {
				if (this.underlying != null) {
					if (this.underlying.has(key)) {
						rt = this.underlying.getString(key);
					} 
				}
			} catch (Exception e) {
				rt = null;
			}
			return rt;
		}
		
		public Integer getInt(String key) {
			Integer rt = null;
			try {
				if (this.underlying != null) {
					if (this.underlying.has(key)) {
						rt = this.underlying.getInt(key);
					} 
				}
			} catch (Exception e) {
				rt = null;
			}
			return rt;
		}
		
		public Long getLong(String key) {
			Long rt = null;
			try {
				if (this.underlying != null) {
					if (this.underlying.has(key)) {
						rt = this.underlying.getLong(key);
					} 
				}
			} catch (Exception e) {
				rt = null;
			}
			return rt;
		}
		
		public Double getDouble(String key) {
			Double rt = null;
			try {
				if (this.underlying != null) {
					if (this.underlying.has(key)) {
						rt = this.underlying.getDouble(key);
					} 
				}
			} catch (Exception e) {
				rt = null;
			}
			return rt;
		}
		
		public Boolean getBoolean(String key) {
			Boolean rt = null;
			try {
				if (this.underlying != null) {
					if (this.underlying.has(key)) {
						rt = this.underlying.getBoolean(key);
					} 
				}
			} catch (Exception e) {
				rt = null;
			}
			return rt;
		}
		
		//no need to try convert string to joo since it has already been done by the json lib
		public JSONObject getJSONObject(String key) {
			JSONObject rt = null;
			try {
				if (this.underlying != null) {
					if (this.underlying.has(key)) {
						rt = this.underlying.getJSONObject(key);
					} 
				}
			} catch (Exception e) {
				rt = null;
			}
			return rt;
		}
		
		//name different since the actual value may not be map
		@SuppressWarnings("unchecked")
		public <T> Map<String, T> getValueAsMap(String key) {
			Map<String, T> rt = null;
			try {
				if (this.underlying != null) {
					if (this.underlying.has(key)) {
						Object value = this.underlying.get(key);
						if (value instanceof Map) {
							//can cause runtime error if type not match later...
							rt = (Map<String, T>) value;							
						} else if (value instanceof JSONObject) {
							JSONObject valueOfJoo = (JSONObject) value;
							rt = JSONHelper.transformJSONObjectToMap(valueOfJoo);							
						} else if (value instanceof String) {
							JSONObject valueOfJoo = new JSONObject((String) value);
							rt = JSONHelper.transformJSONObjectToMap(valueOfJoo);
						}
					} 
				}
			} catch (Exception e) {
				rt = null;
			}
			return rt;
		}
 
		public JSONArray getJSONArray(String key) {
			JSONArray rt = null;
			try {
				if (this.underlying != null) {
					if (this.underlying.has(key)) {
						rt = this.underlying.getJSONArray(key);
					} 
				}
			} catch (Exception e) {
				rt = null;
			}
			return rt;
		}
		
		@SuppressWarnings("unchecked")
		public <T> List<T> getValueAsList(String key) {
			List<T> rt = null;
			try {
				if (this.underlying != null) {
					if (this.underlying.has(key)) {
						Object value = this.underlying.get(key);
						if (value instanceof List) {
							rt = (List<T>) value;
						} else if (value instanceof JSONArray) {
							rt = JSONHelper.transformJSONArrayToList((JSONArray) value);
						}
					} 
				}
			} catch (Exception e) {
				rt = null;
			}
			return rt;
		}
		
		public void put(String key, Object value) {
			try {
				if (this.underlying != null) {
					this.underlying.put(key, value);
				}
			} catch (Exception e) {
				
			} 
		}
		
		public void remove(String key) {
			try {
				if (this.underlying != null) {
					this.underlying.remove(key);
				}
			} catch (Exception e) {
				
			} 
		}
		
		
		public JSONObject getUnderlying() {
			return this.underlying;
		}
		
		@Override
		//TOCHECK better return {} instead of null to be consistent?
		public String toString() {
			if (this.underlying == null) {
				return null;
			} else {
				return this.underlying.toString();
			}
		}
	}
	
}
