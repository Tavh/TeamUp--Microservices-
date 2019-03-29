package com.teamup.project.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamup.project.entities.EventEntity;
import com.teamup.project.entities.EventMemberInsertionObject;
import com.teamup.project.enums.ErrorType;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.logic.EventApplicationController;
import com.teamup.project.logic.EventController;

@RestController
@RequestMapping(value = "/events")
public class EventApi {

	@Autowired
	private EventController eventController;
	@Autowired
	private EventApplicationController eventAppController;

	// ----------------------------- Create --------------------------------------

	@PostMapping("/{eventLeaderId}")
	public void createEvent (@RequestBody EventEntity event, @PathVariable long eventLeaderId) throws ApplicationException {

		eventController.createEvent(event, eventLeaderId);
	}

	// ----------------------------- Remove --------------------------------------

	@DeleteMapping("/{id}")
	public void removeEvent(@PathVariable long id) throws ApplicationException {

		eventController.removeEvent(id);
	}

	@DeleteMapping("/expired/{id}")
	public void removeExpiredEvent(@PathVariable long id)  throws ApplicationException {
		eventController.removeExpiredEvent(id);
	}

	// -----------------------------Updates a event--------------------------------------

	// Might be better to refactor this to the controller later
	@PutMapping("/addmember")
	public void addEventMember(@RequestBody EventMemberInsertionObject eventAndMemberIdentities) throws ApplicationException {

		long eventId = eventAndMemberIdentities.getEventId();
		long userId = eventAndMemberIdentities.getUserId();

		if (eventController.getEvent(eventId).isEventPrivate()) {
			throw new ApplicationException (ErrorType.TEAM_IS_PRIVATE, ErrorType.TEAM_IS_PRIVATE.getInternalMessage());
		}

		eventController.addEventMember(eventId, userId);
	}

	// Might be better to refactor this to the controller later
	@PutMapping("/addmemeberthroughapp")
	public void addEventMemberThroughApp(@RequestBody EventMemberInsertionObject eventAndMemberIdentities, @RequestParam long appId) throws ApplicationException {
		// Removes the event application
		eventAppController.removeEventApplication(appId);

		long eventId = eventAndMemberIdentities.getEventId();
		long userId = eventAndMemberIdentities.getUserId();

		eventController.addEventMember(eventId, userId);
	}

	// -----------------------------Getters--------------------------------------

	@GetMapping("/{id}")
	public EventEntity getEvent (@PathVariable long id) throws ApplicationException {
		return eventController.getEvent(id);
	}

	@GetMapping("userevents")
	public List<EventEntity> getUserEvents (@RequestParam long userId) throws ApplicationException {

		return eventController.getUserEvents(userId);
	}
}
