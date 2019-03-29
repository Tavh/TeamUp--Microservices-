package com.teamup.project.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.teamup.project.entities.ExpiredEventEntity;
import com.teamup.project.enums.ErrorType;
import com.teamup.project.exceptions.ApplicationException;

@Repository
public class ExpiredEventRepos {

	@PersistenceContext
	private EntityManager entityManager;

// --------------------------------------- Create -----------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public long createExpiredEvent(ExpiredEventEntity expiredEvent){

		entityManager.persist(expiredEvent);

		return expiredEvent.getId();
	}

// ------------------------------ update -------------------------------------
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateExpiredEvent(ExpiredEventEntity expiredEvent){

		entityManager.merge(expiredEvent);
	}

// ------------------------------remove a event------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public void removeExpiredEvent(long id) throws ApplicationException {

		ExpiredEventEntity event = entityManager.find(ExpiredEventEntity.class, id);
		
		if (event == null) {
			throw new ApplicationException (ErrorType.DATA_NOT_FOUND, "The event you're trying to remove does not exist");
		}

		entityManager.remove(event);
	}

// -------------------------------------Getters----------------------------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public ExpiredEventEntity getExpiredEvent(long id) throws ApplicationException {

		ExpiredEventEntity event = entityManager.find(ExpiredEventEntity.class, id);

		return event;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<ExpiredEventEntity> getUserExpiredEvents(long userId) throws ApplicationException {
		
		Query query = entityManager.createQuery("SELECT event FROM ExpiredEventEntity event JOIN event.eventMembers as members WITH members.id=:userId")
				.setParameter("userId", userId);

		@SuppressWarnings("unchecked")
		List<ExpiredEventEntity> userEvents = query.getResultList();
		
		return userEvents;
	}
	
}
