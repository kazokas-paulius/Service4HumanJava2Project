package s4h.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import s4h.entity.Language;
import s4h.entity.User;
import s4h.repository.AuthRepository;
import s4h.service.LangServiceImpl;
import s4h.service.UserServiceImpl;

@Controller
@SessionAttributes("provId")
public class LangThController {
    private Integer provId;
    @Autowired
    private UserServiceImpl provService;
    @Autowired
    private LangServiceImpl langService;
    @Autowired
    private AuthRepository authService;
    ResourceBundle messages = ResourceBundle.getBundle("LocaleMessages", Locale.getDefault());

    @ModelAttribute("provId")
    public int setId() {
	System.out.println("provIddddd = " + provId);
	if (provId == null) {
	    provId= 0;
	}
	return provId;
    }

    @GetMapping("/languages")
    public String getProducts(Model model) { // , RedirectAttributes attr) {
	System.out.println("@GetMapping(/languages) prId= " + provId);
	List<Language> langs = langService.findAllByProvId(provId);
	System.out.println("Languages === " + langs);
	model.addAttribute("languages", langs);
//		model.addAttribute("lang", lang.get(0));

//		return "index";
	return "operations/langList";
    }

    @GetMapping("/")
    public String intiProvId(Model model) {
	System.out.println("INIT provId");
	model.addAttribute("provId", provId);
	return "operations/login";
	
    }
    
    @GetMapping("/login")
    public String loginTransl(Model model) {
	    //@SessionAttribute("provId") int id) {
	provId = 0;
	model.addAttribute("provId", provId);
	System.out.println("login..." + provId);
//	session.setAttribute("provId",	provId);
	return "operations/login";
    }

    @PostMapping("/login")
    public String registerUser(@ModelAttribute("provId") int id, HttpServletRequest request, Model model) {
	String user = request.getParameter("username");
	String passw = request.getParameter("password");
	System.out.println(user + " " + passw);
	Optional<User> prov = authService.findByUsernameAndPassword(user, passw);
	System.out.println("provvvvv -------" + prov);
	if (!prov.isPresent()) {
	    String errorMsg = messages.getString("errorBadUser");
	    System.out.println("errorMsg ---" + errorMsg);
	    model.addAttribute("error", errorMsg); // "Bad user");
	    System.out.println("Bad username or password");
//			return "redirect:/login";
	    return "operations/login";
	}
	provId = prov.orElseThrow().getId();
	String role = prov.get().getSort();
	System.out.println("provId === " + provId + " is " + role + " id = " + id);
	model.addAttribute("provId", provId);
	List<Language> langs;
	switch (role) {
	case "T":
	    System.out.println("TTT");
	    langs = langService.findAllByProvId(provId);
	    // model.addAttribute("provId", provId);
	    model.addAttribute("languages", langs);
	    return "/operations/langList";
//			break;
	case "U":
	    System.out.println("UUU");
	    langs = langService.findAllLanguages();
	    model.addAttribute("languages", langs);
	    return "/operations/langSelect";
//			break;
	default:
	    System.out.println("BAD");

	}

	return "redirect:/languages";
    }

    @GetMapping("/edit/{id}")
    public String editLang(@PathVariable Integer id, Model model) {

	Language editLang = langService.findByLangId(id); // .getProviderById(id);
	System.out.println(editLang.getUserId() + "--- /edit/{id} ---" + editLang.getLangId());
	model.addAttribute("editLang", editLang);
	// model.addAttribute("provId", provId);
	System.out.println("Edit " + editLang);
//		return "redirect:/languages";
	// return "/operations/langList";

	return "/operations/editLang";
    }

    @GetMapping("/delete/{id}")
    public String deleteLang(@PathVariable Integer id) { // , Model model) {

	langService.deleteLanguage(id);
//		return "/operations/editLang";
	return "redirect:/languages";

//		return "redirect:/"; //operations/langList";

    }

    @GetMapping("/newLang")
    public String newLang(Model model) {
	Language newLang = new Language();
//		langService.saveLanguage(newLang);
	model.addAttribute("editLang", newLang);
	return "/operations/editLang";
//		return "/edit/{" + 1
	// newLang.getLangId()
//				+ "}";
    }

    @GetMapping("/create/{id}")
    public String inputNew(Model model, @PathVariable String id) {
	System.out.println("Active provider --- " + id);
	String username = "";
	String password = "";
	String name = "";
	String mail = "";
	int pId = Integer.valueOf(id);
	if (pId != 0) {
	    User prov = provService.getUserById(pId);
	    username = prov.getUsername();
	    name = prov.getName();
	    mail = prov.getMail();
	}
	// model.addAttribute("id", provId);
	model.addAttribute("username", username);
	model.addAttribute("password", password);
	model.addAttribute("name", name);
	model.addAttribute("mail", mail);
	return "operations/create";

    }

