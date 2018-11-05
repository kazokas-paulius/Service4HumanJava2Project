package s4h.repository;
//VYGANTAS
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4h.entity.User;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findAll(); //WhereFreeIsNotNull(); //Free();
	List<User> findByName(String name); 
	User findByUsername(String username);
//	List<Provider> findByFreeBeforeNowOrderByFree(LocalDate now); 
//	List<Provider> findByFreeOrderByFree();

//	List<Provider> findByFreeBeforeOrderByFree(LocalDate today); 
//	List<Provider> findByFreeBeforeOrderByFree(LocalDate today); 
	
	
}