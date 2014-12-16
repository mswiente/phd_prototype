package com.jswiente.phd.prototype.batch.mediation;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.jswiente.phd.prototype.domain.EventType;
import com.jswiente.phd.prototype.domain.RawUsageEvent;
import com.jswiente.phd.prototype.utils.LogUtils;

public class RawUsageEventFieldSetMapper implements FieldSetMapper<RawUsageEvent> {

	public static final int RECORD_ID_COLUMN = 0;
	public static final int SEQUENCE_NUM_COLUMN = 1;
	public static final int EVENTSOURCE_COLUMN = 2;
	public static final int CALLING_PARTY_COLUMN = 3;
	public static final int CALLED_PARTY_COLUMN = 4;
	public static final int STARTDATE_COLUMN = 5;
	public static final int ENDDATE_COLUMN = 6;
	public static final int EVENTTYPE_COLUMN = 7;
	public static final int FLATEVENT_COLUMN = 8;
	
	public RawUsageEvent mapFieldSet(FieldSet fieldSet) throws BindException {
		
		long timestamp = System.currentTimeMillis();
		RawUsageEvent usageEvent = new RawUsageEvent();
		
		usageEvent.setRecordId(fieldSet.readLong(RECORD_ID_COLUMN));
		usageEvent.setSequenceNum(fieldSet.readLong(SEQUENCE_NUM_COLUMN));
		usageEvent.setEventSource(fieldSet.readString(EVENTSOURCE_COLUMN));
		usageEvent.setCallingParty(fieldSet.readString(CALLING_PARTY_COLUMN));
		usageEvent.setCalledParty(fieldSet.readString(CALLED_PARTY_COLUMN));
		usageEvent.setStartDate(fieldSet.readDate(STARTDATE_COLUMN, "yyyy-MM-dd'T'HH:mm:ssZ"));
		usageEvent.setEndDate(fieldSet.readDate(ENDDATE_COLUMN, "yyyy-MM-dd'T'HH:mm:ssZ"));
		usageEvent.setEventType(EventType.parse(fieldSet.readInt(EVENTTYPE_COLUMN)));
		usageEvent.setFlatEvent(fieldSet.readBoolean(FLATEVENT_COLUMN));
		
		LogUtils.logEvent(LogUtils.Event.PROC_START, usageEvent.getRecordId(), timestamp);
		
		return usageEvent;
	}

}
