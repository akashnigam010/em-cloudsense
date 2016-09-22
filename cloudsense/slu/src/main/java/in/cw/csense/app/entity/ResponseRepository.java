package in.cw.csense.app.entity;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public final class ResponseRepository {

	private ConcurrentHashMap<String, Collection<Order>> responseStore = new ConcurrentHashMap<>();
	private static final ResponseRepository responseRepository = new ResponseRepository();
	
	private ResponseRepository() {
		
	}
	
	public synchronized static ResponseRepository getRepositoryInstance() {
		return  responseRepository;
	}
	
	public void addResponseForRequest(final String requestID, final Collection<Order> orders) {
		responseStore.put(requestID, orders);
	}
	
	public boolean gotResponseForRequest(final String requestID) {
		return responseStore.containsKey(requestID);
	}
	
	public void removeResponseForRequest(final String requestID) {
		responseStore.remove(requestID);
	}
	
	public Collection<Order> getResponseForRequest(final String requestID) {
		return responseStore.get(requestID);
	}
	
	
}
