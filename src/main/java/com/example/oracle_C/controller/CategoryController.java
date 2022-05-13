package com.example.oracle_C.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.oracle_C.model.User;

public class CategoryController {
	@GetMapping("/list_Categories")
	public String viewListUsers(Model model) {
		/* List<User> listUser =repo.findAll(); */
		/* model.addAttribute("listuser", listUser); */
		return "users";
	}
}
