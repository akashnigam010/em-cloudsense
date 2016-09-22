package in.cw.csense.app.message.element;

import javax.websocket.Session;

import in.cw.csense.app.message.processor.MessageProcessor;

public class RequestMessageElement implements MessageElement {

	private String requestID;
	
	private MessageElementType messageElementType;
	
	public void accept(MessageProcessor processor, Session session) {
		processor.process(this, session);

	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
		
	}

	public void setType(MessageElementType messageElementType) {
		this.messageElementType = messageElementType;
	}

	public MessageElementType getMessageElementType() {
		return messageElementType;
	}

	public void setMessageElementType(MessageElementType messageElementType) {
		this.messageElementType = messageElementType;
	}

	public String getRequestID() {
		return requestID;
	}
	
	
	

}
