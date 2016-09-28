package in.cw.csense.app.message.processor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SharedQueue {
	private static final SharedQueue sharedQueue = new SharedQueue();
	private final BlockingQueue blockingQueue = new LinkedBlockingQueue();

	private SharedQueue() {
	}

	public static SharedQueue getInstance() {
		return sharedQueue;
	}

	public BlockingQueue getQueue() {
		return this.blockingQueue;
	}
}
