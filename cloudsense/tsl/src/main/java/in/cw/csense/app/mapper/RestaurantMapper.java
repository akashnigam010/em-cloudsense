package in.cw.csense.app.mapper;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.stereotype.Component;

import cwf.helper.hash.HashUtil;
import in.cw.csense.app.dto.RestaurantDto;
import in.cw.csense.app.dto.request.ClientRegistrationDetailsRequest;
import in.cw.csense.app.entity.Restaurant;

@Component
public class RestaurantMapper {
	public RestaurantDto mapRestaurantToDto(Restaurant entity) {
		RestaurantDto dto = new RestaurantDto();
		dto.setRestaurantId(entity.getRestaurantId());
		dto.setPublicKey(entity.getPublicKey());
		dto.setCreatedDateTime(entity.getCreatedDateTime());
		dto.setLastLoginTime(entity.getLastLoginTime());
		dto.setIsOnline(entity.getIsOnline());
		return dto;
	}

	public Restaurant mapDtoToRestaurant(RestaurantDto dto) {
		Restaurant entity = new Restaurant();
		entity.setRestaurantId(dto.getRestaurantId());
		entity.setPassword(dto.getPassword());
		entity.setPublicKey(dto.getPublicKey());
		entity.setCreatedDateTime(dto.getCreatedDateTime());
		if (dto.getLastLoginTime() != null) {
			entity.setLastLoginTime(dto.getLastLoginTime());
		}
		if (dto.getIsOnline() != null) {
			entity.setIsOnline(dto.getIsOnline());
		}
		return entity;
	}

	public RestaurantDto mapClientRegistrationDetailsToDto(ClientRegistrationDetailsRequest details)
			throws NoSuchAlgorithmException {
		RestaurantDto dto = new RestaurantDto();
		dto.setRestaurantId(details.getRestaurantId());
		dto.setPassword(HashUtil.hash(details.getPassword()));
		dto.setPublicKey(details.getPublicKey());
		dto.setCreatedDateTime(new Date());
		return dto;
	}
}