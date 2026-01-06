package itau.worker.queue.infrastructure.config;

import java.util.Objects;

import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import itau.pix.commons.config.DatabaseConstants;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(DatabaseConstants.CONNECTION_STRING);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .applyToConnectionPoolSettings(builder -> {
                    builder.maxSize(50);      // máximo de conexões no pool
                    builder.minSize(5);       // mínimo de conexões abertas
                    builder.maxWaitTime(1000, java.util.concurrent.TimeUnit.MILLISECONDS); // tempo máximo de espera
                })
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(Objects.requireNonNull(mongoClient, "MongoClient não pode ser nulo"), DatabaseConstants.DATABASE_NAME);
    }
}
