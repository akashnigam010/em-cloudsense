package in.cw.csense.app.message.element;

import java.util.List;

import javax.websocket.Session;

import in.cw.csense.app.message.processor.MessageProcessor;

public class BillAckMessageElement implements MessageElement {
	private List<ProcessedBill> processBills;

	public List<ProcessedBill> getProcessBills() {
		return processBills;
	}

	public void setProcessBills(List<ProcessedBill> processBills) {
		this.processBills = processBills;
	}

	@Override
	public void accept(MessageProcessor processor, Session session) {
		processor.process(this, session);
	}
}
