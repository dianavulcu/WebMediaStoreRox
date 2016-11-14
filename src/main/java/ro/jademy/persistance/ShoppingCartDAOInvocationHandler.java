package ro.jademy.persistance;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class ShoppingCartDAOInvocationHandler implements InvocationHandler {

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		ShoppingCartDBDAO shoppingCartDBDAO = ShoppingCartDBDAO.getInstance();
		Class[] argsClasses = new Class[args.length];
		int i = 0;
		for (Object obj : args) {
			argsClasses[i] = args[i].getClass();
			i++;
		}

		Method localMethod = shoppingCartDBDAO.getClass().getMethod(method.getName(), argsClasses);
		Field field = shoppingCartDBDAO.getClass().getDeclaredField("connection");
		field.setAccessible(true);
		if (method.isAnnotationPresent(Postgres.class)) {
			field.set(shoppingCartDBDAO, ConnectionManager.getConnection());
		} else {
			field.set(shoppingCartDBDAO, ConnectionManager.getConnection());
		}
		Object invoke = localMethod.invoke(shoppingCartDBDAO, args);
		((Connection) field.get(shoppingCartDBDAO)).close();
		field.set(shoppingCartDBDAO, null);
		return invoke;
	}
}
