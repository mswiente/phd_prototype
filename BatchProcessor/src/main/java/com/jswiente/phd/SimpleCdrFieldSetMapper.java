package com.jswiente.phd;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.jswiente.phd.prototype.domain.SimpleCDR;

public class SimpleCdrFieldSetMapper implements FieldSetMapper<SimpleCDR> {

	public static final int RECORD_NO_COLUMN = 0;
	public static final int SEQUENCE_NO_COLUMN = 1;
	public static final int CUSTERMER_NO_COLUMN = 2;
	public static final int CALLING_PARTY_COLUMN = 3;
	public static final int CALLED_PARTY_COLUMN = 4;
	public static final int STARTDATE_COLUMN = 5;
	public static final int ENDDATE_COLUMN = 6;
	public static final int CALLTYPE_COLUMN = 7;
	
	@Override
	public SimpleCDR mapFieldSet(FieldSet fieldSet) throws BindException {
		
		SimpleCDR cdr = new SimpleCDR();
		cdr.setRecordNo(fieldSet.readLong(RECORD_NO_COLUMN));
		cdr.setSequenceNo(fieldSet.readLong(SEQUENCE_NO_COLUMN));
		cdr.setCustomerNo(fieldSet.readLong(CUSTERMER_NO_COLUMN));
		cdr.setCallingParty(fieldSet.readString(CALLING_PARTY_COLUMN));
		cdr.setCalledParty(fieldSet.readString(CALLED_PARTY_COLUMN));
		cdr.setStartDate(fieldSet.readDate(STARTDATE_COLUMN));
		cdr.setEndDate(fieldSet.readDate(ENDDATE_COLUMN));
		cdr.setCallType(fieldSet.readString(CALLTYPE_COLUMN));
		
		return cdr;
	}

}
