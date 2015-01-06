package com.jswiente.phd.prototype.domain;

// Generated 06.03.2012 20:55:50 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Eventsource generated by hbm2java
 */
@Entity
@Table(name = "eventsource", catalog = "billdb", uniqueConstraints = @UniqueConstraint(columnNames = {
		"EventSource", "EventType" }))
public class Eventsource implements Record, java.io.Serializable {

	private long eventSourceId;
	private Customerproduct customerproduct;
	private String eventSource;
	private EventType eventType;

	public Eventsource() {
	}

	public Eventsource(long eventSourceId, String eventSource, EventType eventType) {
		this.eventSourceId = eventSourceId;
		this.eventSource = eventSource;
		this.eventType = eventType;
	}

	public Eventsource(long eventSourceId, Customerproduct customerproduct,
			String eventSource, EventType eventType) {
		this.eventSourceId = eventSourceId;
		this.customerproduct = customerproduct;
		this.eventSource = eventSource;
		this.eventType = eventType;
	}

	@Id
	@Column(name = "EventSourceId", unique = true, nullable = false)
	public long getEventSourceId() {
		return this.eventSourceId;
	}

	public void setEventSourceId(long eventSourceId) {
		this.eventSourceId = eventSourceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CustomerProductId")
	public Customerproduct getCustomerproduct() {
		return this.customerproduct;
	}

	public void setCustomerproduct(Customerproduct customerproduct) {
		this.customerproduct = customerproduct;
	}

	@Column(name = "EventSource", nullable = false, length = 50)
	public String getEventSource() {
		return this.eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "EventType", nullable = false)
	public EventType getEventType() {
		//return EventType.parse(this.eventType);
		return eventType;
	}

	public void setEventType(EventType eventType) {
		//this.eventType = eventType.getValue();
		this.eventType = eventType;
	}

	public long getId() {
		return eventSourceId;
	}

}
