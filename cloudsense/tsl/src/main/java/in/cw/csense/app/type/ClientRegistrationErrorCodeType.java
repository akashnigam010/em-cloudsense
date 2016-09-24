package in.cw.csense.app.type;

import cwf.helper.BusinessErrorCode;

public enum ClientRegistrationErrorCodeType implements BusinessErrorCode {
	CLOUD_DETAILS_NOT_FOUND(20100);

	private int value;

	ClientRegistrationErrorCodeType(int value) {
		this.value = value;
	}

	@Override
	public Integer getBusinessErrorCode() {
		return value;
	}
}
