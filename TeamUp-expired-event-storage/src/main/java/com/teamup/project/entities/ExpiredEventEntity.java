package com.teamup.project.entities;

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

	@GeneratedValue
	@Id
	private long id;

	@Column (name="event_title", nullable=false)
	private String eventTitle;

	@ManyToOne
	private UserEntity eventLeader;

	@ManyToMany (fetch=FetchType.EAGER)
	private Collection<UserEntity> eventMembers;

	@Column (name="start_date", nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date startDate;

	@Column (name="end_date", nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date endDate;

	@Column (name="event_text", nullable=true)
	private String eventText;

	@Column (name="private", nullable=false)
	private boolean isEventPrivate;

	public ExpiredEventEntity() {
	}

	public ExpiredEventEntity(String eventTitle, UserEntity eventLeader, Collection<UserEntity> eventMembers, Date startDate,
			Date endDate, String eventText, boolean isEventPrivate) {
		this.eventTitle = eventTitle;
		this.eventLeader = eventLeader;
		this.eventMembers = eventMembers;
		this.startDate = startDate;
		this.endDate = endDate;
		this.eventText = eventText;
		this.isEventPrivate = isEventPrivate;
	}

	public ExpiredEventEntity(long id, String eventTitle, UserEntity eventLeader, Collection<UserEntity> eventMembers,
			Date startDate, Date endDate, String eventText, boolean isEventPrivate) {
		this.id = id;
		this.eventTitle = eventTitle;
		this.eventLeader = eventLeader;
		this.eventMembers = eventMembers;
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

	public UserEntity getEventLeader() {
		return eventLeader;
	}

	public void setEventLeader(UserEntity eventLeader) {
		this.eventLeader = eventLeader;
	}

	public Collection<UserEntity> getEventMembers() {
		return eventMembers;
	}

	public void setEventMembers(Collection<UserEntity> eventMembers) {
		this.eventMembers = eventMembers;
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
		return "EventEntity [id=" + id + ", eventTitle=" + eventTitle + ", eventLeader=" + eventLeader + ", eventMembers="
				+ eventMembers + ", startDate=" + startDate + ", endDate=" + endDate + ", eventText=" + eventText
				+ ", isEventPrivate=" + isEventPrivate + "]";
	}
}
