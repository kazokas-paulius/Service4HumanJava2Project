package s4h.service;
//VYGANTAS
import java.util.List;

import s4h.entity.Language;
import s4h.entity.User;

public interface LangService {

	List<Language> findAllLanguages();

	List<Language> findAllByProvId(Integer provId);

	Language findByLangId(Integer langId);

	Language jobDone(Language lang);

	void saveLanguage(Language lang);

	void deleteLanguage(Integer langId);
	
	User findUserByLangId(Integer langId);
}