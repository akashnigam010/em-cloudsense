package in.cw.csense.app.message.element;

import java.util.Date;
import java.util.List;

import javax.websocket.Session;

import in.cw.csense.app.message.processor.MessageProcessor;
import in.cw.sense.api.bo.bill.dto.BillDto;

public class BillDetailMessageElement implements MessageElement {
	private String requestID;
	private MessageElementType elementType;
	private Date detailsFrom;
	private Date detailsTO;
	private List<BillDto> bills;
	private Integer restaurantId;

	public BillDetailMessageElement(final List<BillDto> bills, Integer restaurantId) {
		this.bills = bills;
		this.restaurantId = restaurantId;
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

	public List<BillDto> getBills() {
		return bills;
	}

	public void setBills(List<BillDto> bills) {
		this.bills = bills;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public void accept(MessageProcessor processor, Session session) {
		processor.process(this, session);
	}
}
