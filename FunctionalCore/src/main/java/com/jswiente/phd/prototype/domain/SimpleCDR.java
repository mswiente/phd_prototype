package com.jswiente.phd.prototype.domain;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.jswiente.phd.prototype.utils.DataUtils;

public class SimpleCDR implements Record {
	
	private Long recordNo;
	private long sequenceNo;
	private long customerNo;
	private String callingParty;
	private String calledParty;
	private Date startDate;
	private Date endDate;
	private String callType;
	
	/* (non-Javadoc)
	 * @see com.jswiente.phd.DataGenerator.data.Record#toString()
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
			.append(String.format("%015d", recordNo))
			.append(String.format("%06d", sequenceNo))
			.append(String.format("%010d", customerNo))
			.append(StringUtils.leftPad(callingParty, 20, ' '))
			.append(StringUtils.leftPad(calledParty, 20, ' '))
			.append(DataUtils.formatDate(startDate))
			.append(DataUtils.formatDate(endDate))
			.append(callType);

		return stringBuilder.toString();
	}

	public Long getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(Long recordNo) {
		this.recordNo = recordNo;
	}

	public long getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public long getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(long customerNo) {
		this.customerNo = customerNo;
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

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}
}
