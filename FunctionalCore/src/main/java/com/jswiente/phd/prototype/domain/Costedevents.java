package com.jswiente.phd.prototype.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlRootElement(namespace = "com.jswiente.phd.prototype.domain")
public class Costedevents {
	private static final long serialVersionUID = 825703031457696443L;
	
	private List<Costedevent> costedEvents;

	@XmlElement(name = "costedEvent")
	public List<Costedevent> getCostedEvents() {
		return costedEvents;
	}

	public void setCostedEvents(List<Costedevent> costedEvents) {
		this.costedEvents = costedEvents;
	}

}
