package com.jswiente.phd.prototype.messaging.mediation.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.jswiente.phd.prototype.domain.RawUsageEvents;
import com.jswiente.phd.prototype.domain.SimpleCDRs;
import com.jswiente.phd.prototype.messaging.mediation.service.MediationPortType;

public class WSClient {
    private static final QName SERVICE_NAME
        = new QName("http://www.jswiente.com/contract/mediation", "MediationService");
    private static final QName PORT_NAME
        = new QName("http://www.jswiente.com/contract/mediation", "MediationPort");

    public static void main (String[] args) throws Exception {
        String endpointAddress = "http://localhost:8040/services/mediation";
        Service service = Service.create(new URL(endpointAddress +"?wsdl"), SERVICE_NAME);
        MediationPortType port = service.getPort(MediationPortType.class);

        process(port);
    } 

    public static void process(MediationPortType port) throws Exception {
        SimpleCDRs resp = port.processEvents(new RawUsageEvents());
    }
}

