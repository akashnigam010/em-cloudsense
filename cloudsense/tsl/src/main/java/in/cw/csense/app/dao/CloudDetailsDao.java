package in.cw.csense.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import cwf.dbhelper.SenseContext;
import cwf.helper.exception.BusinessException;
import in.cw.csense.app.dto.CloudDetailsDto;
import in.cw.csense.app.entity.CloudDetails;
import in.cw.csense.app.mapper.CloudDetailsMapper;
import in.cw.csense.app.type.ClientRegistrationErrorCodeType;

@Repository
public class CloudDetailsDao {

	@Autowired
	SenseContext context;
	@Autowired
	CloudDetailsMapper mapper;

	public CloudDetailsDto getCloudDetails() throws BusinessException {
		MongoTemplate template = context.getSenseDbInstance();
		try {
			List<CloudDetails> cloudDetails = template.findAll(CloudDetails.class);
			if (cloudDetails.size() != 1) {
				throw new BusinessException(ClientRegistrationErrorCodeType.CLOUD_DETAILS_NOT_FOUND);
			}
			return mapper.mapCloudDetails(cloudDetails.get(0));

		} finally {
			template.getDb().getMongo().close();
		}
	}
}
