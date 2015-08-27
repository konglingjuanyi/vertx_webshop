package nl.sogeti.vertx.webshop.verticle;

import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.ext.web.handler.BasicAuthHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.UserSessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import nl.sogeti.vertx.webshop.security.myAuthProvider;

public class AdminPanelVerticle extends WebVerticle {
	public AdminPanelVerticle(int port, String path) {
		super(port, path);
	}
	
	@Override
	protected void configureBasicRouter(Router router) {
		AuthProvider authProvider = new myAuthProvider();
		router.route().handler(CookieHandler.create());
		router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx, "sessions", 1800000)));
		router.route().handler(UserSessionHandler.create(authProvider));
		AuthHandler basicAuthHandler = BasicAuthHandler.create(authProvider);
		basicAuthHandler.addAuthority("role:admin");
		router.route("/").handler(basicAuthHandler).failureHandler(ErrorHandler.create());
		super.configureBasicRouter(router);
	}
}
