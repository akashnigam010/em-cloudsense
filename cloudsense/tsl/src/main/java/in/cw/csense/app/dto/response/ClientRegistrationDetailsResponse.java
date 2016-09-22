package in.cw.csense.app.dto.response;

import in.cw.sense.api.bo.response.GenericResponse;

@SuppressWarnings("rawtypes")
public class ClientRegistrationDetailsResponse extends GenericResponse {
	private static final long serialVersionUID = 1L;
	private Integer restaurantId;
	private String password;
	private String publicKey;
	private String privateKey;
	private String cloudUrl;
	private String cloudPublicKey;

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getCloudUrl() {
		return cloudUrl;
	}

	public void setCloudUrl(String cloudUrl) {
		this.cloudUrl = cloudUrl;
	}

	public String getCloudPublicKey() {
		return cloudPublicKey;
	}

	public void setCloudPublicKey(String cloudPublicKey) {
		this.cloudPublicKey = cloudPublicKey;
	}
}
