package eu.luminis.dataSamplerPOC.mongo;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;


@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "DataSampler";
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1:27017");
    }
}
