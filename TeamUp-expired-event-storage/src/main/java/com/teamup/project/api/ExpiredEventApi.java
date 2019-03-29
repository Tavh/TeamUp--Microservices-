package com.teamup.project.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamup.project.entities.ExpiredEventEntity;
import com.teamup.project.enums.ErrorType;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.logic.ExpiredEventController;

@RestController
@RequestMapping(value = "/expired-events")
public class ExpiredEventApi {

	@Autowired
	private ExpiredEventController expiredEventController;

	// ----------------------------- Create --------------------------------------

	@PostMapping
	public void createExpiredEvent (@RequestBody ExpiredEventEntity expiredEvent) throws ApplicationException {

		expiredEventController.createExpiredEvent(expiredEvent);
	}

	// -----------------------------Getters--------------------------------------
	
	@GetMapping("/{id}")
	public ExpiredEventEntity getExpiredEvent (@PathVariable long id) throws ApplicationException {
		return expiredEventController.getExpiredEvent(id);
	}

	@GetMapping("user-expired-events")
	public List<ExpiredEventEntity> getUserExpiredEvents (@RequestParam long userId) throws ApplicationException {
		
		return expiredEventController.getUserExpiredEvents(userId);
	}
}
