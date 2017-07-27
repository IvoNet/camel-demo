package nl.ivonet.route.eip.message_routing.aggregator.boundary;

import org.springframework.stereotype.Component;

/**
 * @author Ivo Woltring
 */
@Component
public class MyAggregationStrategyPojo {

    public String assembleTogether(final String oldBody,
                                   final String newBody) {

        if (oldBody == null) {
            return newBody;
        }
        return oldBody.trim() + ", " + newBody.trim();

    }
}
