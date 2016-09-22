package in.cw.csense.app.message.processor;

import java.util.List;

import javax.websocket.Session;

import org.apache.log4j.Logger;

import in.cw.csense.app.entity.ResponseRepository;
import in.cw.csense.app.message.element.BillAckMessageElement;
import in.cw.csense.app.message.element.BillDetailMessageElement;
import in.cw.csense.app.message.element.HandShakeMessageElement;
import in.cw.csense.app.message.element.OrderDetailMessageElement;
import in.cw.csense.app.message.element.ProcessedBill;
import in.cw.csense.app.message.element.RequestMessageElement;
import in.cw.csense.app.websocket.SessionCollector;
import in.cw.sense.api.bo.bill.entity.BillEntity;
import in.cw.sense.api.bo.setting.dto.CloudConnectDto;

public class MessageProcessorImpl implements MessageProcessor {

	private static final Logger LOG = Logger.getLogger(MessageProcessorImpl.class);
	
	public void process(HandShakeMessageElement message, Session session) {
		processNewSession(message, session);
	}

	public void process(OrderDetailMessageElement orderDetail, Session session) {
		processOrderDetails(orderDetail, session);
		
	}

	public void process(BillDetailMessageElement message, Session session) {
		processTheBills(message.getBills(), session);
		System.out.println("Processing the bills... at cloude side");
		
	}

	private void processTheBills(List<BillEntity> bills, final Session session) {
		BillMessageProcessorHelper helper = new BillMessageProcessorHelper(SharedQueue.getInstance().getQueue());
		helper.processBills(bills, session);
		
	}

	public void process(RequestMessageElement message, Session session) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void process(BillAckMessageElement billAckMessageElement, Session session) {
		// TODO Auto-generated method stub
		processBillAckMessage(billAckMessageElement);
		
	}
	

	/**
	 * This method will actually stores the new session into the datastructure.
	 * everytime when clould sense has to connect to its client will use this
	 * session to communicate.
	 * 
	 * @param handShakeMessage
	 *            provided web socket message recieved from client.
	 * @param session
	 *            the new session.
	 */
	private void processNewSession(HandShakeMessageElement handShakeMessage, Session session) {
		CloudConnectDto cloudConnectDto = handShakeMessage.getCloudConnect();
		if (cloudConnectDto != null) {
			SessionCollector.putSession(cloudConnectDto.getRestaurantId(), session);
			LOG.info("New session has been stored in the local map for the client : " + cloudConnectDto.getRestaurantId());
		}
		
	}
	
	private void processOrderDetails(OrderDetailMessageElement orderDetail, Session session) {

		ResponseRepository responseRepository = ResponseRepository.getRepositoryInstance();
		responseRepository.addResponseForRequest(orderDetail.getRequestID(), orderDetail.getOrders());		
	}
	
	private void processBillAckMessage(BillAckMessageElement billAckMessageElement) {
		for(ProcessedBill processBill : billAckMessageElement.getProcessBills()) {
			List<Integer> success = processBill.getSucceededBillIds();
			List<Integer> failed = processBill.getFailedBillIds();
			if(success.isEmpty()) {
				System.out.println("No bills were sucessfull");
			} else {
				for(Integer billId : success) {
					System.out.println("sucess bill id : " + billId);
				}
			}
			
			if(!failed.isEmpty()) {
				for(Integer billId : failed) {
					System.out.println("Failed bill id : " + billId);
				}
			}
			
		}
		
	}

}
