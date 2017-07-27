package nl.ivonet.route.eip.message_routing.aggregator;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.route.eip.message_routing.aggregator.boundary.MyAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * An Aggregator EIP demo.
 * <p>
 * Routes:
 * 1) In this demo we first get all the names from the names topic and send them to an new topic (names_with_generated_correlation_ids) with randomly generated
 * correlation ids between 1 and 4.
 * 2) In this demo we aggregate multiple messages from the jms:topic:names_with_generated_correlation_ids to one message again based on the
 * JMSCorrelationID in the header as generated in the first route.
 * <p>
 * the {@link MyAggregationStrategy} is used to aggregate the messages to one message.
 * A new message is finished when we have a size of 3 aggregated messages or if the timeout has expired.
 * In the end one to four correlationIds may not have sufficient names to complete on the completionSize and will then
 * end on the timeout. Less then 3 names will then be in the message.
 * <p>
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
        final String name = this.getClass()
                                .getSimpleName();

        from("jms:topic:names")
              .routeId(name + "_1")
              .setHeader("JMSCorrelationID", method("correlationIdGenerator", "next"))
              .log("CorrelationId: ${header[JMSCorrelationID]}")
              .to("jms:topic:names_with_generated_correlation_ids");

        from("jms:topic:names_with_generated_correlation_ids")
              .routeId(name+"_2")
              .convertBodyTo(String.class)
              .log("Received: ${body}")
              .log("CorrelationId: ${header[JMSCorrelationID]}")
              .aggregate(header("JMSCorrelationID"), this.myAggregationStrategy)
              .completionSize(3)
              .completionTimeout(2000)
              .log("Aggregated: ${body}");

    }
}
