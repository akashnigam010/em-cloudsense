package in.cw.csense.app.message.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.websocket.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.cw.csense.app.dao.SaveBillDao;
import in.cw.csense.app.message.element.ProcessedBill;
import in.cw.sense.api.bo.bill.dto.BillDto;

@Repository
public class BillMessageProcessorHelper {
	private static final Logger LOG = Logger.getLogger(BillMessageProcessorHelper.class);
	
	@Autowired
	private SaveBillDao dao;
	
	private final BlockingQueue<ProcessedBill> queue;

	public BillMessageProcessorHelper(BlockingQueue<ProcessedBill> sharedQueue) {
		this.queue = sharedQueue;
	}
	
	public BillMessageProcessorHelper() {
		queue = SharedQueue.getInstance().getQueue();
	}

	public void processBills(List<BillDto> bills, Integer restaurantId, final Session session) {
		@SuppressWarnings("unused")
		//TODO:Rename this Test class respectively
		Test test = new Test(session);
		ProcessedBill processedBill = new ProcessedBill();
		List<Integer> successBillIds = new ArrayList<>();
		List<Integer> failedBillIds = new ArrayList<>();

		for (BillDto bill : bills) {
			boolean flag = dao.saveBill(bill, restaurantId);
			if (flag) {
				successBillIds.add(bill.getId());
			} else {
				failedBillIds.add(bill.getId());
			}
		}
		processedBill.setFailedBillIds(failedBillIds);
		processedBill.setSucceededBillIds(successBillIds);

		try {
			this.queue.put(processedBill);
		} catch (InterruptedException e) {
			LOG.error("Exception occured while updating queue with processed bills", e);
		}

		LOG.debug("Total bills processed are : " + bills.size());
	}
}
