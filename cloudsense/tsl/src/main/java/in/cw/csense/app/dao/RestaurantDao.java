package in.cw.csense.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import cwf.dbhelper.SenseContext;
import cwf.helper.exception.BusinessException;
import in.cw.csense.app.dto.RestaurantDto;
import in.cw.csense.app.entity.Restaurant;
import in.cw.csense.app.mapper.RestaurantMapper;

@Repository
public class RestaurantDao {
	private static final String RESTAURANT_ID = "restaurantId";
	private static final String RESTAURANT_ID_SEQ = "restaurant_seq";

	@Autowired
	RestaurantMapper mapper;
	@Autowired
	SenseContext context;
	@Autowired
	MongoTemplate senseMongoTemplate;

	public void setSenseMongoTemplate(MongoTemplate senseMongoTemplate) {
		this.senseMongoTemplate = senseMongoTemplate;
	}

	public RestaurantDto getRestaurant(Integer restaurantId) throws BusinessException {
		Query findQuery = Query.query(Criteria.where(RESTAURANT_ID).is(restaurantId));
		Restaurant restaurant = senseMongoTemplate.findOne(findQuery, Restaurant.class);
		return mapper.mapRestaurantToDto(restaurant);
	}

	public Integer getNextRestaurantId() {
		return (int) context.getNextSequenceId(RESTAURANT_ID_SEQ);
	}

	public void saveRestaurant(RestaurantDto dto) throws BusinessException {
		senseMongoTemplate.save(mapper.mapDtoToRestaurant(dto));
	}
}
