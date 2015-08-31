package nl.sogeti.vertx.webshop.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/*
 * This class provides some simple static methods to do json to object/list conversion.
 */
public class JsonConverter {
	private static final Gson gson = new Gson();
	
	public static <T> List<T> fromJsonList(List<JsonObject> objects, Class<T> theClass){
		return objects.stream().map(json -> gson.fromJson(json.toString(), theClass)).collect(Collectors.<T>toList());
	}
	
	public static <T> List<T> fromJsonArray(JsonArray objects, Class<T> theClass){
		List<T> result = new ArrayList<T>();
		for(int index = 0; index < objects.size(); index++){
			JsonObject json = objects.getJsonObject(index);
			result.add(gson.fromJson(json.toString(), theClass));
		}
		return result;
	}
	
	//This method serves mostly as a static accessor.
	public static <T> T fromJsonObject(JsonObject object, Class<T> theClass){
		return gson.fromJson(object.toString(), theClass);
	}
}
