package com.teamup.project.service_communicator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.teamup.project.entities.EventEntity;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.logic.EventController;

@RestController
public class ExpiredEventServiceDispatcher {

	@Autowired
	ExpiredEventServiceClient expiredEventServiceClient;
	@Autowired
	private EventController eventController;
	
	public void transferEventToExpiredEventsServer(EventEntity event) throws ApplicationException{
		ResponseEntity<String> res = expiredEventServiceClient.transferEventToExpiredServer(event);
		
		if ((res.getStatusCode().equals(HttpStatus.OK))) {
			eventController.removeEvent(event.getId());
		}
	}
}

