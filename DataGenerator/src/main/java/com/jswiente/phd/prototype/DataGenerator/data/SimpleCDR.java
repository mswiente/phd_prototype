package com.jswiente.phd.prototype.DataGenerator.data;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.jswiente.phd.prototype.DataGenerator.util.DataUtils;

public class SimpleCDR implements Record {
	
	public Long recordNo;
	public long sequenceNo;
	public long customerNo;
	public String callingParty;
	public String calledParty;
	public Date startDate;
	public Date endDate;
	public String callType;
	
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
}
