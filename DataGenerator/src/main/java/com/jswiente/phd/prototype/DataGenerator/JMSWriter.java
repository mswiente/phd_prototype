package com.jswiente.phd.prototype.DataGenerator;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import com.jswiente.phd.prototype.domain.RawUsageEvent;

@Service
public class JMSWriter implements Writer<RawUsageEvent> {
	
	private class XMLMessageConverter implements MessageConverter {

		private JAXBContext context;
		private Marshaller marshaller;
		private Unmarshaller unmarshaller;

		public XMLMessageConverter() throws JAXBException {

			this.context = JAXBContext
					.newInstance("com.jswiente.phd.prototype.domain");
			this.marshaller = context.createMarshaller();
			this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			this.unmarshaller = context.createUnmarshaller();
		}

		public Object fromMessage(Message message) throws JMSException,
				MessageConversionException {
			try {
				StringReader sr = new StringReader(
						((TextMessage) message).getText());
				return unmarshaller.unmarshal(sr);
			} catch (JAXBException e) {
				throw new MessageConversionException(
						"Error converting message to object", e);
			}
		}

		public Message toMessage(Object obj, Session session)
				throws JMSException, MessageConversionException {
			try {
				StringWriter sw = new StringWriter();
				marshaller.marshal(obj, sw);
				return session.createTextMessage(sw.toString());

			} catch (JAXBException e) {
				throw new MessageConversionException(
						"Error converting object to message", e);
			}
		}

	}

	@Autowired
	protected JmsTemplate jmsTemplate;

	public void writeRecord(RawUsageEvent usageEvent) throws IOException {
		sendMessage(usageEvent);
	}

	private void sendMessage(final RawUsageEvent usageEvent) {
		this.jmsTemplate.convertAndSend(usageEvent, new MessagePostProcessor() {
			public Message postProcessMessage(Message message) throws JMSException {
				message.setLongProperty("recordId", usageEvent.getRecordId());
				return message;
			}
		});
	}

	public void open() {
		// intentionally left empty
	}

	public void close() throws IOException {
		// intentionally left empty
	}

	@PostConstruct
	public void postConstruct() throws JAXBException {
		this.jmsTemplate.setMessageConverter(new XMLMessageConverter());
	}

	public void setConfig(Configuration config) {
		// intentionally left empty
	}
}