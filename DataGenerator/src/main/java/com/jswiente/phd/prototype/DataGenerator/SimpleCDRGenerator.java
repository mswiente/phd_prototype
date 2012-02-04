package com.jswiente.phd.prototype.DataGenerator;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

import com.jswiente.phd.prototype.DataGenerator.data.Record;
import com.jswiente.phd.prototype.DataGenerator.data.SimpleCDR;
import com.jswiente.phd.prototype.DataGenerator.util.DataUtils;

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
		cdr.recordNo = id;
		cdr.customerNo = DataUtils.getRandomLong(9999999999L);
		cdr.callingParty = "+491703097135";
		cdr.calledParty = "+4061747582";
		cdr.callType = "PHONE";
		cdr.startDate = DataUtils.getRandomDate(start.toDate(), end.toDate());
		cdr.endDate = DataUtils.getRandomDate(start.toDate(), end.toDate());
		cdr.sequenceNo = 1L;
		
		return cdr;
	}
}
