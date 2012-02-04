package com.jswiente.phd.prototype.domain;

import java.util.Date;

public class Event {
	
	private long id;
	private Date startTime;
	private Date endTime;
	private long duration;
	private String callingParty;
	private String calledParty;
	private EventType eventType;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public String getCallingParty() {
		return callingParty;
	}
	
	public void setCallingParty(String callingParty) {
		this.callingParty = callingParty;
	}
	
	public String getCalledParty() {
		return calledParty;
	}
	
	public void setCalledParty(String calledParty) {
		this.calledParty = calledParty;
	}
	
	public EventType getEventType() {
		return eventType;
	}
	
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
}
