package com.teamup.project.entities;

public class EventMemberInsertionObject {

	private long userId;
	private long eventId;
	
	public EventMemberInsertionObject() {
	}
	
	public EventMemberInsertionObject(long userId, long eventId) {
		this.userId = userId;
		this.eventId = eventId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "EventMemberAdditionObject [userId=" + userId + ", eventId=" + eventId + "]";
	}
}
