package com.teamup.project.api;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamup.project.entities.UserEntity;
import com.teamup.project.logic.SendMailController;

@RestController
@RequestMapping(value = "/sendmail")
public class SendMailApi {

	@Autowired
	SendMailController sendMailController;

	@PostMapping
	public void sendMail (@RequestBody UserEntity userDetails) {

		sendMailController.sendMail(userDetails);
	}
}
