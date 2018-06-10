package in.cw.csense.app.message.element;

import javax.websocket.Session;

import in.cw.csense.app.message.processor.MessageProcessor;

public interface MessageElement {
	public void accept(MessageProcessor processor, Session session);
}
