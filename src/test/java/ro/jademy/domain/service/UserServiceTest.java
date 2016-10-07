/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.jademy.domain.service;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import ro.jademy.domain.entities.User;
import ro.jademy.persistance.UserDAO;

/**
 *
 * @author mihai
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
	ServiceLocator serviceLocator;
	
	@Mock
	UserDAO userDAO;
	
	@Test()
	public void checkPasswordTest() {
		User userToVerify = new User("admin", "admin", "email");
		User userToVerify2 = new User("admin2", "admin", "email");
		
		User userFromDB = new User("admin1", "123", "");
		User userFromDB2 = new User("admin2", "admin", "");
		
		UserService service = new UserService();
		service.setServiceLocator(serviceLocator);
		when(serviceLocator.getUserDao()).thenReturn(userDAO);
		
		when(userDAO.getUserByUsername(userToVerify.getUsername())).thenReturn(userFromDB);
		when(userDAO.getUserByUsername(userToVerify2.getUsername())).thenReturn(userFromDB2);
		
		assertNull("Test picat", service.checkPassword(userToVerify));
		assertNotNull("Test picat", service.checkPassword(userToVerify2));
	}
}
