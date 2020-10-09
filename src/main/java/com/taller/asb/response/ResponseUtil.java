package com.taller.asb.response;

import java.util.HashMap;
import java.util.Map;
//import com.jayway.jsonpath.Configuration;
//import com.jayway.jsonpath.Option;
//import com.jayway.jsonpath.JsonPath;

public class ResponseUtil {

	public static Map<String,Object> buildResponse(String node, Object o) {
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put(node, o);
		
		return result;
	}
	
//	public static Object getStringFromJson(String json, String path){
//        Configuration conf = Configuration.defaultConfiguration()
//                .addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL)
//                .addOptions(Option.SUPPRESS_EXCEPTIONS);
//
//        return JsonPath.using(conf).parse(json).read(path);
//    }
	
}
