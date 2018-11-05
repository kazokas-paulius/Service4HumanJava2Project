package s4h.repository;
//VYGANTAS
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s4h.entity.User;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsernameAndPassword(String username, String password);
//	String findByUsernameAndPassword(String username, String password);
	Optional<User> findByUsername(String username);
}