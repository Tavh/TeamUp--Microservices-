package com.teamup.project.service_communicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.teamup.project.entities.EventEntity;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.logic.EventController;

@RestController
public class ExpiredEventServiceDispatcher {

	@Autowired
	ExpiredEventServiceClient exEventServiceClient;
	@Autowired
	private EventController eventController;
	
	public void transferEventToExpiredEventsServer(EventEntity event) throws ApplicationException{
		ResponseEntity<String> res = exEventServiceClient.transferEventToExpiredServer(event);
		
		if ((res.getStatusCode().equals(HttpStatus.OK))) {
			eventController.removeEvent(event.getId());
		}
	}
}

