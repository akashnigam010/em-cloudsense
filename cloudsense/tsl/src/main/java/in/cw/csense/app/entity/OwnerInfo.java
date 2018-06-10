package in.cw.csense.app.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "owner_info")
public class OwnerInfo {
	@Id
	@Field("ownerId")
	private Integer ownerId;

	@Field("password")
	private String password;

	@Field("restaurantIds")
	private List<Integer> restaurantIds;

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Integer> getRestaurantIds() {
		return restaurantIds;
	}

	public void setRestaurantIds(List<Integer> restaurantIds) {
		this.restaurantIds = restaurantIds;
	}
}
