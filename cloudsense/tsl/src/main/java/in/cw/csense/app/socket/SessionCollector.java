package in.cw.csense.app.socket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import in.cw.csense.app.exception.ClientNotFoundException;

/**
 * @author Arya
 *
 *         Session storage factory, for each active session, there will be an
 *         entry in the data structure.
 */
public class SessionCollector {
	private static Map<Integer, Session> PEERS = new HashMap<>();
	private static Integer CLIENTID = 1001;

	public static void putSession(final Integer restaurentId, final Session session) {
		PEERS.put(restaurentId, session);
	}

	public static Session getSessionForClient(final Integer clientID) throws ClientNotFoundException {
		final Session clientSession;
		if (PEERS.containsKey(clientID)) {
			clientSession = PEERS.get(clientID);
		} else {
			throw new ClientNotFoundException("No client found");
		}
		return clientSession;
	}

	public static Integer getNewClientId() {
		return CLIENTID++;
	}

	public static Map<Integer, Session> getPeers() {
		return PEERS;
	}

	public static void removeSessionFor(final Integer restaurentId) {
		PEERS.remove(restaurentId);
	}
}
