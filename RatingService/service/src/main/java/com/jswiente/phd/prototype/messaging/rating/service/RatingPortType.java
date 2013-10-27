package com.jswiente.phd.prototype.messaging.rating.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.jswiente.phd.prototype.core.ProcessingException;
import com.jswiente.phd.prototype.domain.Costedevent;
import com.jswiente.phd.prototype.domain.Costedevents;
import com.jswiente.phd.prototype.domain.SimpleCDR;
import com.jswiente.phd.prototype.domain.SimpleCDRs;

@WebService
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public interface RatingPortType {
	@WebMethod(operationName="processCallDetails")
	@WebResult(name="costedEvents")
	public Costedevents processCallDetails(@WebParam(name="callDetailRecords") SimpleCDRs callDetailRecords) throws ProcessingException, Exception;
	
	@WebMethod(operationName="processCallDetail")
	@WebResult(name="costedEvent")
	public Costedevent processCallDetail(@WebParam(name="simpleCDR") SimpleCDR callDetailRecord) throws ProcessingException, Exception;
}

