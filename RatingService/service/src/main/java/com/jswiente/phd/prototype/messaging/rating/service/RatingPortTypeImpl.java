package com.jswiente.phd.prototype.messaging.rating.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jswiente.phd.prototype.core.ProcessingException;
import com.jswiente.phd.prototype.core.RatingProcessor;
import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.domain.Costedevents;
import com.jswiente.phd.prototype.domain.SimpleCDR;
import com.jswiente.phd.prototype.domain.SimpleCDRs;

@Service
@WebService(targetNamespace = "http://www.jswiente.com/contract/rating",
            endpointInterface = "com.jswiente.phd.prototype.messaging.rating.service.RatingPortType",
            serviceName = "RatingService",
            portName = "RatingPort")
public class RatingPortTypeImpl implements RatingPortType {
	
	@Autowired
	private RatingProcessor processor;

    public Costedevents processCallDetails(SimpleCDRs callDetailRecords) throws Exception {
        Costedevents result = new Costedevents();
    	List<Costedevent> costedEventList = new ArrayList<Costedevent>();
    	result.setCostedEvents(costedEventList);
    	
        for (SimpleCDR callDetailRecord : callDetailRecords.getSimpleCDRs()) {
        	costedEventList.add(processCallDetail(callDetailRecord));
        }
        
        return result;
    }
    
    public Costedevent processCallDetail(SimpleCDR callDetailRecord) throws Exception {
    	return processor.process(callDetailRecord);
    }

	public void setProcessor(RatingProcessor processor) {
		this.processor = processor;
	}

}
