package nl.ivonet.route.eip.message_routing.routing_slip;

import nl.ivonet.route.eip.message_routing.routing_slip.boundary.Decider;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Ivo Woltring
 */
@Component
public class RoutingSlipRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        final String name = this.getClass()
                                .getSimpleName();


        from("jms:topic:names")
              .routeId(name + "_1")
              .setHeader("ivonetRoutingSlip")
              .method(Decider.class)
              .routingSlip(header("ivonetRoutingSlip"));

        from("direct:A")
              .routeId(name + "_A")
              .log("ROUTING: A -> ${body}");

        from("direct:B")
              .routeId(name + "_B")
              .log("ROUTING: B -> ${body}");

        from("direct:other")
              .routeId(name + "_O")
              .log("ROUTING: O -> ${body}");
    }
}
