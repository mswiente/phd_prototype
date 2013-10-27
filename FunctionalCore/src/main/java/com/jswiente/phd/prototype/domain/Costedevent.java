package com.jswiente.phd.prototype.domain;

// Generated 20.03.2012 22:03:25 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Costedevent generated by hbm2java
 */
@Entity
@Table(name = "costedevent", catalog = "billdb", uniqueConstraints = @UniqueConstraint(columnNames = {
		"RecordId", "SequenceNum" }))
@XmlType
@XmlRootElement(namespace = "com.jswiente.phd.prototype.domain")
public class Costedevent implements Record, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4486758916392474906L;
	private long costedEventId;
	private Account account;
	private Long recordId;
	private Long sequenceNum;
	private Date startDate;
	private Date endDate;
	private String callingParty;
	private String calledParty;
	private EventType eventType;
	private BigDecimal charge;
	private Integer billCycle;
	private Date processingDate;

	public Costedevent() {
	}

	public Costedevent(long costedEventId) {
		this.costedEventId = costedEventId;
	}

	public Costedevent(long costedEventId, Account account, Long recordId,
			Long sequenceNum, Date startDate, Date endDate,
			String callingParty, String calledParty, EventType eventType,
			BigDecimal charge, Integer billCycle, Date processingDate) {
		this.costedEventId = costedEventId;
		this.account = account;
		this.recordId = recordId;
		this.sequenceNum = sequenceNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.callingParty = callingParty;
		this.calledParty = calledParty;
		this.eventType = eventType;
		this.charge = charge;
		this.billCycle = billCycle;
		this.processingDate = processingDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CostedEventId", unique = true, nullable = false)
	public long getCostedEventId() {
		return this.costedEventId;
	}

	public void setCostedEventId(long costedEventId) {
		this.costedEventId = costedEventId;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "StartDate", length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndDate", length = 19)
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

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "EventType")
	public EventType getEventType() {
		return this.eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@Column(name = "Charge", precision = 10)
	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	@Column(name = "BillCycle")
	public Integer getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Integer billCycle) {
		this.billCycle = billCycle;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ProcessingDate", length = 10)
	public Date getProcessingDate() {
		return this.processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	@Override
	public long getId() {
		return recordId;
	}

}
