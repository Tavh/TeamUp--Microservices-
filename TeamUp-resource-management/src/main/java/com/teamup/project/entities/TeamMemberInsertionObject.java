package com.teamup.project.entities;

public class TeamMemberInsertionObject {

	private long userId;
	private long teamId;
	
	public TeamMemberInsertionObject() {
	}
	
	public TeamMemberInsertionObject(long userId, long teamId) {
		this.userId = userId;
		this.teamId = teamId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	@Override
	public String toString() {
		return "TeamMemberAdditionObject [userId=" + userId + ", teamId=" + teamId + "]";
	}
}
