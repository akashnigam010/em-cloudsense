package in.cw.csense.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cwf.helper.ResponseHelper;
import cwf.helper.exception.BusinessException;
import in.cw.csense.app.delegate.ClientRegistrationDelegate;
import in.cw.csense.app.dto.request.ClientRegistrationDetailsRequest;
import in.cw.csense.app.dto.response.ClientRegistrationDetailsResponse;
import in.cw.sense.api.bo.response.StatusResponse;

@RestController
@RequestMapping(value = "/register")
public class ClientRegistrationService {
	@Autowired
	ResponseHelper helper;
	@Autowired
	ClientRegistrationDelegate delegate;

	@RequestMapping(value = "/getRegistrationDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public ClientRegistrationDetailsResponse getRegistrationDetails() {
		ClientRegistrationDetailsResponse response = new ClientRegistrationDetailsResponse();
		try {
			response = delegate.getDetailsForClientRegistration();
			return helper.success(response);
		} catch (BusinessException e) {
			return helper.failure(response, e);
		}
	}

	@RequestMapping(value = "/saveClientDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public StatusResponse saveClientDetails(@RequestBody ClientRegistrationDetailsRequest request) {
		StatusResponse response = new StatusResponse();
		try {
			delegate.saveClient(request);
			return helper.success(response);
		} catch (BusinessException e) {
			return helper.failure(response, e);
		}
	}
}
