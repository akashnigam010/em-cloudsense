package in.cw.csense.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import cwf.dbhelper.sequencegenerator.SequenceDao;
import in.cw.csense.app.dto.RestaurantDto;
import in.cw.csense.app.entity.Restaurant;
import in.cw.csense.app.mapper.RestaurantMapper;

@Repository
public class RestaurantDao {
	private static final String RESTAURANT_ID = "restaurantId";
	private static final String RESTAURANT_ID_SEQ = "restaurant_seq";

	@Autowired RestaurantMapper mapper;
	@Autowired SequenceDao sequenceDao;
	@Autowired MongoTemplate senseMongoTemplate;

	public void setSenseMongoTemplate(MongoTemplate senseMongoTemplate) {
		this.senseMongoTemplate = senseMongoTemplate;
	}

	public RestaurantDto getRestaurant(Integer restaurantId) {
		Query findQuery = Query.query(Criteria.where(RESTAURANT_ID).is(restaurantId));
		Restaurant restaurant = senseMongoTemplate.findOne(findQuery, Restaurant.class);
		return mapper.mapRestaurantToDto(restaurant);
	}

	public int getNextRestaurantId() {
		return sequenceDao.getNextSequenceId(RESTAURANT_ID_SEQ);
	}

	public void saveRestaurant(RestaurantDto dto) {
		senseMongoTemplate.save(mapper.mapDtoToRestaurant(dto));
	}
}
