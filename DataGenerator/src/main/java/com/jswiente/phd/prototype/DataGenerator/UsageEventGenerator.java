package com.jswiente.phd.prototype.DataGenerator;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jswiente.phd.prototype.domain.EventType;
import com.jswiente.phd.prototype.domain.Eventsource;
import com.jswiente.phd.prototype.domain.RawUsageEvent;
import com.jswiente.phd.prototype.persistence.EventsourceDAO;
import com.jswiente.phd.prototype.utils.DataUtils;

@Component
public class UsageEventGenerator implements Generator<RawUsageEvent> {

	@Autowired
	private EventsourceDAO eventSourceDAO;
	private List<Eventsource> eventSources;
	
	public RawUsageEvent generate(Long id) {
		
		RawUsageEvent usageEvent = new RawUsageEvent();
		usageEvent.setRecordId(id);
		usageEvent.setSequenceNum(1L);
		usageEvent.setFlatEvent(DataUtils.getRandomBoolean());
		
		usageEvent.setEventSource(DataUtils.getRandomElement(eventSources).getEventSource());
		
		//TODO randomize
		usageEvent.setCalledParty("+4901703097135");
		//TODO randomize
		usageEvent.setCallingParty("+4901703097135");
		
		DateTime endDate = new DateTime();
		int duration = DataUtils.getRandomInteger(3600);
		DateTime startDate = endDate.minusSeconds(duration);
		
		usageEvent.setStartDate(startDate.toDate());
		usageEvent.setEndDate(endDate.toDate());
		usageEvent.setEventType(EventType.parse(DataUtils.getRandomInteger(2)));
		
		return usageEvent;
	}
	
	public void init() {
		eventSources = eventSourceDAO.getAllEventSources();
	}
}
