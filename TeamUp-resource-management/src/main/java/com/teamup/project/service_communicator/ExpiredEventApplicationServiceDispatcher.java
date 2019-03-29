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
public class ExpiredEventApplicationServiceDispatcher {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private EventController eventController;
	
	public void TransferEventToExpiredEventsServer(EventEntity event) throws ApplicationException{
		
		HttpEntity<EventEntity> requestBody = new HttpEntity<>(event);
		
		ResponseEntity<String> res = restTemplate.exchange("http://expired-event-service/expired-events", HttpMethod.POST, requestBody, String.class);

		
		if ((res.getStatusCode().equals(HttpStatus.OK))) {
			eventController.removeEvent(event.getId());
		}
		
	}
}
