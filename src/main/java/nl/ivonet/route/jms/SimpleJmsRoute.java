package nl.ivonet.route.jms;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * JMS ActiveMQ demo.
 * <p>
 * ActiveMQ needs to be configured to work.
 * The {@link org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration} class will
 * take care of the auto configuration of the {@link org.apache.activemq.ActiveMQConnectionFactory}.
 * <p>
 * in the first route a file test-data/SimpleJmsRoute folder will be routed to the
 * SimpleJmsRoute Queue in ActiveMQ as configured by the spring.activemq.broker-url property
 * in the application.yml file.
 * <p>
 * The second route watches the SimpleJmsRoute Queue and 'consumes' the messages put on that Queue and
 * logs the body with some help of a {@link Processor} and sends the file to the test-data/ftp/admin
 * folder.
 *
 * For this route to work you need the org.apache.camel:camel-jms dependency in your pom
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class SimpleJmsRoute extends RouteBuilder {

    private final CamelDemoContext context;

    @Autowired
    public SimpleJmsRoute(final CamelDemoContext context) {
        this.context = context;
    }


    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();
        final String queue = String.format("jms:queue:%s", name);


        from(String.format("file://%s/test-data/%s/", projectBaseLocation, name))
                .routeId(name + "_1")
                .to(queue);

        from(queue)
                .routeId(name + "_2")
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    log.info(body);
                })
                .to(String.format("file://%s/test-data/ftp/admin/", projectBaseLocation));
    }
}
