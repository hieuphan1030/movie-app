package com.bezkoder.springjwt.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.UpdatePassword;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/test/user")
public class UserController {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> showUpdate(@PathVariable("id") long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		return ResponseEntity.ok(new JwtResponse(user.getUsername(), user.getEmail()));
	}

	@PostMapping("/password/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updatePassword(@PathVariable("id") long id, @Valid @RequestBody UpdatePassword update) {

		User oldUser = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		if (update.getNewPassword().equals(update.getOldPassword())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu mới và cũ không được trùng!"));

		}

		if (!encoder.matches(update.getOldPassword(), oldUser.getPassword())) {

			return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu hiện tại không đúng!"));

		} else {
			oldUser.setPassword(encoder.encode(update.getNewPassword()));
			userRepository.save(oldUser);
		}

		return ResponseEntity.ok(new MessageResponse("Cập nhập mật khẩu thành công!"));
	}

}