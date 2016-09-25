package in.cw.csense.app.mongo.config;

import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;

@Configuration
@PropertySource(value = "classpath:db.properties", ignoreResourceNotFound = true)
public class CloudSenseMongoConfig {
	@Value("${CLOUD_DB_USERNAME}")
	String CLOUD_DB_USERNAME;

	@Value("${CLOUD_DB_PASSWORD}")
	String CLOUD_DB_PASSWORD;

	@Value("${CLOUD_DB_NAME}")
	String CLOUD_DB_NAME;

	@Value("${CLOUD_DB_HOST}")
	String CLOUD_DB_HOST;

	@Value("${CLOUD_DB_PORT}")
	String CLOUD_DB_PORT;

	@Value("${CLOUD_DB_PROTOCOL}")
	String CLOUD_DB_PROTOCOL;

	@Value("${CLOUD_DB_AUTH_MECHANISM}")
	String CLOUD_DB_AUTH_MECHANISM;

	@Bean
	public MongoClientURI cloudSenseMongoUri() {
		MongoClientURI cloudSenseMongoUri = new MongoClientURI(formCloudSenseMongoUri());
		return cloudSenseMongoUri;
	}

	@Bean
	public MongoDbFactory cloudSenseDbFactory() throws UnknownHostException {
		MongoDbFactory cloudSenseMongoDbFactory = new SimpleMongoDbFactory(cloudSenseMongoUri());
		return cloudSenseMongoDbFactory;
	}

	@Bean
	public MongoTemplate cloudSenseMongoTemplate() throws UnknownHostException {
		MongoTemplate cloudSenseMongoTemplate = new MongoTemplate(cloudSenseDbFactory());
		return cloudSenseMongoTemplate;
	}

	private String formCloudSenseMongoUri() {
		StringBuilder mongoUri = new StringBuilder();
		mongoUri.append(CLOUD_DB_PROTOCOL);
		mongoUri.append("://");
		if (StringUtils.isNotEmpty(CLOUD_DB_USERNAME) && StringUtils.isNotEmpty(CLOUD_DB_PASSWORD)) {
			mongoUri.append(CLOUD_DB_USERNAME);
			mongoUri.append(":");
			mongoUri.append(CLOUD_DB_PASSWORD);
			mongoUri.append("@");
		}
		mongoUri.append(CLOUD_DB_HOST);
		mongoUri.append(":");
		mongoUri.append(CLOUD_DB_PORT);
		mongoUri.append("/");
		mongoUri.append(CLOUD_DB_NAME);
		mongoUri.append("?");
		mongoUri.append("authMechanism=");
		mongoUri.append(CLOUD_DB_AUTH_MECHANISM);
		return mongoUri.toString();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
