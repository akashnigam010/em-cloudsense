package in.cw.csense.app.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.cw.csense.app.entity.Client;
import in.cw.csense.app.services.WebSocketMessageService;

@Controller 
class HomeController {

	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Map<String, Object> model) {

		Client client = new Client();
		model.put("message", "Welcome");
		model.put("counter", ++counter);
		model.put("client", client); 
		logger.debug("[welcome] counter : {}", counter);

		return VIEW_INDEX;

	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, Map<String, Object> model) {

		model.put("message", "Welcome " + name);
		model.put("counter", ++counter);
		logger.debug("[welcomeName] counter : {}", counter);
		return VIEW_INDEX;

	}

	@RequestMapping(value = "/orderDetails", method = RequestMethod.POST)
	public String orderDetails(@ModelAttribute("client") Client client,  Map<String, Object> model) throws IOException {

		model.put("clientId", client.getClientId());
		WebSocketMessageService messageService = new WebSocketMessageService();
		//String requestID = messageService.sendMessage();
		
		/*while(!ResponseRepository.getRepositoryInstance().gotResponseForRequest(requestID)) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		List<Order> orders = (List<Order>) ResponseRepository.getRepositoryInstance().getResponseForRequest(requestID);
		model.put("orders", orders);
		ResponseRepository.getRepositoryInstance().removeResponseForRequest(requestID);*/
		return "result";

	}

}