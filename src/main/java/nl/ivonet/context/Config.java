package nl.ivonet.context;

import org.apache.camel.component.leveldb.LevelDBAggregationRepository;
import org.apache.camel.spi.AggregationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Ivo Woltring
 */
@Component
public class Config {

    @Bean
    public AggregationRepository aggregationRepository() {
        final LevelDBAggregationRepository levelDB = new LevelDBAggregationRepository("aggregator",
                                                                                      "target/aggregator.db");
        levelDB.setUseRecovery(true);
        levelDB.setMaximumRedeliveries(5);
        levelDB.setDeadLetterUri("jms:queue:dead-letter");
        levelDB.setRecoveryInterval(3000);

        return levelDB;
    }
}
