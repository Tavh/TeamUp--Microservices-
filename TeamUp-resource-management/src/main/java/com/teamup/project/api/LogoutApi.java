package com.teamup.project.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logout")
public class LogoutApi {

	@GetMapping
	public void logout (HttpServletRequest request, 
			HttpServletResponse response)  throws Throwable {

		String email = "No email detected";
		
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("email")) {
				email = cookie.getValue();
				Cookie userCookie = new Cookie("email", null);
				userCookie.setValue(null);
				userCookie.setPath(request.getContextPath()); 
				userCookie.setMaxAge(0); 
				response.addCookie(userCookie);
			}
		}
				
		request.getSession().invalidate();
		
		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setValue(null);
		cookie.setPath(request.getContextPath()); 
		cookie.setMaxAge(0); 
		response.addCookie(cookie);
		
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("LogoutStatus", "User : " +  email + " has logged out successfully");
	}
}