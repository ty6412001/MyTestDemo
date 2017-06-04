package library.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringHelper {
	
	public static Object getDoubleOrStringFromObject(Object object) {
		Object rt = null;
		if (object == null) {
			return rt;
		}
		String objectOfString = object.toString();
		rt = objectOfString;
		try {
			Double valueOfDouble = Double.valueOf(objectOfString);
			rt = valueOfDouble;
		} catch (Exception e) {
			rt = objectOfString;
		}
		return rt;
	}
	
	public static String getStringFromObject(Object object) {
		String rt = null;
		if (object == null) {
			return rt;
		}
		String objectOfString = object.toString();
		rt = objectOfString;
		return rt;
	}
	
	public static ArrayList<String> getSegmentsForXMLPath(String path) {

		ArrayList<String> segments = new ArrayList<String>();;
		
		String oddDotsPatternStr = "(?<!\\.)(\\.)(\\.\\.)*(?!\\.)";
		Pattern oddDotsPattern = Pattern.compile(oddDotsPatternStr);
		Matcher oddDotsPatternMatcher = oddDotsPattern.matcher(path);
		
		String consecutiveDotsPatternStr = "(?<!\\.)(\\.)+(?!\\.)";
		Pattern consecutiveDotsPattern = Pattern.compile(consecutiveDotsPatternStr);
		
		int lastOddDotsPatternEnd=0;
		while(true) {
			String segmentTag = path.substring(lastOddDotsPatternEnd);
			boolean isOddDotsPatternFound = oddDotsPatternMatcher.find();
			if (isOddDotsPatternFound) {
				int end=oddDotsPatternMatcher.end();
				segmentTag = path.substring(lastOddDotsPatternEnd, end);
				lastOddDotsPatternEnd = end;
			}
//			System.out.println(segmentTag);
			Matcher consecutiveDotsPatternMatcher = consecutiveDotsPattern.matcher(segmentTag);
			int lastConsecutiveDotsPatternEnd=0;
			while(true) {
				String segmentDot = segmentTag.substring(lastConsecutiveDotsPatternEnd);
				if (segmentDot.endsWith(".")) {
					segmentDot.substring(0, segmentDot.length()-1);
				}
				boolean isConsecutiveDotsPatternFound = consecutiveDotsPatternMatcher.find();
				if(isConsecutiveDotsPatternFound) {
					int start=consecutiveDotsPatternMatcher.start();
					int end=consecutiveDotsPatternMatcher.end();
					String segmentDotStr = segmentTag.substring(lastConsecutiveDotsPatternEnd, start);
					String segmentDotDot = segmentTag.substring(start, end);
					int dotNum = segmentDotDot.length();
					int dotNumEscapeAndDelimiterRemoved = dotNum/2-1;//aasdf..ase, also remove . from aasdf.
					String segmentDotDotEscapeAndDelimiterRemoved = "";
					for (int j=0; j<dotNumEscapeAndDelimiterRemoved; j++) {
						segmentDotDotEscapeAndDelimiterRemoved = segmentDotDotEscapeAndDelimiterRemoved + ".";
					}
					segmentDot = segmentDotStr.concat(segmentDotDotEscapeAndDelimiterRemoved);
					segments.add(segmentDot);
					lastConsecutiveDotsPatternEnd = end;
				} else {
					if (segmentDot.length()>0) {
						segments.add(segmentDot);
					}
					break;
				}	
			}
			if (!isOddDotsPatternFound) {
				break;
			}
		}
		return segments;
	}

	public static Boolean isDoubleString(String string) {
		try {  
			Double.parseDouble(string);  
		} catch(NumberFormatException e) {  
			return false;
		}  
		return true;  
	}
	
	public static Boolean isIntString(String string) {
		try {  
			Integer.parseInt(string);  
		} catch(NumberFormatException e) {  
			return false;
		}  
		return true;  
	}	
	
	public static Boolean isLongString(String string) {
		try {  
			Long.parseLong(string);  
		} catch(NumberFormatException e) {  
			return false;
		}  
		return true;  
	}
	
	
	public static String join(CharSequence separator, Iterable<?> strings) {
		Iterator<?> i = strings.iterator();
	    if (!i.hasNext()) {
	      return null;//""
	    }
	    StringBuilder sb = new StringBuilder(i.next().toString());
	    while (i.hasNext()) {
	      sb.append(separator);
	      sb.append(i.next().toString());
	    }
	    return sb.toString();
	}
	
	public static String utilAddDoubleQuotes(String strIn) {
		String strOut = strIn;
		if (strIn == null) {
			return null;
		}
		//TODO endsWith check?
		if (!strIn.startsWith("\"")) {
			strOut = "\"" + strIn + "\"";
		}
		return strOut;
	}
	
}
