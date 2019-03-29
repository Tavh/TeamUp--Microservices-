package com.teamup.project.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.teamup.project.entities.EventEntity;
import com.teamup.project.enums.ErrorType;
import com.teamup.project.exceptions.ApplicationException;

@Repository
public class EventRepos {

	@PersistenceContext
	private EntityManager entityManager;

// ---------------------------------------Create a new event-----------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public long createEvent(EventEntity event){

		entityManager.persist(event);

		return event.getId();
	}

// ------------------------------update a event------------------
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateEvent(EventEntity event){

		entityManager.merge(event);
	}

// ------------------------------remove a event------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public void removeEvent(long id) throws ApplicationException {

		EventEntity event = entityManager.find(EventEntity.class, id);
		
		if (event == null) {
			throw new ApplicationException (ErrorType.DATA_NOT_FOUND, "The event you're trying to remove does not exist");
		}

		entityManager.remove(event);
	}

// -------------------------------------Getters----------------------------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public EventEntity getEvent(long eventId) throws ApplicationException {

		EventEntity event = entityManager.find(EventEntity.class, eventId);

		return event;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<EventEntity> getUserEvents(long userId) throws ApplicationException {
		
		Query query = entityManager.createQuery("SELECT event FROM EventEntity event JOIN event.eventMembers as members WITH members.id=:userId")
				.setParameter("userId", userId);

		@SuppressWarnings("unchecked")
		List<EventEntity> userEvents = query.getResultList();
		
		return userEvents;
	}
	
}
