package in.cw.csense.app.message.processor;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import javax.websocket.Session;

import org.apache.log4j.Logger;

import in.cw.csense.app.message.element.BillAckMessageElement;
import in.cw.csense.app.message.element.Message;
import in.cw.csense.app.message.element.MessageElementType;
import in.cw.csense.app.message.element.ProcessedBill;
import in.cw.csense.app.util.JsonUtil;

public class BillMessageAcknowledger implements Runnable {
	private static final Logger LOG = Logger.getLogger(BillMessageAcknowledger.class);
	private final BlockingQueue<ProcessedBill> queue;
	private final Session session;

	public BillMessageAcknowledger(BlockingQueue queue, Session session) {
		this.queue = queue;
		this.session = session;
	}

	@Override
	public void run() {
		while (true) {
			try {
				LOG.info("Message has been consumed");
				ProcessedBill processedBill = this.queue.take();
				try {
					if (session != null && session.isOpen()) {
						session.getBasicRemote().sendText(prepareMessage(processedBill));
					} else {
						LOG.warn("Session closed, cant send bill message acknowledgment...");
					}
				} catch (IOException e) {
					LOG.error("Exception occured while trying to send message acknowledgment..", e);
				}
			} catch (InterruptedException e) {
				LOG.error("Exception occured while fetching details from queue...", e);
			}
		}
	}

	private String prepareMessage(final ProcessedBill processedBill) {
		Message message = new Message();
		message.setType(MessageElementType.BILL_ACK);
		BillAckMessageElement messageElement = new BillAckMessageElement();
		messageElement.setProcessBills(Arrays.asList(processedBill));
		message.setMessageElement(messageElement);
		return JsonUtil.toJson(message);
	}
}
