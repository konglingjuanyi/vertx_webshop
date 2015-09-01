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
import nl.sogeti.vertx.webshop.util.MongoClientProvider;

public abstract class WebVerticle extends AbstractVerticle {
	protected int PORT;
	protected String PATH;
	private static final String WELCOME_PAGE = "index.html";
	
	protected ProductService productService;
	protected CategoryService categoryService;
	protected OrderService orderService;
	protected UserService userService;
	
	private HttpServer server;
		
	public WebVerticle(int port, String path) {
		this.PORT = port;
		this.PATH = path;
	}
	
	@Override
	public void start() {
		MongoClientProvider.setClient(MongoClient.createShared(vertx, new JsonObject()));
		registerServices();
		createServer();
	}
	@Override
	public void stop(){
		if(server != null){
			server.close();
			System.out.println("Server shut down.");
		}
	}
	
	private void registerServices(){
		productService = new ProductService();	
		categoryService = new CategoryService();
		orderService = new OrderService();
		userService = new UserService();
	}
	
	private void createServer(){
		server = vertx.createHttpServer();
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		configureBasicRouter(router);
		server.requestHandler(router::accept).listen(PORT);
		System.out.println("Server " + this.getClass().getTypeName() + " is started.");
	}
	
	protected void configureBasicRouter(Router router){
		router.mountSubRouter("/api", createRestRouter());
		router.get("/").handler(StaticHandler.create().setWebRoot(PATH).setIndexPage(WELCOME_PAGE));
		router.get("/" + PATH + "/*").handler(StaticHandler.create().setWebRoot(PATH));
	}

	protected Router createRestRouter(){
		Router router = Router.router(vertx);
		router.get("/users/*").handler(userService::findUser);
		router.post("/users").handler(userService::addUser);
		router.post("/orders").handler(orderService::addOrder);
		router.get("/products").handler(productService::getProducts);
		router.get("/categories").handler(categoryService::getCategories);
		return router;
	}
}
