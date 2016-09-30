package ro.jademy.domain.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.stereotype.Service;

import ro.jademy.persistance.UserDAO;
import ro.jademy.persistance.UserDAOInvocationHandler;
import ro.jademy.persistance.UserPropDAO;

@Service
public class ServiceLocator {
	public UserDAO getUserDao() {
		String daoType = System.getenv("DAO_IMPL");
		if ((daoType == null) || (daoType.equals("DB"))) {
			InvocationHandler handler = new UserDAOInvocationHandler();
			UserDAO proxy = (UserDAO) Proxy.newProxyInstance(UserDAO.class.getClassLoader(),
					new Class[] { UserDAO.class }, handler);
			return proxy;


		}
		if (daoType.equals("PROP")) {
			return UserPropDAO.getInstance();
		}
		throw new WrongApplicationConfiguration(String.format("wrong system propriety %s value %s", "DAO_IMPL", daoType));
	}
}
