package in.cw.csense.app.entity;

public class OrderDetails extends Result {

	private int orderId;
	private String orderName;
	private String orderType;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public OrderDetails(int orderId, String orderName, String orderType) {
		super();
		this.orderId = orderId;
		this.orderName = orderName;
		this.orderType = orderType;
	}

}
