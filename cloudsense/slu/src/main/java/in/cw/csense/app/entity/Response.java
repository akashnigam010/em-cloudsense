package in.cw.csense.app.entity;

import java.util.List;

public class Response {

	private String clientID;

	private RequestResponseType type;

	private List<Order> orders;
	
	private List<BookingDetails> bookings;
	
	private String responseID;
	
	public String getResponseID() {
		return responseID;
	}

	public void setResponseID(String responseID) {
		this.responseID = responseID;
	}

	public Response() {
		
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public RequestResponseType getType() {
		return type;
	}

	public void setType(RequestResponseType type) {
		this.type = type;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<BookingDetails> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingDetails> bookings) {
		this.bookings = bookings;
	}
	
	

}
