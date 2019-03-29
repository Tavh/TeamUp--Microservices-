package com.teamup.project.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.teamup.project.entities.UserEntity;
import com.teamup.project.exceptions.ApplicationException;

@Repository
public class UserRepos {

	@PersistenceContext
	private EntityManager entityManager;

	// ---------------------------------------Create a new user-----------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public long createUser(UserEntity user){

		entityManager.persist(user);

		return user.getId();
	}

	// ------------------------------remove a user------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public void removeUser(long userId) throws ApplicationException {

		UserEntity user = entityManager.find(UserEntity.class, userId);

		entityManager.remove(user);
	}

	// -------------------------------------Getters----------------------------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public UserEntity getUser(long userId) throws ApplicationException {

		UserEntity user = entityManager.find(UserEntity.class, userId);

		return user;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public UserEntity getUser(String userEmail) throws ApplicationException {

		Query query = entityManager.createQuery("FROM UserEntity user WHERE user.email=:userEmail").setParameter("userEmail", userEmail);

		UserEntity user;

		List results = query.getResultList();

		if(results.isEmpty()) {
			user = null;
		} else {
			user = (UserEntity) query.getSingleResult();
		}

		return user;
	}


	// ------------------------------------etc-------------------------------------

	public boolean isUserInEvent(long userId, long eventId) {
		Query query = entityManager.createQuery("SELECT event FROM EventEntity event JOIN event.eventMembers as members WHERE members.id=:userId AND event.id=:eventId")
				.setParameter("userId", userId).setParameter("eventId", eventId);

		List results = query.getResultList();

		if(results.isEmpty()) {
			return false;
		} 

		return true;
	}


}
