package itau.gateway.queue.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClients;

import itau.pix.commons.config.DatabaseConstants;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(
                MongoClients.create(DatabaseConstants.CONNECTION_STRING),
                DatabaseConstants.DATABASE_NAME
        );
    }
}