    @PostMapping("/create/{id}")
    public String updateProv(@PathVariable Integer id, @ModelAttribute User editProv, Model model) {
	User startProv = provService.getUserById(id);

	if (id != 0) {
	    editProv.setSort(startProv.getSort());
	    editProv.setUsername(startProv.getUsername());
	}
	if (editProv.getPassword() == "") {
	    System.out.println("password is " + editProv.getPassword());
	    model.addAttribute("error", messages.getString("errorBadPass"));
	    return "operations/create";
//			return "redirect:/create";
	}
	System.out.println("/create/id --- " + editProv.getId());

	provService.updateUser(id, editProv);
	return "/operations/login";
    }

    @PostMapping("/create")
    public String createNew(HttpServletRequest request, Model model) {

	String username = (String) request.getParameter("username");
	if (authService.findByUsername(username).isPresent()) {
	    System.out.println("Duplicate username");
	    return "operations/login";
	}
	if (request.getParameter("password") == null) {
	    System.out.println("password is wrong");
	    model.addAttribute("error", messages.getString("errorBadPass"));
	    return "/create/{id}";
	}

	String password = (String) request.getParameter("password");
	String mail = (String) request.getParameter("mail");
	String name = (String) request.getParameter("name");
	String sort = (String) request.getParameter("sort");
	System.out.println(name + " Sort type - " + sort);
	User prov = new User(username, password, name, mail, sort);
	System.out.println(prov);
	provService.saveUser(prov);
//		if (sort.equals("T")) {
//		} else {
//		}
	return "/operations/login";
    }

    @GetMapping("/logout")
    public String logoutProv(Model model) {
	provId = 0;
	model.addAttribute("provId", provId);
	System.out.println("session.provId === " + provId);

	return "/operations/login";
    }

//	@PostMapping("/update")
    public String addProduct(HttpServletRequest request) { // , Model model) {
	System.out.println("fiddd = " + request.getParameter("fid"));
	Language edit;
	if (request.getParameter("fid").equals("0")) {
	    edit = new Language();
	    edit.setUserId(provId);
	} else {
	    edit = langService.findByLangId(Integer.valueOf(request.getParameter("fid")));
	}
	edit.setFirst(request.getParameter("ffirst"));
	edit.setSecond(request.getParameter("fsecond"));
	edit.setWork(Byte.valueOf(request.getParameter("fwork")));
	edit.setFree(LocalDate.parse(request.getParameter("ffree")));
	edit.setPrice(Float.valueOf(request.getParameter("fprice")));
	langService.saveLanguage(edit);

	List<Language> lang = langService.findAllByProvId(provId);
	System.out.println("Languages update === " + lang);
//		model.addAttribute("languages", lang);
//		return "operations/langList";
	return "redirect:/languages";

    }

    @PostMapping("/update")
    public String updateLang(@ModelAttribute Language editLang) {
	System.out.println(editLang.getUserId() + "--- /update ---" + editLang.getLangId());
	if (editLang.getLangId() == 0) {
	    editLang.setUserId(provId);
	}
	System.out.println(editLang.getUserId() + "--- /update 2 ---" + editLang); // .getLangId());

	langService.saveLanguage(editLang);
	System.out.println(editLang.getUserId() + "--- /update after ---" + +editLang.getLangId());
	return "redirect:/languages";
    }
    
    @GetMapping("/use/{id}")
    public String orderTempGet(Model model, @PathVariable String id) {
    	System.out.println("Active provider --- " + id);
    	int oId = Integer.valueOf(id);
    	String user_name = "";
    	String provider_name = "";
    	String lang_from = "";
    	String lang_to = "";
    	float price = 0;
    	if (oId != 0) {
    		Language provLang = langService.findByLangId(Integer.valueOf(id));
    		if (!id.isEmpty()) {
    			User provUser = provService.getUserById(Integer.valueOf(id));
    			if (provUser.getId() == oId) {
    				if (provUser.getSort().equals("T")) {
    					provider_name = provUser.getName();
    				} else {
    					provider_name = "NULL";
    				}
    				User current_user = provService.getUserById(provId);
    				user_name = current_user.getName();
    				
    				lang_from = provLang.getFirst();
    				lang_to = provLang.getSecond();
    				price = provLang.getPrice();
    			} else {
    				System.out.println("Id mismatch " + provUser.getId() + "with " + id + "!");
    			}
    		}
    	}
    	model.addAttribute("user_name", user_name);
    	model.addAttribute("provider_name", provider_name);
    	model.addAttribute("lang_from", lang_from);
    	model.addAttribute("lang_to", lang_to);
    	model.addAttribute("price", price);
    	return "/operations/order";
    }
}
