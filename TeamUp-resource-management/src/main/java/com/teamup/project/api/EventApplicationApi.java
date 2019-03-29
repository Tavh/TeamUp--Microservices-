package com.teamup.project.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamup.project.entities.EventApplicationEntity;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.logic.EventApplicationController;


@RestController
@RequestMapping(value = "/applications")
public class EventApplicationApi {

	@Autowired
	private EventApplicationController eventApplicationController;
	
	// -----------------------------Creates a new invitation--------------------------------------

	@PostMapping
	public void createEventApplication (@RequestParam long senderId, @RequestParam long eventId, @RequestBody EventApplicationEntity inv) throws ApplicationException {
		eventApplicationController.createEventApplication(senderId, eventId, inv);
	}

	// -----------------------------Getters--------------------------------------
	
	@GetMapping("/{id}")
	public EventApplicationEntity getEventApplication (@PathVariable long id) throws ApplicationException {
		return eventApplicationController.getEventApplication(id);
	}
	
	@GetMapping("/appsinevent")
	public List <EventApplicationEntity> getEventApplicationsInEvent (@RequestParam long eventId) throws ApplicationException {
			
		return eventApplicationController.getEventApplicationsInEvent(eventId);
	}
	
}
