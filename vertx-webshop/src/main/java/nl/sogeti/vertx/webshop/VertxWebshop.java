package nl.sogeti.vertx.webshop;

import io.vertx.core.Vertx;
import nl.sogeti.vertx.webshop.verticles.StaticHttpVerticle;

public class VertxWebshop {
	
	//Entry point
	public static void main(String[] args){
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new StaticHttpVerticle());
	}
}
