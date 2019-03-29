package com.teamup.project.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name="expired_events")
public class ExpiredEventEntity {

	@Id
	@Column (name="id", nullable=false)
	private long id;
	
	@Column (name="event_title", nullable=false)
	private String eventTitle;

	@Column (name="event_leader", nullable=false)
	private long eventLeaderId;

	@Column (name="event_member_identities", nullable=false)
	private long[] eventMemberIdentities;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	@Column (name="start_date", nullable=false)
	private Date startDate;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	@Column (name="end_date", nullable=false)
	private Date endDate;

	@Column (name="event_text", nullable=true)
	private String eventText;

	@Column (name="private", nullable=false)
	private boolean isEventPrivate;

	public ExpiredEventEntity() {
	}

	public ExpiredEventEntity(EventEntity event) {
		this.id = event.getId();
		this.eventTitle = event.getEventTitle();
		this.eventLeaderId = event.getEventLeader().getId();

		this.eventMemberIdentities = new long[event.getEventMembers().size()];
		int index = 0;
		for (UserEntity member : event.getEventMembers()) {
			this.eventMemberIdentities[index] = member.getId();
			index++;
		}

		this.startDate = event.getStartDate();
		this.endDate = event.getEndDate();
		this.eventText = event.getEventText();
		this.isEventPrivate = event.isEventPrivate();
	}

	public ExpiredEventEntity(String eventTitle, long eventLeaderId, long[] eventMembersIdentities, Date startDate,
			Date endDate, String eventText, boolean isEventPrivate) {
		this.eventTitle = eventTitle;
		this.eventLeaderId = eventLeaderId;
		this.eventMemberIdentities = eventMembersIdentities;
		this.startDate = startDate;
		this.endDate = endDate;
		this.eventText = eventText;
		this.isEventPrivate = isEventPrivate;
	}

	public ExpiredEventEntity(long id, String eventTitle, long eventLeaderId, long[] eventMembersIdentities,
			Date startDate, Date endDate, String eventText, boolean isEventPrivate) {
		this.id = id;
		this.eventTitle = eventTitle;
		this.eventLeaderId = eventLeaderId;
		this.eventMemberIdentities = eventMembersIdentities;
		this.startDate = startDate;
		this.endDate = endDate;
		this.eventText = eventText;
		this.isEventPrivate = isEventPrivate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public long getEventLeader() {
		return eventLeaderId;
	}

	public void setEventLeader(long eventLeaderId) {
		this.eventLeaderId = eventLeaderId;
	}

	public long[] getEventMemberIdentities() {
		return eventMemberIdentities;
	}

	public void setEventMemberIdentities(long[] eventMemberIdentities) {
		this.eventMemberIdentities = eventMemberIdentities;
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

	public String getEventText() {
		return eventText;
	}

	public void setEventText(String eventText) {
		this.eventText = eventText;
	}

	public boolean isEventPrivate() {
		return isEventPrivate;
	}

	public void setEventPrivate(boolean isEventPrivate) {
		this.isEventPrivate = isEventPrivate;
	}

	@Override
	public String toString() {
		return "ExpiredEventEntity [id=" + id + ", eventTitle=" + eventTitle + ", eventLeader=" + eventLeaderId
				+ ", eventMemberIdentities=" + Arrays.toString(eventMemberIdentities) + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", eventText=" + eventText + ", isEventPrivate=" + isEventPrivate + "]";
	}

}
