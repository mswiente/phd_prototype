package com.jswiente.phd.prototype.messaging.mediation.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.jswiente.phd.prototype.core.MediationProcessor;
import com.jswiente.phd.prototype.domain.RawUsageEvent;
import com.jswiente.phd.prototype.domain.RawUsageEvents;
import com.jswiente.phd.prototype.domain.SimpleCDR;
import com.jswiente.phd.prototype.domain.SimpleCDRs;

@WebService(targetNamespace = "http://www.jswiente.com/contract/mediation",
            endpointInterface = "com.jswiente.phd.prototype.messaging.mediation.service.MediationPortType",
            serviceName = "MediationService",
            portName = "MediationPort")
public class MediationPortTypeImpl implements MediationPortType {
	
	private MediationProcessor processor;

    public SimpleCDRs processEvents(RawUsageEvents usageEvents) throws Exception {
    	SimpleCDRs result = new SimpleCDRs();
    	List<SimpleCDR> simpleCDRList = new ArrayList<SimpleCDR>();
    	result.setSimpleCDRs(simpleCDRList);
    	
        for (RawUsageEvent usageEvent : usageEvents.getUsageEvents()) {
        	simpleCDRList.add(processEvent(usageEvent));
        }
    	
        return result;
    }
    
    public SimpleCDR processEvent(RawUsageEvent usageEvent) throws Exception {
    	return processor.process(usageEvent);
    }

	public void setProcessor(MediationProcessor processor) {
		this.processor = processor;
	}

}