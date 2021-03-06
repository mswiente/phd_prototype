package com.jswiente.phd.prototype.domain;

// Generated 20.03.2012 22:03:25 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Calldetail generated by hbm2java
 */
@Entity
@Table(name = "calldetail", catalog = "billdb", uniqueConstraints = @UniqueConstraint(columnNames = {
		"RecordId", "SequenceNum" }))
public class Calldetail implements Record, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 555651154778204513L;
	private long callDetailId;
	private Account account;
	private Long recordId;
	private Long sequenceNum;
	private Date startDate;
	private Date endDate;
	private String callingParty;
	private String calledParty;
	private Integer eventType;
	private Boolean flatEvent;
	private Date processingDate;

	public Calldetail() {
	}

	public Calldetail(long callDetailId) {
		this.callDetailId = callDetailId;
	}

	public Calldetail(long callDetailId, Account account, Long recordId,
			Long sequenceNum, Date startDate, Date endDate,
			String callingParty, String calledParty, Integer eventType,
			Boolean flatEvent, Date processingDate) {
		this.callDetailId = callDetailId;
		this.account = account;
		this.recordId = recordId;
		this.sequenceNum = sequenceNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.callingParty = callingParty;
		this.calledParty = calledParty;
		this.eventType = eventType;
		this.flatEvent = flatEvent;
		this.processingDate = processingDate;
	}

	@Id
	@Column(name = "CallDetailId", unique = true, nullable = false)
	public long getCallDetailId() {
		return this.callDetailId;
	}

	public void setCallDetailId(long callDetailId) {
		this.callDetailId = callDetailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AccountId")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "RecordId")
	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	@Column(name = "SequenceNum")
	public Long getSequenceNum() {
		return this.sequenceNum;
	}

	public void setSequenceNum(Long sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "StartDate", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EndDate", length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "CallingParty", length = 50)
	public String getCallingParty() {
		return this.callingParty;
	}

	public void setCallingParty(String callingParty) {
		this.callingParty = callingParty;
	}

	@Column(name = "CalledParty", length = 50)
	public String getCalledParty() {
		return this.calledParty;
	}

	public void setCalledParty(String calledParty) {
		this.calledParty = calledParty;
	}

	@Column(name = "EventType")
	public Integer getEventType() {
		return this.eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	@Column(name = "FlatEvent")
	public Boolean getFlatEvent() {
		return this.flatEvent;
	}

	public void setFlatEvent(Boolean flatEvent) {
		this.flatEvent = flatEvent;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ProcessingDate", length = 10)
	public Date getProcessingDate() {
		return this.processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	public long getId() {
		return recordId;
	}

}
