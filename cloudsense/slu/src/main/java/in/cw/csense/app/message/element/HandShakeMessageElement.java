package in.cw.csense.app.message.element;

import javax.websocket.Session;

import in.cw.csense.app.message.processor.MessageProcessor;
import in.cw.sense.api.bo.setting.dto.CloudConnectDto;

public class HandShakeMessageElement implements MessageElement {
	private CloudConnectDto cloudConnect;

	public HandShakeMessageElement(CloudConnectDto cloudConnectDto) {
		this.cloudConnect = cloudConnectDto;
	}

	public CloudConnectDto getCloudConnect() {
		return cloudConnect;
	}

	public void setCloudConnect(CloudConnectDto cloudConnect) {
		this.cloudConnect = cloudConnect;
	}

	@Override
	public void accept(MessageProcessor processor, Session session) {
		processor.process(this, session);
	}
}
