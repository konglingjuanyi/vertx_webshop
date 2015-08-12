package nl.sogeti.vertx.webshop.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class StaticHttpVerticle extends AbstractVerticle {
	private static final int PORT = 8080;
	private static final String PATH = "app/webshop";
	private static final String WELCOME_PAGE = "index.html";

	@Override
	public void start() {
		Router router = Router.router(vertx);

		// Serve the static pages
		router.get("/").handler(StaticHandler.create().setWebRoot(PATH).setIndexPage(WELCOME_PAGE));
		router.get("/" + PATH + "/*").handler(StaticHandler.create().setWebRoot(PATH));
		vertx.createHttpServer().requestHandler(router::accept).listen(8080);

		System.out.println("Server is started");
	}
}
