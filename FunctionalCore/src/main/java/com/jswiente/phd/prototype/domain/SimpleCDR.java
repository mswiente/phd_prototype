package com.jswiente.phd.prototype.domain;

import com.jswiente.phd.prototype.utils.DataUtils;

public class SimpleCDR extends RawUsageEvent {
	
	private boolean skip;
	private SkipReason skipReason;
	
	public SimpleCDR() {
		
	}
	
	public SimpleCDR(RawUsageEvent usageEvent) {
		this.recordId = usageEvent.getRecordId();
		this.sequenceNum = usageEvent.getSequenceNum();
		this.eventSource = usageEvent.getEventSource();
		this.callingParty = usageEvent.getCallingParty();
		this.calledParty = usageEvent.getCalledParty();
		this.startDate = usageEvent.getStartDate();
		this.endDate = usageEvent.getEndDate();
		this.eventType = usageEvent.getEventType();
		this.flatEvent = usageEvent.isFlatEvent();
	}
	
	/* (non-Javadoc)
	 * @see com.jswiente.phd.DataGenerator.data.Record#toString()
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
			.append(String.format("%015d", recordId)).append(";")
			.append(String.format("%06d", sequenceNum)).append(";")
			.append(eventSource).append(";")
			.append(callingParty).append(";")
			.append(calledParty).append(";")
			.append(DataUtils.formatDate(startDate)).append(";")
			.append(DataUtils.formatDate(endDate)).append(";")
			.append(eventType);

		return stringBuilder.toString();
	}

	public boolean isSkip() {
		return skip;
	}
	
	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	public SkipReason getSkipReason() {
		return skipReason;
	}

	public void setSkipReason(SkipReason skipReason) {
		this.skipReason = skipReason;
	}
}
