package nl.ivonet.route.eip.message_routing.aggregator.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class CorrelationIdGenerator {

    private final Random random;

    public CorrelationIdGenerator() {
        this.random = new Random();
    }

    public int next() {
        return Math.abs(this.random.nextInt(4) % 4);
    }
}
