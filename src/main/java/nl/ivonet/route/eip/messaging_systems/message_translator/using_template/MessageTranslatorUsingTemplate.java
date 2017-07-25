package nl.ivonet.route.eip.messaging_systems.message_translator.using_template;

import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.builder.RouteBuilder;

import static java.lang.String.format;

/**
 * A.k.a the Adapter pattern.
 * NOT IMPLEMENTED YET
 *
 * @author Ivo Woltring
 */
//@Slf4j
//@Component
public class MessageTranslatorUsingTemplate extends RouteBuilder {

    private final CamelDemoContext context;

    //@Autowired
    public MessageTranslatorUsingTemplate(final CamelDemoContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();

        from(format("file://%s/target/MessageTranslatorUsingTransform/?noop=true", projectBaseLocation))
                .routeId(name)
                .log("Found file [$simple{header.CamelFileName}] processing xml to velocity template in this route.")
                .log("Input message:\n${body}")
                .unmarshal().jaxb()
                .log("Xml marshalled:\n${body}");
//                .to("velocity://thank_you_message.vm")
//                .log("Velocity templated:\n${body}");
//                .to(format("file://%s/target/%s?fileName=${header.CamelFileName}.xml", projectBaseLocation, name));

    }
}
