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
					"jdbc:mysql://localhost:3306/media_store", 
					"root",
					"root");
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
