package com.jswiente.phd.prototype.messaging.rating.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.jswiente.phd.prototype.core.ProcessingException;
import com.jswiente.phd.prototype.domain.Costedevents;
import com.jswiente.phd.prototype.domain.SimpleCDRs;
import com.jswiente.phd.prototype.messaging.rating.service.RatingPortType;

public class WSClient {
    private static final QName SERVICE_NAME
        = new QName("http://www.jswiente.com/contract/rating", "RatingService");
    private static final QName PORT_NAME
        = new QName("http://www.jswiente.com/contract/rating", "RatingPort");

    public static void main (String[] args) throws Exception {
        String endpointAddress = "http://localhost:8040/services/rating";
        Service service = Service.create(new URL(endpointAddress +"?wsdl"), SERVICE_NAME);
        RatingPortType port = service.getPort(RatingPortType.class);

        process(port);
    } 

    public static void process(RatingPortType port) throws Exception {
        Costedevents resp;
		try {
			resp = port.processCallDetails(new SimpleCDRs());
		} catch (ProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

