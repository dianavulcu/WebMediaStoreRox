package ro.jademy.persistance;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDAOInvocationHandler implements InvocationHandler {

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		UserDBDAO userDBDAO = UserDBDAO.getInstance();
		Class[] argsClasses = new Class[args.length];
		int i = 0;
		for (Object obj : args) {
			argsClasses[i] = args[i].getClass();
			i++;
		}
		
		Method localMethod = userDBDAO.getClass().getMethod(method.getName(), argsClasses);
		Field field = userDBDAO.getClass().getDeclaredField("connection");
		field.setAccessible(true);
		if(method.isAnnotationPresent(Postgres.class)) {
			field.set(userDBDAO, getConnection());
		} else {
			field.set(userDBDAO, getConnection());
		}
		Object invoke = localMethod.invoke(userDBDAO, args);
		((Connection)field.get(userDBDAO)).close();
		field.set(userDBDAO, null);
		return invoke;
	}
	
	Connection getConnection(){
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://ec2-54-243-203-141.compute-1.amazonaws.com:5432/db097ee5t7rs7r?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", 
					"xvseorxagpkwwn",
					"PTA8mq2JR2mBe1eR4IY6AhInua");
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
