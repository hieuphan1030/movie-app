package com.bezkoder.springjwt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/admin/all")
	public List<User> showAllUser() {
		List<User> result = (List<User>) userRepository.findAll();
		if (result.size() > 0)
			return result;
		else
			return new ArrayList<User>();
	}
	@DeleteMapping("/admin/{id}")
	public void deleteMovieFavourite(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
