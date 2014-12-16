package com.jswiente.phd.prototype.batch.rating;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.jswiente.phd.prototype.domain.EventType;
import com.jswiente.phd.prototype.domain.SimpleCDR;

public class SimpleCdrFieldSetMapper implements FieldSetMapper<SimpleCDR> {

	public SimpleCDR mapFieldSet(FieldSet fieldSet) throws BindException {
		SimpleCDR cdr = new SimpleCDR();
		
		cdr.setRecordId(fieldSet.readLong("recordId"));
		cdr.setSequenceNum(fieldSet.readLong("sequenceNum"));
		cdr.setEventSource(fieldSet.readString("eventSource"));
		cdr.setCallingParty(fieldSet.readString("callingParty"));
		cdr.setCalledParty(fieldSet.readString("calledParty"));
		cdr.setStartDate(fieldSet.readDate("startDate", "yyyy-MM-dd'T'HH:mm:ssZ"));
		cdr.setEndDate(fieldSet.readDate("endDate", "yyyy-MM-dd'T'HH:mm:ssZ"));
		cdr.setEventType(EventType.parse(fieldSet.readInt("eventType")));
		cdr.setFlatEvent(fieldSet.readInt("flatEvent") == 1);
		
		return cdr;
	}

}
