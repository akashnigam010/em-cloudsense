package in.cw.csense.app.type;

import cwf.helper.BusinessErrorCode;

public enum CommonErrorCodeType implements BusinessErrorCode {
	SOMETHING_WENT_WRONG(99999);

	private int value;

	CommonErrorCodeType(int value) {
		this.value = value;
	}

	public Integer getBusinessErrorCode() {
		return value;
	}
}
