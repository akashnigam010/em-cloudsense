package in.cw.csense.app.message.processor;

import javax.websocket.Session;

public class Test {
	public Test(Session session) {
		BillMessageAcknowledger acknowledger = new BillMessageAcknowledger(SharedQueue.getInstance().getQueue(),
				session);
		Thread t = new Thread(acknowledger);
		t.start();
	}
}
