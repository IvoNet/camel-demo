package nl.ivonet.route.eip.messaging_systems.message_translator.using_processor;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A.k.a the Adapter pattern.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class MessageTranslatorUsingProcessor extends RouteBuilder {

    private final CamelDemoContext context;

    @Autowired
    public MessageTranslatorUsingProcessor(final CamelDemoContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {

    }
}
