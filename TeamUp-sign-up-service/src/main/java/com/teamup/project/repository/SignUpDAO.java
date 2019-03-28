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
public class SignUpDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation=Propagation.REQUIRED)
	public long createUser(UserEntity user){

		entityManager.persist(user);

		return user.getId();
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
}
