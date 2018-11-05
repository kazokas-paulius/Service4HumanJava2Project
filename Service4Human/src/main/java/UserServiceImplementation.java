
//KAZOKAS
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import s4h.entity.User;

@Service
@Transactional
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> getUsers() {
		return repository.findAll();
	}
	
	@Override
	public String getUserByUsername(User u) {
		return repository.getUser_DataByUsername(u);
	}
}