package nl.sogeti.vertx.webshop.data;

import io.vertx.core.Handler;
import nl.sogeti.vertx.webshop.model.User;

public interface IUserRepository {
	void addUser(User user);
	void findUser(Handler<User> handler, String userName);
}
