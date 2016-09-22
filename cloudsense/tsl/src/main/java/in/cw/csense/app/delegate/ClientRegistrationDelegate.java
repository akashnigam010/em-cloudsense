package in.cw.csense.app.delegate;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cwf.helper.exception.BusinessException;
import in.cw.csense.app.dao.CloudDetailsDao;
import in.cw.csense.app.dao.RestaurantDao;
import in.cw.csense.app.dto.CloudDetailsDto;
import in.cw.csense.app.dto.RestaurantDto;
import in.cw.csense.app.dto.request.ClientRegistrationDetailsRequest;
import in.cw.csense.app.dto.response.ClientRegistrationDetailsResponse;
import in.cw.csense.app.mapper.RestaurantMapper;
import in.cw.csense.app.type.CommonErrorCodeType;

@Service
public class ClientRegistrationDelegate {
	// TODO: remove these after bundles API become functional
	private static final String PASSWORD = "abc123abc";
	private static final String PUBLIC_KEY = "xyzabcxyz123";
	private static final String PRIVATE_KEY = "pqrabcpqr123";

	@Autowired
	RestaurantDao restaurantDao;
	@Autowired
	CloudDetailsDao cloudDetailsDao;
	@Autowired
	RestaurantMapper mapper;

	public ClientRegistrationDetailsResponse getDetailsForClientRegistration() throws BusinessException {
		ClientRegistrationDetailsResponse response = new ClientRegistrationDetailsResponse();
		response.setRestaurantId(restaurantDao.getNextRestaurantId());
		CloudDetailsDto cloudDetailsDto = cloudDetailsDao.getCloudDetails();
		response.setCloudUrl(cloudDetailsDto.getCloudUrl());
		response.setCloudPublicKey(cloudDetailsDto.getPublicKey());
		// TODO : get the below values from Bundles API
		response.setPassword(PASSWORD);
		response.setPublicKey(PUBLIC_KEY);
		response.setPrivateKey(PRIVATE_KEY);
		return response;
	}

	public void saveClient(ClientRegistrationDetailsRequest details) throws BusinessException {
		RestaurantDto dto;
		try {
			dto = mapper.mapClientRegistrationDetailsToDto(details);
		} catch (NoSuchAlgorithmException e) {
			throw new BusinessException(CommonErrorCodeType.SOMETHING_WENT_WRONG);
		}
		restaurantDao.saveRestaurant(dto);
	}

}
