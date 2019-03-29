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
import com.teamup.project.entities.UserEntity;
import com.teamup.project.enums.ErrorType;
import com.teamup.project.exceptions.ApplicationException;

@Repository
public class TeamRepos {

	@PersistenceContext
	private EntityManager entityManager;

// ---------------------------------------Create a new team-----------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public long createTeam(TeamEntity team){

		entityManager.persist(team);

		return team.getId();
	}

// ------------------------------update a team------------------
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateTeam(TeamEntity team){

		entityManager.merge(team);
	}

// ------------------------------remove a team------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public void removeTeam(long id) throws ApplicationException {

		TeamEntity team = entityManager.find(TeamEntity.class, id);
		
		if (team == null) {
			throw new ApplicationException (ErrorType.DATA_NOT_FOUND, "The team you're trying to remove does not exist");
		}

		entityManager.remove(team);
	}

// -------------------------------------Getters----------------------------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public TeamEntity getTeam(long teamId) throws ApplicationException {

		TeamEntity team = entityManager.find(TeamEntity.class, teamId);

		return team;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<TeamEntity> getUserTeams(long userId) throws ApplicationException {
		
		Query query = entityManager.createQuery("SELECT team FROM TeamEntity team JOIN team.teamMembers as members WITH members.id=:userId")
				.setParameter("userId", userId);

		@SuppressWarnings("unchecked")
		List<TeamEntity> userTeams = query.getResultList();
		
		return userTeams;
	}
	
}
