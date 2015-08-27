package nl.sogeti.vertx.webshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import nl.sogeti.vertx.webshop.verticle.AdminPanelVerticle;
import nl.sogeti.vertx.webshop.verticle.WebshopVerticle;

public class VertxWebshop {
	
	//Entry point
	public static void main(String[] args){
		Vertx vertx = Vertx.vertx();
		AbstractVerticle[] verticles = new AbstractVerticle[]{ new WebshopVerticle(8080, "app/webshop"), new AdminPanelVerticle(9080, "app/admin") };
		for(AbstractVerticle verticle : verticles){
			vertx.deployVerticle(verticle);
		}
	}
}
