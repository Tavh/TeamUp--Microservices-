package com.teamup.project.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="team_applications")
public class TeamApplicationEntity {

	@GeneratedValue
	@Id
	private long id;

	@ManyToOne (fetch=FetchType.EAGER)
	private UserEntity sender;

	@ManyToOne (fetch=FetchType.EAGER)
	private TeamEntity team;

	@Column (name="message", nullable=true)
	private String message;
	
	@Column (name="start_date", nullable=false)
	private String startDate;

	public TeamApplicationEntity() {
	}

	public TeamApplicationEntity(UserEntity sender, String message, String startDate) {
		this.sender = sender;
		this.message = message;
		this.startDate = startDate;
	}

	public TeamApplicationEntity(long id, UserEntity sender, String message, String startDate) {
		this.id = id;
		this.sender = sender;
		this.message = message;
		this.startDate = startDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getSender() {
		return sender;
	}

	public void setSender(UserEntity sender) {
		this.sender = sender;
	}

	public TeamEntity getTeam() {
		return team;
	}

	public void setTeam(TeamEntity team) {
		this.team = team;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "InvitationEntity [id=" + id + ", sender=" + sender + ", team=" + team + ", message=" + message
				+ ", startDate=" + startDate + "]";
	}
	
}
