package com.teamup.project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamup.project.entities.UserEntity;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.logic.SignUpService;



@RestController
@RequestMapping(value = "/sign-up")
public class SignUpApi {

	@Autowired
	private SignUpService signUpService;
	
	@PostMapping
	public void createUser (@RequestBody UserEntity user) throws ApplicationException {
		
		signUpService.createUser(user);
	}
}
