package nl.sogeti.vertx.webshop.util;

import io.vertx.ext.mongo.MongoClient;

public class MongoClientProvider {
	private static MongoClient client;
	
	public static MongoClient getClient(){
		return client;
	}
	
	public static void setClient(MongoClient client){
		MongoClientProvider.client = client;
	}
}
