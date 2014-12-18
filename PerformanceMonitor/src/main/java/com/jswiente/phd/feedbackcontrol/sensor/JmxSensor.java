package com.jswiente.phd.feedbackcontrol.sensor;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class JmxSensor {
	
	private String objectName;
	
	private MBeanServerConnection mBeanServerConnection;

	private static final Logger logger = LoggerFactory
			.getLogger(MBeanServerConnection.class);
	
	public Long getLongAttribute(String attribute) {
		try {
			ObjectName objectNameRequest = new ObjectName(objectName);
			
			return (Long) mBeanServerConnection.getAttribute(objectNameRequest, attribute);
			
		} catch (Exception e) {
			logger.error("Error reading long value", e);
		}
		
		return null;
	}
	
	public Integer getIntegerAttribute(String attribute) {
		try {
			ObjectName objectNameRequest = new ObjectName(objectName);
			
			return (Integer) mBeanServerConnection.getAttribute(objectNameRequest, attribute);
			
		} catch (Exception e) {
			logger.error("Error reading integer value", e);
		}
		
		return null;
	}
	
	@Required
	public void setmBeanServerConnection(MBeanServerConnection mBeanServerConnection) {
		this.mBeanServerConnection = mBeanServerConnection;
	}
	
	@Required
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
}
