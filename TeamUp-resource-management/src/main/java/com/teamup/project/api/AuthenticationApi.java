package com.teamup.project.api;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamup.project.entities.UserEntity;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.logic.UserController;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationApi {

	@Autowired
	private UserController userController;
	
	// -------------------------------------Login--------------------------------------
	
	@PostMapping
	public UserEntity login (HttpServletRequest request, 
			HttpServletResponse response, @RequestBody UserEntity user) throws ApplicationException {

		String email = user.getEmail();
		String password = user.getPassword();
		
		boolean isUserLegitimate = userController.isUserLegitimate(email, password);
		

		if (isUserLegitimate) {

			request.getSession();
			Cookie cookie = new Cookie("email", user.getEmail());
			cookie.setPath("/");
			response.addCookie(cookie);

			HttpServletResponse res = (HttpServletResponse) response;
			res.setHeader("LoginStatus", "User : " + user.getNickname() + ", has logged in successfully");

			user = userController.getUser(email);
			user.setLoginStatus("OK");

			return user;
		}
		
		return null;
	}
}
