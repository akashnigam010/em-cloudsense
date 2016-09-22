package in.cw.csense.app.services;

import java.io.IOException;

import javax.websocket.Session;

import org.apache.log4j.Logger;

import in.cw.csense.app.exception.ClientNotFoundException;
import in.cw.csense.app.message.element.Message;
import in.cw.csense.app.message.element.MessageElementType;
import in.cw.csense.app.message.element.RequestMessageElement;
import in.cw.csense.app.util.JsonUtil;
import in.cw.csense.app.websocket.SessionCollector;


public class WebSocketMessageService {

	private static final Logger LOG = Logger
	        .getLogger(WebSocketMessageService.class);
	
	
	/**
	 * @param restaurantId
	 *            the requested client id from where we have to retrieve the
	 *            information.
	 * @return String
	 *         The requestID for the request being sent to local client
	 *         machine. this
	 *         requestID will be used to track the response recieved from
	 *         client.
	 */
	public String sendMessage(final Integer restaurantId) {
		String result = "";
		try {
			Session session = SessionCollector.getSessionForClient(restaurantId);
			Message messageRequest = createRequestJsonForOrderDetails();
			String jsonRequest  = JsonUtil.toJson(messageRequest);
			session.getBasicRemote().sendText(jsonRequest);
			result = ((RequestMessageElement)messageRequest.getMessageElement()).getRequestID();
		} catch (IOException exception) {
			LOG.error(exception.getMessage());
		} catch (ClientNotFoundException clientNotFoundException) {
			LOG.error(clientNotFoundException.getMessage());
		}
		return result;
	}

	/**
	 * Creates the request JSON for order details.
	 * This method is responsible for generating request json to be sent to
	 * local client machine.
	 * 
	 * @return WebSocketRequest an instance of WebSocketRequest containing the
	 *         basic request information.
	 */
	private Message createRequestJsonForOrderDetails() {
		Message message = new Message();
		RequestMessageElement messageElement = new RequestMessageElement();
		messageElement.setRequestID(RequestIDGenerator.getRequestID("Owner"));
		messageElement.setType(MessageElementType.ORDER_DETAIL);
				
		message.setMessageElement(messageElement);
		return message;
	}
}
