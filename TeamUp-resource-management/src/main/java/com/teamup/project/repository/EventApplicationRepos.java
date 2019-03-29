package com.teamup.project.repository;

import java.util.List;


import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.teamup.project.entities.EventApplicationEntity;
import com.teamup.project.enums.ErrorType;
import com.teamup.project.exceptions.ApplicationException;

@Repository
public class EventApplicationRepos {


	@PersistenceContext
	private EntityManager entityManager;

// ---------------------------------------Create a new company-----------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public long createEventApplication(EventApplicationEntity app){

		entityManager.persist(app);

		return app.getId();
	}

// ------------------------------remove a company------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public void removeEventApplication(long id) throws ApplicationException {


		EventApplicationEntity app = entityManager.find(EventApplicationEntity.class, id);
		
		if (app == null) {
			throw new ApplicationException (ErrorType.DATA_NOT_FOUND, "The event application you're trying to remove does not exist");
		}
		
		entityManager.remove(app);
	}

// -------------------------------------Getters----------------------------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public EventApplicationEntity getEventApplication(long appId) throws ApplicationException {

		EventApplicationEntity app = entityManager.find(EventApplicationEntity.class, appId);

		return app;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<EventApplicationEntity> getEventApplicationsInEvent(long eventId) throws ApplicationException {
		Query query = entityManager.createQuery("FROM EventApplicationEntity WHERE event_id=:eventId").setParameter("eventId", eventId);
		
		@SuppressWarnings("unchecked")
		List<EventApplicationEntity> allApps = query.getResultList();
		
		return allApps;
	}
	
	// ------------------------------------etc-------------------------------------

	public boolean isEventApplicationPending (long senderId, long eventId) {
		Query query = entityManager.createQuery("FROM EventApplicationEntity WHERE sender_id=:senderId AND event_id=:eventId")
				.setParameter("senderId", senderId).setParameter("eventId", eventId);
		
		List results = query.getResultList();

		if(results.isEmpty()) {
			return false;
		} 

		return true;
	}
	
}
