package s4h.service;

import java.time.LocalDateTime;
//VYGANTAS
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import s4h.entity.User;
import s4h.event.LangImportEvent;
import s4h.repository.UserRepository;

@Service
@Validated
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
 
///    @Autowired
//    public void setProductRepository(ProvRepository repository) {
    public UserServiceImpl(UserRepository repository) {
	this.repository = repository;
    }

    @Override
    public User getUserById(Integer id) {
	return repository.getOne(id);
    }

    @Override
    public void saveUser(User provider) {
	repository.save(provider);
    }

    @Override
    public void updateUser(Integer id, User provider) {
//		  Provider updated = repository.getOne(id);
//	        updated.setDone(done);
//	        updated.setMessage(message);
	provider.setId(id);
	repository.save(provider);

    }

    @Override
    public void deleteUser(Integer id) {
	repository.deleteById(id);
    }

//	@Override
//	public List<Provider> findAllFreeProviders() {
//		return repository.findByFreeBeforeOrderByFree(LocalDate.now()); //findAll();// findAllFree();
//	}

    @Override
    public List<User> findAllUsers() {
	return repository.findAll();
    }

    @Override
    public List<User> getUsers(String viewType) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<User> findAllFreeUsers() {
	// TODO Auto-generated method stub
	return null;
    }

//	@Override
//	public List<Provider> getProviders(String viewType) {
//		
//		System.out.println(viewType + "-------------" + viewType);
//		if (viewType.equals("free")) {
//			return repository.findByFreeBeforeOrderByFree(LocalDate.now());
//		} else {
//			return repository.findAll();

//				repository.findAll().stream().collect(Collectors.toList());
//		List<Provider> prov = 			
    // return repository.findAll().stream()
//				.forEach(pr -> System.out.println(pr.getName()));	
    // .filter(pr ->
    // pr.getFree().isBefore(LocalDate.now())).collect(Collectors.toList());
//		Collections.sort(prov, );
//		System.out.println("---------------" + prov);
    // return new ArrayList<Provider>();

//	public List<Provider> findByFreeBeforeOrderByFree(LocalDate today) {
//		return repository.findByFreeBeforeOrderByFree(today);
//	}

}