package nl.sogeti.vertx.webshop.verticle;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

public class WebshopVerticle extends WebVerticle {

	public WebshopVerticle(int port, String path) {
		super(port, path);
	}
	
	@Override
	protected void configureBasicRouter(Router router) {
		router.route().handler(CookieHandler.create());
		router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx, "sessions", 1800000)));
		
		//router.route("/").handler(basicAuthHandler).failureHandler(ErrorHandler.create());
		super.configureBasicRouter(router);
	}
	
	@Override
	protected Router createRestRouter(){
		Router router = super.createRestRouter();
		router.post("/users/login").handler(userService::logIn);
		return router;
	}
}
