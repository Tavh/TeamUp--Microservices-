package com.teamup.project.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table (name="teams")
public class TeamEntity {

	@GeneratedValue
	@Id
	private long id;

	@Column (name="team_title", nullable=false)
	private String teamTitle;

	@ManyToOne
	private UserEntity teamLeader;

	@ManyToMany (fetch=FetchType.EAGER)
	private Collection<UserEntity> teamMembers;

	@Column (name="start_date", nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date startDate;

	@Column (name="end_date", nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date endDate;

	@Column (name="team_text", nullable=true)
	private String teamText;

	@Column (name="private", nullable=false)
	private boolean isTeamPrivate;

	public TeamEntity() {
	}

	public TeamEntity(String teamTitle, UserEntity teamLeader, Collection<UserEntity> teamMembers, Date startDate,
			Date endDate, String teamText, boolean isTeamPrivate) {
		this.teamTitle = teamTitle;
		this.teamLeader = teamLeader;
		this.teamMembers = teamMembers;
		this.startDate = startDate;
		this.endDate = endDate;
		this.teamText = teamText;
		this.isTeamPrivate = isTeamPrivate;
	}

	public TeamEntity(long id, String teamTitle, UserEntity teamLeader, Collection<UserEntity> teamMembers,
			Date startDate, Date endDate, String teamText, boolean isTeamPrivate) {
		this.id = id;
		this.teamTitle = teamTitle;
		this.teamLeader = teamLeader;
		this.teamMembers = teamMembers;
		this.startDate = startDate;
		this.endDate = endDate;
		this.teamText = teamText;
		this.isTeamPrivate = isTeamPrivate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamTitle() {
		return teamTitle;
	}

	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}

	public UserEntity getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(UserEntity teamLeader) {
		this.teamLeader = teamLeader;
	}

	public Collection<UserEntity> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(Collection<UserEntity> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTeamText() {
		return teamText;
	}

	public void setTeamText(String teamText) {
		this.teamText = teamText;
	}

	public boolean isTeamPrivate() {
		return isTeamPrivate;
	}

	public void setTeamPrivate(boolean isTeamPrivate) {
		this.isTeamPrivate = isTeamPrivate;
	}

	@Override
	public String toString() {
		return "TeamEntity [id=" + id + ", teamTitle=" + teamTitle + ", teamLeader=" + teamLeader + ", teamMembers="
				+ teamMembers + ", startDate=" + startDate + ", endDate=" + endDate + ", teamText=" + teamText
				+ ", isTeamPrivate=" + isTeamPrivate + "]";
	}
}
