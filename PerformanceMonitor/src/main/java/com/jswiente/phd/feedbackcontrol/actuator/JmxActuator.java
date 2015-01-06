package com.jswiente.phd.feedbackcontrol.actuator;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class JmxActuator<T> implements Actuator<T> {
	
	private String objectName;
	private String attributeName;
	
	private MBeanServerConnection mBeanServerConnection;
	
	private static final Logger logger = LoggerFactory
			.getLogger(MBeanServerConnection.class);

	public void setValue(T u) {
		try {
			ObjectName objectNameRequest = new ObjectName(objectName);
			
			Attribute attribute = new Attribute(attributeName, u);
			mBeanServerConnection.setAttribute(objectNameRequest, attribute);
			
		} catch (Exception e) {
			logger.error("Error setting value", e);
		}
	}
	
	@Required
	public void setmBeanServerConnection(MBeanServerConnection mBeanServerConnection) {
		this.mBeanServerConnection = mBeanServerConnection;
	}
	
	@Required
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

}
