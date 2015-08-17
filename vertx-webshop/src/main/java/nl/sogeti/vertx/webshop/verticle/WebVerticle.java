package nl.sogeti.vertx.webshop.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
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
		MongoClient mongo = MongoClient.createShared(vertx, new JsonObject());
		productService = new ProductService(mongo);	
		createServer();
	}
	@Override
	public void stop(){
		if(server != null){
			server.close();
			System.out.println("Server shut down.");
		}
	}
	
	private void createServer(){
		server = vertx.createHttpServer();
		server.requestHandler(createRouter()::accept).listen(PORT);
		System.out.println("Server is started.");
	}
	
	private Router createRouter(){
		Router router = Router.router(vertx);
		router.mountSubRouter("/api", createRestRouter());
		router.get("/").handler(StaticHandler.create().setWebRoot(PATH).setIndexPage(WELCOME_PAGE));
		router.get("/" + PATH + "/*").handler(StaticHandler.create().setWebRoot(PATH));
		return router;
	}
	
	private Router createRestRouter(){
		Router router = Router.router(vertx);
		router.get("/products").handler(productService::getProducts);
		router.get("/categories").handler(productService::getCategories);
		return router;
	}
}
