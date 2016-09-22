package in.cw.csense.app.entity;

public enum RequestResponseType {

	
	ORDER_DETAILS("order_details"),
	BOOKING_DETAILS("booking_details");
	
	private RequestResponseType(final String name) {
		this.name = name;
	}
	
	private String name;
	
	public RequestResponseType getType(final String name) {
		return RequestResponseType.valueOf(name);
	}
	
	public String getName() {
		return this.name;
	}
}
