package in.cw.csense.app.entity;

import java.time.LocalDate;
import java.util.List;

public class Order {

	private Integer orderId;
	private List<Item> items;
	private LocalDate date;

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
