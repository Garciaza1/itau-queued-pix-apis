package itau.gateway.queue.infrastructure.config;

import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import itau.pix.commons.config.DatabaseConstants;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new com.mongodb.ConnectionString(DatabaseConstants.CONNECTION_STRING))
                        .uuidRepresentation(UuidRepresentation.STANDARD) // 🔑 aqui define o padrão
                        .build()
        );
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, DatabaseConstants.DATABASE_NAME);
    }
}
