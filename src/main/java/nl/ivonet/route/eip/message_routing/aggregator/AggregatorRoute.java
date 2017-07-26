package nl.ivonet.route.eip.message_routing.aggregator;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.route.eip.message_routing.aggregator.boundary.MyAggregationStrategy;
import nl.ivonet.route.eip.messaging_endpoints.service_activator.with_beans.ServiceActivatorRoutesWithBeanMethodResolving;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * An Aggregator EIP demo.
 *
 * In this demo we aggregate multiple messages from the jms:topic:names to one message again based on the
 * JMSCorrelationID in the header. this has been set to a constant "FortyTwo" in {@link ServiceActivatorRoutesWithBeanMethodResolving}
 * for this demo. We want all messages to have the same id in this demo.
 *
 * the {@link MyAggregationStrategy} is used to aggregate the messages to one message.
 * A new message is finished when we have a size of 3 aggregated messages or if the timeout has expired.
 * The number of names provided in the topic is not cleanly divisible by 3 so we will get multiple packages of 3
 * aggregated names and one with only bilbo in it because of the timeout.
 *
 * Every time a completed aggregation has occurred it will be logged to console.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class AggregatorRoute extends RouteBuilder {

    private final MyAggregationStrategy myAggregationStrategy;

    @Autowired
    public AggregatorRoute(final MyAggregationStrategy myAggregationStrategy) {
        this.myAggregationStrategy = myAggregationStrategy;
    }

    @Override
    public void configure() throws Exception {

        from("jms:topic:names")
              .routeId(this.getClass()
                           .getSimpleName())
              .convertBodyTo(String.class)
              .log("Received: ${body}")
              .aggregate(header("JMSCorrelationID"), this.myAggregationStrategy)
              .completionSize(3)
              .completionTimeout(2000)
              .log("Aggregated: ${body}");


    }
}
