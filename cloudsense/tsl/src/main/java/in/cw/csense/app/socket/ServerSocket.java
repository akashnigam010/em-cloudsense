package in.cw.csense.app.socket;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import in.cw.csense.app.exception.ClientNotFoundException;
import in.cw.csense.app.processor.MessageProcessor;

@ServerEndpoint(value = "/server", configurator = ServerSocketConfigurator.class)
public class ServerSocket {
	private final MessageProcessor messageProcessor = new MessageProcessor();
	private static final Logger LOG = Logger.getLogger(ServerSocket.class);

	/**
	 * @param endPointConfig
	 * @param session
	 */
	@OnOpen
	public void handleOpen(EndpointConfig endPointConfig, Session session) {
		LOG.debug("******New connection request arrived******");

	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		LOG.debug("handling the message...");
		messageProcessor.process(message, session);
	}

	@OnClose
	public void handleClose(Session session) {
		LOG.info("*******Session is closed for client id : *******");
		closeTheSession(session);
		LOG.info("Session id: " + session.getId() + " closed....");
	}

	/*
	 * @OnError public void handleError(Session session) { LOG.info(
	 * "Session is being closed due to error from client machine.");
	 * closeTheSession(session); }
	 */

	private void closeTheSession(Session session) {
		for (Integer clientID : SessionCollector.getPeers().keySet()) {
			try {
				Session clientSession = SessionCollector.getSessionForClient(clientID);
				if (session.equals(clientSession) && !clientSession.isOpen()) {
					SessionCollector.removeSessionFor(clientID);
				}
			} catch (ClientNotFoundException e) {
				LOG.error("Session is not active on client side with ID:" + clientID, e);
			}
		}
	}
}
