package in.cw.csense.app.message.element;

import java.util.Collection;

import javax.websocket.Session;

import in.cw.csense.app.entity.Order;
import in.cw.csense.app.message.processor.MessageProcessor;

public class OrderDetailMessageElement implements MessageElement {


	private String requestID;
	
	private Collection<Order> orders;
	
	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public void accept(MessageProcessor processor, Session session) {
		processor.process(this, session);
	}
}
