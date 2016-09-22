package in.cw.csense.app.entity;

import java.math.BigDecimal;

public class Item {
	private Integer itemId;
	private String itemName;
	private Integer quantity;
	private BigDecimal price;

	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Item(Integer itemId, String itemName, Integer quantity, BigDecimal price) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}
	
	
}
