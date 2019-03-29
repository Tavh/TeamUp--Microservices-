package com.teamup.project.repository;

import java.util.List;


import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.teamup.project.entities.TeamApplicationEntity;
import com.teamup.project.entities.TeamEntity;
import com.teamup.project.enums.ErrorType;
import com.teamup.project.exceptions.ApplicationException;

@Repository
public class TeamApplicationRepos {


	@PersistenceContext
	private EntityManager entityManager;

// ---------------------------------------Create a new company-----------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public long createTeamApplication(TeamApplicationEntity app){

		entityManager.persist(app);

		return app.getId();
	}

// ------------------------------remove a company------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public void removeTeamApplication(long id) throws ApplicationException {


		TeamApplicationEntity app = entityManager.find(TeamApplicationEntity.class, id);
		
		if (app == null) {
			throw new ApplicationException (ErrorType.DATA_NOT_FOUND, "The team application you're trying to remove does not exist");
		}
		
		entityManager.remove(app);
	}

// -------------------------------------Getters----------------------------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public TeamApplicationEntity getTeamApplication(long appId) throws ApplicationException {

		TeamApplicationEntity app = entityManager.find(TeamApplicationEntity.class, appId);

		return app;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TeamApplicationEntity> getTeamApplicationsInTeam(long teamId) throws ApplicationException {
		Query query = entityManager.createQuery("FROM TeamApplicationEntity WHERE team_id=:teamId").setParameter("teamId", teamId);
		
		@SuppressWarnings("unchecked")
		List<TeamApplicationEntity> allApps = query.getResultList();
		
		return allApps;
	}
	
	// ------------------------------------etc-------------------------------------

	public boolean isTeamApplicationPending (long senderId, long teamId) {
		Query query = entityManager.createQuery("FROM TeamApplicationEntity WHERE sender_id=:senderId AND team_id=:teamId")
				.setParameter("senderId", senderId).setParameter("teamId", teamId);
		
		List results = query.getResultList();

		if(results.isEmpty()) {
			return false;
		} 

		return true;
	}
	
}
