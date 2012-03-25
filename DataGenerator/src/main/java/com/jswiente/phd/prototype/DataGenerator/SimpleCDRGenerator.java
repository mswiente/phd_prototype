package com.jswiente.phd.prototype.DataGenerator;

import org.joda.time.DateMidnight;

import com.jswiente.phd.prototype.domain.EventType;
import com.jswiente.phd.prototype.domain.Record;
import com.jswiente.phd.prototype.domain.SimpleCDR;
import com.jswiente.phd.prototype.utils.DataUtils;

public class SimpleCDRGenerator implements Generator {
	
	private DateMidnight start;
	private DateMidnight end;
	
	public SimpleCDRGenerator() {
		start = DataUtils.getFirstDayOfMonth();
		end = start.plusMonths(1);
	}
	
	/* (non-Javadoc)
	 * @see com.jswiente.phd.DataGenerator.Generator#generate(java.math.BigInteger)
	 */
	@Override
	public Record generate(Long id) {
		
		SimpleCDR cdr = new SimpleCDR();
		cdr.setRecordId(id);
		cdr.setCallingParty("+491703097135");
		cdr.setCalledParty("+4061747582");
		cdr.setEventType(EventType.VOICE);
		cdr.setStartDate(DataUtils.getRandomDate(start.toDate(), end.toDate()));
		cdr.setEndDate(DataUtils.getRandomDate(start.toDate(), end.toDate()));
		cdr.setSequenceNum(1L);
		
		return cdr;
	}
	
	@Override
	public void init()
	{
		
	}
}
