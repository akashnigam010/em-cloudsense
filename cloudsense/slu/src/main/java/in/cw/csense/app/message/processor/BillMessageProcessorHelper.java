package in.cw.csense.app.message.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.websocket.Session;

import in.cw.csense.app.message.element.ProcessedBill;
import in.cw.sense.api.bo.bill.dto.BillDto;
import in.cw.sense.api.bo.bill.entity.BillEntity;

public class BillMessageProcessorHelper {

	private final BlockingQueue<ProcessedBill> queue;
	
	public BillMessageProcessorHelper(BlockingQueue<ProcessedBill> sharedQueue) {
		this.queue =  sharedQueue;
	}
	
	public void processBills(List<BillEntity> bills, final Session session) {
		@SuppressWarnings("unused")
		Test test = new Test(session);
		ProcessedBill processedBill = new ProcessedBill();
		List<Integer> successBillIds = new ArrayList<>();
		List<Integer> failedBillIds = new ArrayList<>();
		
		for(BillEntity bill : bills) {
			if(insertOrUpdate(bill)) {
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
			e.printStackTrace();
		}
		System.out.println("Total bill processed : " + bills.size());
	}

	private boolean insertOrUpdate(BillEntity billDto) {
		return true;
		
	}
}
