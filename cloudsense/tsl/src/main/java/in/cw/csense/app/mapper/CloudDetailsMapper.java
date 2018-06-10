package in.cw.csense.app.mapper;

import org.springframework.stereotype.Component;

import in.cw.csense.app.dto.CloudDetailsDto;
import in.cw.csense.app.entity.CloudDetails;

@Component
public class CloudDetailsMapper {
	public CloudDetailsDto mapCloudDetails(CloudDetails entity) {
		CloudDetailsDto dto = new CloudDetailsDto();
		dto.setCloudUrl(entity.getCloudUrl());
		dto.setPublicKey(entity.getPublicKey());
		dto.setPrivateKey(entity.getPrivateKey());
		return dto;
	}
}
