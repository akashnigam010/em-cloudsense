package in.cw.csense.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cloud_details")
public class CloudDetails {
	@Id
	@Field
	private Integer id;

	@Field("publicKey")
	private String publicKey;

	@Field("privateKey")
	private String privateKey;

	@Field("cloudUrl")
	private String cloudUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
}
