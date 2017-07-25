package nl.ivonet.route.eip.messaging_systems.message_translator.using_processor;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * A.k.a the Adapter pattern.
 *
 * In this demo the Adapter (MessageTranslator) pattern is shown by using a {@link Processor} ({@link CustomFormatToCsvProcessor})
 * What this route does is getting a custom message from the test-data/eip/messaging_systems/message_translator
 * location and translating it to Csv format.
 * The custom formatted file is completely arbitrarily chosen by me to demo that it could be anything.
 * It will write the created csv file to the target/MessageTranslatorUsingProcessor folder and also log it to console.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class MessageTranslatorUsingProcessor extends RouteBuilder {

    private final CamelDemoContext context;
    private final CustomFormatToCsvProcessor customFormatToCsvProcessor;

    @Autowired
    public MessageTranslatorUsingProcessor(final CamelDemoContext context, final CustomFormatToCsvProcessor customFormatToCsvProcessor) {
        this.context = context;
        this.customFormatToCsvProcessor = customFormatToCsvProcessor;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();

        from(format("file://%s/test-data/eip/messaging_systems/message_translator/?noop=true", projectBaseLocation))
                .routeId(name)
                .log("Found file [$simple{header.CamelFileName}] processing custom format to csv in this route.")
                .log("Custom formatted file:\n${body}")
                .process(this.customFormatToCsvProcessor)
                .log("Csv formatted:\n${body}")
                .to(format("file://%s/target/%s?fileName=${header.CamelFileName}.csv", projectBaseLocation, name));
    }
}
