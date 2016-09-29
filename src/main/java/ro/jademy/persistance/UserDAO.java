package ro.jademy.persistance;

import ro.jademy.domain.entities.User;

public interface UserDAO {

	User getUserByUsername(String username);

	void createUser(User user);

	void updateUser(User user);

	User getUserByUuid(String uuid);

}
