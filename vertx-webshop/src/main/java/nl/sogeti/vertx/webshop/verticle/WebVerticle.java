package nl.sogeti.vertx.webshop.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import nl.sogeti.vertx.webshop.service.ProductService;

public class WebVerticle extends AbstractVerticle {
	private static final int PORT = 8080;
	private static final String PATH = "app/webshop";
	private static final String WELCOME_PAGE = "index.html";
	
	private ProductService productService;
	private HttpServer server;
	@Override
	public void start() {
		Router router = Router.router(vertx);
		productService = new ProductService();
		//REST Routing
		router.get("/api/products").handler(productService::getProducts);
		// Static Routing
		router.get("/").handler(StaticHandler.create().setWebRoot(PATH).setIndexPage(WELCOME_PAGE));
		router.get("/" + PATH + "/*").handler(StaticHandler.create().setWebRoot(PATH));
		
		server = vertx.createHttpServer();
		server.requestHandler(router::accept).listen(PORT);
		System.out.println("Server is started.");
	}
	@Override
	public void stop(){
		if(server != null){
			server.close();
			System.out.println("Server shut down.");
		}
	}
}
