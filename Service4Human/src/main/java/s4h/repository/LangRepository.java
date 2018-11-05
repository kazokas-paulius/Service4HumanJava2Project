package s4h.repository;
//VYGANTAS
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s4h.entity.Language;
import s4h.entity.User;

//@Repository
public interface LangRepository extends JpaRepository<Language, Integer> {

//	List<Language> findAllLanguages(); // WhereFreeIsNotNull(); //Free();

//	List<Language> findByFirst(String name);

	List<Language> findAllByUserId(Integer id);

//	@Query("SELECT distinct first, second  FROM Language")
	@Query(value = "select l.* from s4h.languages as l inner join "
			+ "(select l2.first,min(l2.free) mDate from languages l2 group by l2.first) tbl1 "
			+ "on tbl1.first=l.first where tbl1.mDate=l.free", nativeQuery = true)
	List<Language> findDifferenceLanguages();

	Language findByLangId(Integer langId);

	List<Language> findAll();

	User findUserByLangId(Integer langId);
}