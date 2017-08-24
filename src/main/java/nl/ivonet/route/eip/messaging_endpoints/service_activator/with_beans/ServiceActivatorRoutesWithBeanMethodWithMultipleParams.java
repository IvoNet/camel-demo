package nl.ivonet.route.eip.messaging_endpoints.service_activator.with_beans;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.Handler;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
 *
 * Recommended is the way route 4 does it, but all are viable.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class ServiceActivatorRoutesWithBeanMethodWithMultipleParams extends RouteBuilder {

    private final CamelDemoContext context;

    @Autowired
    public ServiceActivatorRoutesWithBeanMethodWithMultipleParams(final CamelDemoContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass()
                                .getSimpleName();


        from("jms:topic:names")
              .routeId(name + "_1")
              .bean("beanMultiParamMethods")
              .log("${body}");


    }
}
