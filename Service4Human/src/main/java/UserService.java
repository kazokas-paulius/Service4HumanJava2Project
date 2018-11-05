
//KAZOKAS
import java.util.List;

import org.springframework.stereotype.Service;

import s4h.entity.User;

//surasti pagal laikus
@Service
public interface UserService {
	List<User> getUsers();
	String getUserByUsername(User u);
}