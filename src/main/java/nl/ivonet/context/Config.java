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
        return new LevelDBAggregationRepository("aggregator", "target/aggregator.db");
    }
}
