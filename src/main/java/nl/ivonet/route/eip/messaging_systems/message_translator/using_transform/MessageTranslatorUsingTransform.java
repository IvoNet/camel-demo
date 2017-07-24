package nl.ivonet.route.eip.messaging_systems.message_translator.using_transform;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import nl.ivonet.route.eip.messaging_systems.message_translator.using_beans.boundary.Order;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * A.k.a the Adapter pattern.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class MessageTranslatorUsingTransform extends RouteBuilder {

    private final CamelDemoContext context;

    @Autowired
    public MessageTranslatorUsingTransform(final CamelDemoContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();

        from(format("file://%s/target/MessageTranslatorUsingBean/?noop=true", projectBaseLocation))
                .routeId(name)
                .log("Found file [$simple{header.CamelFileName}] processing csv to json in this route.")
                .log("${body}")
                .transform(method(Order.class, "fromJson"))
                .process(exchange -> {
                    final Order body = (Order) exchange.getIn().getBody();
                    log.info(body.getOrderLines().get(0).getDateTime().toString());
                })
                .log("${body}")
                .marshal().jaxb()
                .log("${body}")
                .to(format("file://%s/target/%s?fileName=${header.CamelFileName}.xml", projectBaseLocation, name));

    }
}
