package com.jswiente.phd.prototype.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlRootElement(namespace = "com.jswiente.phd.prototype.domain")
public class SimpleCDRs {
	private static final long serialVersionUID = 9040913766879728741L;
	
	private List<SimpleCDR> simpleCDRs;

	@XmlElement(name = "simpleCDR")
	public List<SimpleCDR> getSimpleCDRs() {
		return simpleCDRs;
	}

	public void setSimpleCDRs(List<SimpleCDR> simpleCDRs) {
		this.simpleCDRs = simpleCDRs;
	}

}
