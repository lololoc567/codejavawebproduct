package com.example.oracle_C;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.oracle_C.model.User;


@Controller
public class AppController_user {
	@Autowired
	private UserRepository repo;
	@Autowired
	private UserServices service;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewHomePage() {
		return "index1";
	}
	@GetMapping("/Register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "SignUp_Form";
	}
	@GetMapping("/DN")
	public String showLoginForm2(Model model) {
		model.addAttribute("user", new User());
		return "DANGNHAP";
	}
	@GetMapping("/DNSAI")
	@ResponseBody
	public String showLoginFormDEAD(Model model) {
		model.addAttribute("user", new User());
		return "Sai hoặc tk ko tồn tại bạn ơi";
	}
//	@RequestMapping(value = "/D_N", method = RequestMethod.GET)
//	public String showLoginForm(Model model) {
//		return "Login_Form";
//	}

	@PostMapping("/process_register")
	public String ProcessRegistation(User user) {
		if(repo.findByEmail(user.getEmail()) != null) {
			return "TKTrung";
		} else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encoderpass= encoder.encode(user.getPassword());
			user.setPassword(encoderpass);
			repo.save(user);
			return "Register_Success";
		}	
	}
	
	@GetMapping("/list_users")
	public String viewListUsers(Model model) {
		List<User> listUser =repo.findAll();
		model.addAttribute("listuser", listUser);
		return "users";
	}
	@GetMapping("/test_user")
	public String testviewListUsers(Model model) {
		List<User> listUser =repo.findAll();
		model.addAttribute("listuser", listUser);
		return "users_test";
	}
	@RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
	public String Delete(@PathVariable("id") Long id , RedirectAttributes ra){
		try {
			service.deleteUser(id);
		} catch (UsernameNotFoundException e) {
			ra.addFlashAttribute("Message",e.getMessage());
		}
		return "redirect:/list_users";
	}
	@GetMapping("/user/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id , Model model, RedirectAttributes ra) {
		try {
			User user =repo.getById(id);
			model.addAttribute("user",user); 
			model.addAttribute("Page_Edit", "Edit User (ID: "+ id + ")");
			return "EditUserForm";
		} catch (UsernameNotFoundException e) {
			ra.addFlashAttribute("Message",e.getMessage());
			return "redirect:/list_users";
		}
	}
	@PostMapping("/process_edit")
	public String ProcessEdit(User user) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encoderpass= encoder.encode(user.getPassword());
			user.setPassword(encoderpass);
			repo.save(user);
			return "redirect:/list_users";
	}
}
