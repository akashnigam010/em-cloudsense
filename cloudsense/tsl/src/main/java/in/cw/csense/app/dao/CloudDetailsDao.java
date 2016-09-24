package in.cw.csense.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import cwf.helper.exception.BusinessException;
import in.cw.csense.app.dto.CloudDetailsDto;
import in.cw.csense.app.entity.CloudDetails;
import in.cw.csense.app.mapper.CloudDetailsMapper;
import in.cw.csense.app.type.ClientRegistrationErrorCodeType;

@Repository
public class CloudDetailsDao {

	@Autowired
	MongoTemplate senseMongoTemplate;
	@Autowired
	CloudDetailsMapper mapper;

	public void setSenseMongoTemplate(MongoTemplate senseMongoTemplate) {
		this.senseMongoTemplate = senseMongoTemplate;
	}

	public CloudDetailsDto getCloudDetails() throws BusinessException {
		List<CloudDetails> cloudDetails = senseMongoTemplate.findAll(CloudDetails.class);
		if (cloudDetails.size() != 1) {
			throw new BusinessException(ClientRegistrationErrorCodeType.CLOUD_DETAILS_NOT_FOUND);
		}
		return mapper.mapCloudDetails(cloudDetails.get(0));
	}
}
