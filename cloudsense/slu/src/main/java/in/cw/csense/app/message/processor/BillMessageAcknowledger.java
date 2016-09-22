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
				System.out.println("Message consumed : ");
				ProcessedBill processedBill = this.queue.take();
				try {
					if (session != null && session.isOpen()) {
						session.getBasicRemote().sendText(prepareMessage(processedBill));
					} else  {
						System.out.println("Cant send bill message ack as session is closed.");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (InterruptedException ex) {
				LOG.debug(ex);
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
