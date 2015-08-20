package nl.sogeti.vertx.webshop.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import nl.sogeti.vertx.webshop.service.CategoryService;
import nl.sogeti.vertx.webshop.service.OrderService;
import nl.sogeti.vertx.webshop.service.ProductService;
import nl.sogeti.vertx.webshop.service.UserService;

public class WebVerticle extends AbstractVerticle {
	private static final int PORT = 8080;
	private static final String PATH = "app/webshop";
	private static final String WELCOME_PAGE = "index.html";
	
	private ProductService productService;
	private CategoryService categoryService;
	private OrderService orderService;
	private UserService userService;
	
	private HttpServer server;
	
	@Override
	public void start() {
		MongoClient mongo = MongoClient.createShared(vertx, new JsonObject());
		registerServices(mongo);
		createServer();
	}
	@Override
	public void stop(){
		if(server != null){
			server.close();
			System.out.println("Server shut down.");
		}
	}
	
	private void registerServices(MongoClient mongo){
		productService = new ProductService(mongo);	
		categoryService = new CategoryService(mongo);
		orderService = new OrderService(mongo);
		userService = new UserService(mongo);
	}
	
	private void createServer(){
		server = vertx.createHttpServer();
		server.requestHandler(createRouter()::accept).listen(PORT);
		System.out.println("Server is started.");
	}
	
	private Router createRouter(){
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.mountSubRouter("/api", createRestRouter());
		router.get("/").handler(StaticHandler.create().setWebRoot(PATH).setIndexPage(WELCOME_PAGE));
		router.get("/" + PATH + "/*").handler(StaticHandler.create().setWebRoot(PATH));
		return router;
	}
	
	private Router createRestRouter(){
		Router router = Router.router(vertx);
		router.post("/users").handler(userService::addUser);
		router.post("/orders").handler(orderService::addOrder);
		router.get("/products").handler(productService::getProducts);
		router.get("/categories").handler(categoryService::getCategories);
		return router;
	}
}
