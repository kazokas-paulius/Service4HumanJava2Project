package s4h.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import s4h.entity.LangStatisticsEntity;
import s4h.entity.Language;
import s4h.entity.User;
import s4h.event.LangImportEvent;
import s4h.repository.LangRepository;

@Service
@Validated
@Transactional
public class LangServiceImpl implements LangService {

    private final LangRepository repository;
    private final ApplicationEventPublisher eventPublisher;

///    @Autowired
//    public void setProductRepository(ProvRepository repository) {
    public LangServiceImpl(LangRepository repository, ApplicationEventPublisher eventPublisher) {
	this.repository = repository;
	this.eventPublisher = eventPublisher;
    }

//	public List<Language> findByFirst() {
//		return repository.findAll(); // findLanguagesByFirst();
//	}

//	@Override
    public List<Language> findDifferenceLanguages() {
	return repository.findDifferenceLanguages();// findByProvId(provId);
    }

    public List<Language> findAllByProvId(Integer provId) {
//			System.out.println("repository.findFirstDistinctAndSecond();");
	return repository.findAllByUserId(provId);
    }

    @Override
    public Language findByLangId(Integer langId) {
	return repository.findByLangId(langId);
    }

    @Override
    public Language jobDone(Language lang) {
	lang.setFree(LocalDate.now());
	return lang;
    }

    @Override
    public void saveLanguage(Language lang) {
	repository.save(lang);
	int total = repository.findAll().size();
	int free = 0;
	eventPublisher.publishEvent(new LangImportEvent(LocalDateTime.now(), total, free));

    }

    @Override
    public void deleteLanguage(Integer langId) {
	repository.deleteById(langId);
	int total = repository.findAll().size();
	int free = 1;
	eventPublisher.publishEvent(new LangImportEvent(LocalDateTime.now(), total, free));

    }

    @Override
    public List<Language> findAllLanguages() {
	return repository.findAll();
    }

	@Override
	public User findUserByLangId(Integer langId) {
		return repository.findUserByLangId(langId);
	}

//	public List<Language> findByFirst() {
//		// TODO Auto-generated method stub
//		return repository.findby;
//	}

}