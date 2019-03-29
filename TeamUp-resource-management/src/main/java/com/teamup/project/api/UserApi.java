package com.teamup.project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamup.project.entities.UserEntity;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.logic.UserController;

@RestController
@RequestMapping(value = "/users")
public class UserApi {

	@Autowired
	private UserController userController;

	// ------------------------------------Creates a new user---------------------------------

	@PostMapping
	public void createUser (@RequestBody UserEntity user) throws ApplicationException {
		userController.createUser(user);
	}

	// ------------------------------------Remove---------------------------------

	@DeleteMapping("/{id}")
	public void removeUser (@PathVariable long id) throws ApplicationException {
		userController.removeUser(id);
	}
	// ------------------------------------Getters---------------------------------

	@GetMapping("/{id}")
	public UserEntity getUser (@PathVariable long id) throws ApplicationException {
		return userController.getUser(id);
	}

	@GetMapping("/byemail")
	public UserEntity getUser (@RequestParam String email) throws ApplicationException {
		return userController.getUser(email);
	}
}
