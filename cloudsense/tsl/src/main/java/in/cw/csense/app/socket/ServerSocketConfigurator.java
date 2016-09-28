package in.cw.csense.app.socket;

import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class ServerSocketConfigurator extends ServerEndpointConfig.Configurator {
	/**
	 * @param sec  
	 * @param request 
	 * @param response 
	 */
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeRequest response) {
	}
}
