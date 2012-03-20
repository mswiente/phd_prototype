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
 * Skippedevent generated by hbm2java
 */
@Entity
@Table(name = "skippedevent", catalog = "billdb", uniqueConstraints = @UniqueConstraint(columnNames = {
		"RecordId", "SequenceNum" }))
public class Skippedevent implements Record, java.io.Serializable {

	private long skippedEventId;
	private Account account;
	private Long recordId;
	private Long sequenceNum;
	private Date startDate;
	private Date endDate;
	private Date callingParty;
	private Date calledParty;
	private Integer eventType;
	private Date processingDate;
	private Integer skipReason;

	public Skippedevent() {
	}

	public Skippedevent(long skippedEventId) {
		this.skippedEventId = skippedEventId;
	}

	public Skippedevent(long skippedEventId, Account account, Long recordId,
			Long sequenceNum, Date startDate, Date endDate, Date callingParty,
			Date calledParty, Integer eventType, Date processingDate,
			Integer skipReason) {
		this.skippedEventId = skippedEventId;
		this.account = account;
		this.recordId = recordId;
		this.sequenceNum = sequenceNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.callingParty = callingParty;
		this.calledParty = calledParty;
		this.eventType = eventType;
		this.processingDate = processingDate;
		this.skipReason = skipReason;
	}

	@Id
	@Column(name = "SkippedEventId", unique = true, nullable = false)
	public long getSkippedEventId() {
		return this.skippedEventId;
	}

	public void setSkippedEventId(long skippedEventId) {
		this.skippedEventId = skippedEventId;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "CallingParty", length = 10)
	public Date getCallingParty() {
		return this.callingParty;
	}

	public void setCallingParty(Date callingParty) {
		this.callingParty = callingParty;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CalledParty", length = 10)
	public Date getCalledParty() {
		return this.calledParty;
	}

	public void setCalledParty(Date calledParty) {
		this.calledParty = calledParty;
	}

	@Column(name = "EventType")
	public Integer getEventType() {
		return this.eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ProcessingDate", length = 10)
	public Date getProcessingDate() {
		return this.processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	@Column(name = "SkipReason")
	public Integer getSkipReason() {
		return this.skipReason;
	}

	public void setSkipReason(Integer skipReason) {
		this.skipReason = skipReason;
	}

}
