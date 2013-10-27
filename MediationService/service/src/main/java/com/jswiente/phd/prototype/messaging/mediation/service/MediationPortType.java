package com.jswiente.phd.prototype.messaging.mediation.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.jswiente.phd.prototype.domain.RawUsageEvent;
import com.jswiente.phd.prototype.domain.RawUsageEvents;
import com.jswiente.phd.prototype.domain.SimpleCDR;
import com.jswiente.phd.prototype.domain.SimpleCDRs;

@WebService
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public interface MediationPortType {
	@WebMethod(operationName="processEvents")
	@WebResult(name="callDetailRecords")
	public SimpleCDRs processEvents(@WebParam(name="rawUsageEvents") RawUsageEvents rawUsageEvents) throws Exception;
	
	@WebMethod(operationName="processEvent")
	@WebResult(name="callDetailRecord")
	public SimpleCDR processEvent(@WebParam(name="rawUsageEvent") RawUsageEvent rawUsageEvent) throws Exception;
}

