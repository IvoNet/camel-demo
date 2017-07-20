package nl.ivonet.route.eip.messaging_systems.message_translator.using_processor;

import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Aka the Adapter pattern.
 *
 * @author Ivo Woltring
 */
//@Slf4j
//@Component
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
