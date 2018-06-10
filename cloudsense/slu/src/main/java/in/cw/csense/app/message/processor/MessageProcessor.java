package in.cw.csense.app.message.processor;

import javax.websocket.Session;

import in.cw.csense.app.message.element.BillAckMessageElement;
import in.cw.csense.app.message.element.BillDetailMessageElement;
import in.cw.csense.app.message.element.HandShakeMessageElement;
import in.cw.csense.app.message.element.RequestMessageElement;

public interface MessageProcessor {
	public void process(HandShakeMessageElement message, Session session);
	public void process(BillDetailMessageElement message, Session session);
	public void process(RequestMessageElement message, Session session);
	public void process(BillAckMessageElement billAckMessageElement, Session session);
}
