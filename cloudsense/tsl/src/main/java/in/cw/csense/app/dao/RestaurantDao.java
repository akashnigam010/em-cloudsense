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
	SenseContext context;
	@Autowired
	RestaurantMapper mapper;

	public RestaurantDto getRestaurant(Integer restaurantId) throws BusinessException {
		MongoTemplate template = context.getSenseDbInstance();
		try {
			Query findQuery = Query.query(Criteria.where(RESTAURANT_ID).is(restaurantId));
			Restaurant restaurant = template.findOne(findQuery, Restaurant.class);
			return mapper.mapRestaurantToDto(restaurant);
		} finally {
			template.getDb().getMongo().close();
		}
	}
	
	public Integer getNextRestaurantId() {
		return (int) context.getNextSequenceId(RESTAURANT_ID_SEQ);
	}
	
	public void saveRestaurant(RestaurantDto dto) throws BusinessException {
		MongoTemplate template = context.getSenseDbInstance();
		try {
			template.save(mapper.mapDtoToRestaurant(dto));
		} finally {
			template.getDb().getMongo().close();
		}
	}
}
