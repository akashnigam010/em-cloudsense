package in.cw.csense.app.entity;

import in.cw.csense.app.entity.RequestResponseType;

public class WebSocketRequest {

	private String requestID;
	
	private RequestResponseType type;

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public RequestResponseType getType() {
		return type;
	}

	public void setType(RequestResponseType type) {
		this.type = type;
	}
	
	
}
