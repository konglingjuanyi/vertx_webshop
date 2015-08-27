package nl.sogeti.vertx.webshop.util;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import io.vertx.core.json.JsonObject;

/*
 * This class provides some simple static methods to do json to object/list conversion.
 */
public class JsonConverter {
	private static final Gson gson = new Gson();
	
	public static <T> List<T> fromJsonList(List<JsonObject> objects, Class<T> theClass){
		return objects.stream().map(json -> gson.fromJson(json.toString(), theClass)).collect(Collectors.<T>toList());
	}
	
	//This method serves mostly as a static accessor.
	public static <T> T fromJsonObject(JsonObject object, Class<T> theClass){
		return gson.fromJson(object.toString(), theClass);
	}
}
