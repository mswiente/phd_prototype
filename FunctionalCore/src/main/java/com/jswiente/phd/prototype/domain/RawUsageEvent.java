package com.jswiente.phd.prototype.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

import com.jswiente.phd.prototype.utils.DataUtils;

@XmlType
@XmlRootElement(namespace = "com.jswiente.phd.prototype.domain")
public class RawUsageEvent implements Record {

	private static final long serialVersionUID = -3895616580085784771L;
	
	protected long recordId;
	protected long sequenceNum;
	protected String eventSource;
	protected String callingParty;
	protected String calledParty;
	protected Date startDate;
	protected Date endDate;
	protected EventType eventType;
	protected boolean flatEvent;
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
			.append(String.format("%015d", recordId))
			.append(String.format("%06d", sequenceNum))
			.append(StringUtils.leftPad(eventSource, 20, ' '))
			.append(StringUtils.leftPad(callingParty, 20, ' '))
			.append(StringUtils.leftPad(calledParty, 20, ' '))
			.append(DataUtils.formatDate(startDate))
			.append(DataUtils.formatDate(endDate))
			.append(eventType.getValue())
			.append(flatEvent ? "1":"0");

		return stringBuilder.toString();
	}
	
	public long getRecordId() {
		return recordId;
	}
	
	public void setRecordId(long recordNum) {
		this.recordId = recordNum;
	}
	
	public long getSequenceNum() {
		return sequenceNum;
	}
	
	public void setSequenceNum(long sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	public String getEventSource() {
		return this.eventSource;
	}
	
	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
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
	
	public EventType getEventType() {
		return eventType;
	}
	
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	public boolean isFlatEvent() {
		return flatEvent;
	}
	
	public void setFlatEvent(boolean flatEvent) {
		this.flatEvent = flatEvent;
	}
}
