package com.teamup.project.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamup.project.entities.TeamEntity;
import com.teamup.project.entities.TeamMemberInsertionObject;
import com.teamup.project.enums.ErrorType;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.logic.TeamApplicationController;
import com.teamup.project.logic.TeamController;

@RestController
@RequestMapping(value = "/teams")
public class TeamApi {

	@Autowired
	private TeamController teamController;
	@Autowired
	private TeamApplicationController teamAppController;

	// -----------------------------Creates a new team--------------------------------------

	@PostMapping("/{teamLeaderId}")
	public void createTeam (@RequestBody TeamEntity team, @PathVariable long teamLeaderId) throws ApplicationException {

		teamController.createTeam(team, teamLeaderId);
	}
	
	// -----------------------------Updates a team--------------------------------------

	
	@PutMapping("/addmember")
	public void addTeamMember(@RequestBody TeamMemberInsertionObject teamAndMemberIdentities) throws ApplicationException {
		
		long teamId = teamAndMemberIdentities.getTeamId();
		long userId = teamAndMemberIdentities.getUserId();
		
		if (teamController.getTeam(teamId).isTeamPrivate()) {
			throw new ApplicationException (ErrorType.TEAM_IS_PRIVATE, ErrorType.TEAM_IS_PRIVATE.getInternalMessage());
		}
		
		teamController.addTeamMember(teamId, userId);
	}

	@PutMapping("/addmemeberthroughapp")
	public void addTeamMemberThroughApp(@RequestBody TeamMemberInsertionObject teamAndMemberIdentities, @RequestParam long appId) throws ApplicationException {
		// Removes the team application
		teamAppController.removeTeamApplication(appId);
		
		long teamId = teamAndMemberIdentities.getTeamId();
		long userId = teamAndMemberIdentities.getUserId();
		
		teamController.addTeamMember(teamId, userId);
	}

	// -----------------------------Getters--------------------------------------
	
	@GetMapping("/{id}")
	public TeamEntity getTeam (@PathVariable long id) throws ApplicationException {
		return teamController.getTeam(id);
	}

	@GetMapping("userteams/{userId}")
	public List<TeamEntity> getUserTeams (@PathVariable long userId) throws ApplicationException {
		
		return teamController.getUserTeams(userId);
	}
}
