package nl.ivonet.route.eip.messaging_systems.message_translator.using_beans;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * A.k.a the Adapter pattern.
 *
 * This route demo's the MessageTranslator pattern by using a Bean for the mapper.
 * This route monitors the folder where the {@link nl.ivonet.route.eip.messaging_systems.message_translator.using_processor.MessageTranslatorUsingProcessor}
 * print its Csv entries to console.
 * Then it will marshall the csv to a list of lists of strings by using the csv marshaller provided by Camel.
 * The bean demoed here will convert the unmarshal-ed entries to Json using the {@link CsvToJson} bean.
 * It will write its json result to the target/MessageTranslatorUsingBean folder to view or use in another demo.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class MessageTranslatorUsingBean extends RouteBuilder {

    private final CamelDemoContext context;
    private final CsvToJson csvToJson;

    @Autowired
    public MessageTranslatorUsingBean(final CamelDemoContext context, final CsvToJson csvToJson) {
        this.context = context;
        this.csvToJson = csvToJson;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();

        from(format("file://%s/target/MessageTranslatorUsingProcessor/?noop=true", projectBaseLocation))
                .routeId(name)
                .log("Found file [$simple{header.CamelFileName}] processing csv to json in this route.")
                .log("Input message:\n${body}")
                .unmarshal().csv()
                .log("CSV unmarshalled:\n${body}")
                .bean(this.csvToJson)
                .log("Bean mapped to json:\n${body}")
                .to(format("file://%s/target/%s?fileName=${header.CamelFileName}.json", projectBaseLocation, name));
    }
}
