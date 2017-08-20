package nl.ivonet.route.eip.message_routing.routing_slip.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class Decider {

    public String decide(final String body) {
        log.info(body);
        if (body.startsWith("A")) {
            return "direct:A";
        }
        if (body.startsWith("B")) {
            return "direct:B";
        }
        return "direct:other";
    }
}
