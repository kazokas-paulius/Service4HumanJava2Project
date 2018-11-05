package s4h.service;
import java.util.List;

import s4h.entity.User;

public interface UserService {
	User getUserById(Integer id);

	void saveUser(User provider);

	void updateUser(Integer id, User provider);

	void deleteUser(Integer id);

	List<User> getUsers(String viewType);
	
//	List<Provider> findAllFree();

//	List<Provider> findByFreeBeforeOrderByFree(LocalDate today);

//	List<Provider> findAll();

	List<User> findAllUsers();

	List<User> findAllFreeUsers();
	
	
	
	
}