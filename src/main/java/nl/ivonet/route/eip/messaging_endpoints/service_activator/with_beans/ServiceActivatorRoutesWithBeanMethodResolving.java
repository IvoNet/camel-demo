package nl.ivonet.route.eip.messaging_endpoints.service_activator.with_beans;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import nl.ivonet.route.eip.message_routing.aggregator.AggregatorRoute;
import org.apache.camel.Handler;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * A few Service Activator by using beans examples.
 * <p>
 * Route:
 * 0) will take a file from the endpoint and expect on every line one name. It wil split them up and send every name
 * to a topic separately
 * 1) subscribes to the names topic and activate a bean containing only one bean.
 * 2) subscribes to the names topic and activate a bean containing multiple methods but only one will resolve correctly
 * because of the string parameter needed
 * 3) subscribes to the names topic and activate a bean containing multiple methods that would all be equally viable but
 * only one method has the {@link Handler} annotation on it so camel will resolve to this one.
 * 4) subscribes to the names topic and activate a bean with a method reference so that method should be used. This one
 * is the most precise and leaves the least space for discussion and ambiguity.
 * <p>
 * Recommended is the way route 4 does it, but all are viable.
 *
 * The header.JMSCorrelationID is set to a constant for the {@link AggregatorRoute} demo. It has no meaning in this demo.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class ServiceActivatorRoutesWithBeanMethodResolving extends RouteBuilder {

    private final CamelDemoContext context;

    @Autowired
    public ServiceActivatorRoutesWithBeanMethodResolving(final CamelDemoContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass()
                                .getSimpleName();


        from(format("file://%s/test-data/eip/messaging_endpoints/service_activator/with_beans/?noop=true",
                    projectBaseLocation))
              .routeId(name + "_0")
              .convertBodyTo(String.class)
              .split(body().tokenize("\n"))
              .setHeader("JMSCorrelationID", constant("FortyTwo"))
              .log("$simple{header.JMSCorrelationID}")
              .to("jms:topic:names");

        from("jms:topic:names")
              .routeId(name + "_1")
              .bean("singleMethodBean")
              .log("${body}");

        from("jms:topic:names")
              .routeId(name + "_2")
              .bean("multiMethodBeanWithClearTypes")
              .log("${body}");

        from("jms:topic:names")
              .routeId(name + "_3")
              .bean("multiAmbiguousMethodsWithHandlerAnnotation")
              .log("${body}");

        from("jms:topic:names")
              .routeId(name + "_4")
              .bean("multiAmbiguousMethodsWithHandlerAnnotation", "sayHello5")
              .log("${body}");

        from("jms:topic:names")
              .routeId(name + "_5")
              .convertBodyTo(byte[].class)
              .bean("multiMethodBeanWithClearTypes")
              .log("${body}");


    }
}
