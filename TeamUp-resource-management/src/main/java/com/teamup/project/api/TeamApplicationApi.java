package com.teamup.project.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamup.project.entities.TeamApplicationEntity;
import com.teamup.project.exceptions.ApplicationException;
import com.teamup.project.logic.TeamApplicationController;
import com.teamup.project.logic.TeamController;
import com.teamup.project.logic.UserController;


@RestController
@RequestMapping(value = "/applications")
public class TeamApplicationApi {

	@Autowired
	private TeamApplicationController teamApplicationController;
	
	// -----------------------------Creates a new invitation--------------------------------------

	@PostMapping
	public void createTeamApplication (@RequestParam long senderId, @RequestParam long teamId, @RequestBody TeamApplicationEntity inv) throws ApplicationException {
		teamApplicationController.createTeamApplication(senderId, teamId, inv);
	}

	// -----------------------------Getters--------------------------------------
	
	@GetMapping("/{id}")
	public TeamApplicationEntity getTeamApplication (@PathVariable long id) throws ApplicationException {
		return teamApplicationController.getTeamApplication(id);
	}
	
	@GetMapping("/appsinteam")
	public List <TeamApplicationEntity> getTeamApplicationsInTeam (@RequestParam long teamId) throws ApplicationException {
			
		return teamApplicationController.getTeamApplicationsInTeam(teamId);
	}
	
}
