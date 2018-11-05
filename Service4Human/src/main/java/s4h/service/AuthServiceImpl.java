package s4h.service;
//VYGANTAS
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import s4h.entity.User;
import s4h.repository.AuthRepository;

@Service
@Transactional
public class AuthServiceImpl {

	private AuthRepository rep;

//	private PasswordEncoder passwordEncoder;

	public AuthServiceImpl(AuthRepository rep) { // , PasswordEncoder passwordEncoder) {
		this.rep = rep;
//		this.passwordEncoder = passwordEncoder;
	}

//	Optional<Provider> findByUsernameAndPassword(String username, String password) {
//		List<Provider> allProviders = rep.findAll();
//		for (Provider provider : allProviders) {
//			if (provider.getName().equals(username) && provider.getPassw().equals(password)) {
//				return Optional.ofNullable(provider);
//			}
//		}
//		return Optional.empty();
//
//	}

	public String authenticateProvider(String username, String password) {
		if (rep.findByUsernameAndPassword(username, password).isPresent()) {
			return rep.findByUsername(username).get().getSort(); // == "T";// ) {
		}
		return "N";
	}

	public boolean registerUser(String username, String password, String sort) {
		if (rep.findByUsername(username).isPresent()) {
			return false;
		} else {
			User user = new User().setUsername(username).setPassword(password);
//					.setPassword(passwordEncoder.encode(password))
//					.setRoles(Set.of(Roles.CUSTOMER));
			rep.save(user);
			return true;
		}

	}

}