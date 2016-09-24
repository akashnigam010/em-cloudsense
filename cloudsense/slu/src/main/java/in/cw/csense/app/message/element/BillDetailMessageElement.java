package in.cw.csense.app.message.element;

import java.util.Date;
import java.util.List;

import javax.websocket.Session;

import in.cw.csense.app.message.processor.MessageProcessor;
import in.cw.sense.api.bo.bill.entity.BillEntity;

public class BillDetailMessageElement implements MessageElement {
	
	private String requestID;
	
	private MessageElementType elementType;

	private Date detailsFrom;
	
	private Date detailsTO;
	
	private List<BillEntity> bills;
	
	public BillDetailMessageElement(final List<BillEntity> bills) {
		this.bills = bills;
	}
	
	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public MessageElementType getElementType() {
		return elementType;
	}

	public void setElementType(MessageElementType elementType) {
		this.elementType = elementType;
	}

	public Date getDetailsFrom() {
		return detailsFrom;
	}

	public void setDetailsFrom(Date detailsFrom) {
		this.detailsFrom = detailsFrom;
	}

	public Date getDetailsTO() {
		return detailsTO;
	}

	public void setDetailsTO(Date detailsTO) {
		this.detailsTO = detailsTO;
	}
	
	public List<BillEntity> getBills() {
		return bills;
	}

	public void setBills(List<BillEntity> bills) {
		this.bills = bills;
	}

	public void accept(MessageProcessor processor, Session session) {
		processor.process(this, session);
	}

}
