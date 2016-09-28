package in.cw.csense.app.message.element;

public class Message {
	private MessageElementType type;
	private MessageElement messageElement;

	public Message() {
	}

	public MessageElementType getType() {
		return type;
	}

	public void setType(MessageElementType type) {
		this.type = type;
	}

	public MessageElement getMessageElement() {
		return messageElement;
	}

	public void setMessageElement(MessageElement messageElement) {
		this.messageElement = messageElement;
	}

	public Message(MessageElementType type, MessageElement messageElement) {
		super();
		this.type = type;
		this.messageElement = messageElement;
	}
}
