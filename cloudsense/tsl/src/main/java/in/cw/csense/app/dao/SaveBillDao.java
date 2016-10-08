package in.cw.csense.app.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import in.cw.csense.app.entity.Bill;
import in.cw.csense.app.mapper.BillMapper;
import in.cw.csense.app.mongo.sequence.generator.CloudSequenceDao;
import in.cw.sense.api.bo.bill.dto.BillDto;

@Repository
public class SaveBillDao {
	private static final Logger LOG = Logger.getLogger(SaveBillDao.class);
	private static final String BILL_ID = "billId";
	private static final String RESTAURANT_ID = "restaurantId";
	private static final String BILL_ID_SEQ = "bill_seq";

	@Autowired BillMapper mapper;
	@Autowired MongoTemplate cloudSenseMongoTemplate;
	@Autowired CloudSequenceDao cloudSequenceDao;

	public void setCloudSenseMongoTemplate(MongoTemplate cloudSenseMongoTemplate) {
		this.cloudSenseMongoTemplate = cloudSenseMongoTemplate;
	}

	public boolean saveBill(BillDto billDto, Integer restaurantId) {
		boolean flag = false;
		Bill bill = null;
		try {
			Query findQuery = Query.query(Criteria.where(BILL_ID).is(billDto.getId()))
					.addCriteria(Criteria.where(RESTAURANT_ID).is(restaurantId));
			List<Bill> findResult = cloudSenseMongoTemplate.find(findQuery, Bill.class);
			if (findResult == null || findResult.isEmpty()) {
				bill = new Bill();
				bill.setId(cloudSequenceDao.getNextSequenceId(BILL_ID_SEQ));
				bill.setRestaurantId(restaurantId);
			} else {
				bill = findResult.get(0);
			}
			mapper.mapBillDtoToEntity(billDto, bill);
			cloudSenseMongoTemplate.save(bill);
			flag = true;
		} catch (Exception e) {
			flag = false;
			LOG.error("Save bill details failed on cloud sense : ", e);
		}
		return flag;
	}
}
