package nl.ivonet.route.eip.message_routing.aggregator.boundary;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

/**
 * An {@link AggregationStrategy} example for simply adding two messages together.
 *
 * If the oldExchange is null then it is the first message in the aggregation.
 *
 * @author Ivo Woltring
 */
@Component
public class MyAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(final Exchange oldExchange,
                              final Exchange newExchange) {

        if (oldExchange == null) {
            return newExchange;
        }

        final String ret = oldExchange.getIn().getBody(String.class);
        final String newEx = newExchange.getIn().getBody(String.class);

        oldExchange.getIn().setBody(ret + "\n" + newEx);

        return oldExchange;
    }
}
