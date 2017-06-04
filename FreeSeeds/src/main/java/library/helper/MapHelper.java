package library.helper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MapHelper {

	public static <T> Map<String, T> mergeMap(Map<String, T> primaryMap, Map<String, T> secondaryMap) {
		
		Map<String, T> rt = null;
		
		if (primaryMap == null) return secondaryMap;
		if (secondaryMap == null) return primaryMap;
		
		rt = new HashMap<String, T>();
		
		Iterator<String> iterPrimaryMap = primaryMap.keySet().iterator();
		Iterator<String> iterSecondaryMap = secondaryMap.keySet().iterator();
		
		while(iterSecondaryMap.hasNext()) {
		    String keyTemp = iterSecondaryMap.next();
		    rt.put(keyTemp, secondaryMap.get(keyTemp));
		}
		while(iterPrimaryMap.hasNext()) {
		    String keyTemp = iterPrimaryMap.next();
		    rt.put(keyTemp, primaryMap.get(keyTemp));
		}

		return rt;
	}
	
	//joo, jos, map to map
	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> getValueAsMap(Map<String, Object> map, String key) {
		Map<String, T> rt = null;
		try {
			if (map == null) {
				return rt;
			}
			if (map.containsKey(key)) {
				Object value = map.get(key);
				if (value instanceof Map) {
					rt = (Map<String, T>) value;
				} else if (value instanceof JSONObject) {
					rt = JSONHelper.transformJSONObjectToMap((JSONObject) value);							
				}
			}
		} catch (Exception e) {
			rt = null;
		}
		return rt;
	}
	
	@SuppressWarnings("unchecked")
	public static Object getValueOrFirstElementOfValueOfList(Map<String, Object> map, String key) {
		Object rt = null;
		try {
			if (map == null) {
				return rt;
			}
			if (map.containsKey(key)) {
				Object value = map.get(key);
				if (value instanceof List) {
					List<Object> valueOfList = (List<Object>) value;
					if (valueOfList != null && valueOfList.size() > 0) {
						rt = valueOfList.get(0);
					}
				} else {
					rt = value;							
				}
			}
		} catch (Exception e) {
			rt = null;
		}
		return rt;
	}	
}
