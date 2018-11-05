package s4h.repository;
//KAZOKAS
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s4h.entity.OrderTemplate;

@Repository
public interface OrdeRepository  extends JpaRepository<OrderTemplate, Integer>{
	
}