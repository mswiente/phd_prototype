package com.jswiente.phd.prototype.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlRootElement(namespace = "com.jswiente.phd.prototype.domain")
public class RawUsageEvents {
	private static final long serialVersionUID = 8133629746774288399L;
	
	protected List<RawUsageEvent> rawUsageEvents;

	@XmlElement(name = "rawUsageEvent")
	public List<RawUsageEvent> getUsageEvents() {
		return rawUsageEvents;
	}

	public void setUsageEvents(List<RawUsageEvent> rawUsageEvents) {
		this.rawUsageEvents = rawUsageEvents;
	}
}
