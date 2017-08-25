package nl.ivonet.route.eip.message_routing.routing_slip.boundary;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.route.eip.message_routing.routing_slip.RoutingSlipRoute;
import org.springframework.stereotype.Component;

/**
 * The single method in this class is used to decide where the next message
 * should be send to in the {@link RoutingSlipRoute} class.
 *
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
