package com.teamup.project.logic;

import java.time.LocalDate;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.teamup.project.entities.TeamApplicationEntity;
import com.teamup.project.entities.TeamEntity;
import com.teamup.project.entities.UserEntity;
import com.teamup.project.enums.ErrorType;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.repository.TeamApplicationRepos;
import com.teamup.project.repository.TeamRepos;
import com.teamup.project.repository.UserRepos;

@Controller
public class TeamApplicationController {
	
	@Autowired
	private TeamApplicationRepos teamAppRepos;
	@Autowired
	private UserRepos userRepos;
	@Autowired
	private TeamRepos teamRepos;

	// ---------------------------------Creates an invitation ------------------------------------
	
	public long createTeamApplication (long senderId, long teamId, TeamApplicationEntity app) throws ApplicationException {

		UserEntity sender = userRepos.getUser(senderId);
		TeamEntity team = teamRepos.getTeam(teamId);
		
		app.setSender(sender);
		app.setTeam(team);
		
		if(app.getSender() == null) {
			throw new ApplicationException (ErrorType.WORNG_INPUT, "The team application should have a valid sender");
		}
		
		if(app.getTeam() == null) {
			throw new ApplicationException (ErrorType.WORNG_INPUT, "The team application should be destined to a valid team");
		}
		
		boolean isUserInTeam = userRepos.isUserInTeam(senderId, teamId);
		
		if (isUserInTeam) {
			throw new ApplicationException (ErrorType.WORNG_INPUT, "User " + sender.getNickname() + " is already in team " + team.getTeamTitle());
		}
		
		boolean isApplicationPending = teamAppRepos.isTeamApplicationPending(senderId, teamId);
		
		if (isApplicationPending) {
			throw new ApplicationException (ErrorType.WORNG_INPUT, "User " + sender.getNickname() + " has already requested to join team " + team.getTeamTitle());
		}
		
		Date appCreationDate = new Date(app.getStartDate());
		
		if (appCreationDate.getDate() != LocalDate.now().getDayOfMonth()) {
			throw new ApplicationException (ErrorType.WORNG_INPUT, "Team application creation date should only be today");
		}
		
		long autoGeneratedId = teamAppRepos.createTeamApplication(app);

		return autoGeneratedId;
	}

	// ---------------------------------Deletes a team application------------------------------------

	public void removeTeamApplication(long id) throws ApplicationException {
		
		if (teamAppRepos.getTeamApplication(id) == null) {
			throw new ApplicationException (ErrorType.DATA_NOT_FOUND, "The team application with the id " + id + " could not be found");
		}
		
		teamAppRepos.removeTeamApplication(id);
	}

	// ---------------------------------Getters------------------------------------
	
	public TeamApplicationEntity getTeamApplication(long id) throws ApplicationException {
		return teamAppRepos.getTeamApplication(id);
	}
	
	// Gets all team applications in a team 
	public List<TeamApplicationEntity> getTeamApplicationsInTeam(long teamId) throws ApplicationException {
		return teamAppRepos.getTeamApplicationsInTeam(teamId);
	}
	
}
