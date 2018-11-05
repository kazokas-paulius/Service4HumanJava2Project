
//KAZOKAS
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import s4h.entity.User;

@Controller
public class UserController {

	@Autowired
	private UserServiceImplementation service;
	
	// get user_data start
	@PostMapping("/get")
	public String getUsersFromDBPost() {
		return "redirect:/userList";
	}
	
	@GetMapping("/userList")
	public String getUsersFromDBGet(Model model) {
		List<User> userList = service.getUsers();
		model.addAttribute("users",userList);
		return "index";
	}
	// get user_data end
	
	// get user name start
	@PostMapping("/get2")
	public String getUserFromDBByUsernamePost(Model model, HttpServletRequest hsrq) {
		String searchRequest = hsrq.getParameter("searchUser");
		model.addAttribute("usernames", searchRequest);
		return "index";
	}
	//get user name end
}
