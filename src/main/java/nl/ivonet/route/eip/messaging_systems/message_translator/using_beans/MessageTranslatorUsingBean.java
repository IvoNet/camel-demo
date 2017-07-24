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
 * writes its Csv files and will convert the Csv file to Json using the {@link CsvToJson} bean.
 * It will write its json result to the target/MessageTranslatorUsingBean folder.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class MessageTranslatorUsingBean extends RouteBuilder {

    private final CamelDemoContext context;

    @Autowired
    public MessageTranslatorUsingBean(final CamelDemoContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();

        from(format("file://%s/target/MessageTranslatorUsingProcessor/?noop=true", projectBaseLocation))
                .routeId(name)
                .log("Found file [$simple{header.CamelFileName}] processing csv to json in this route.")
                .bean(new CsvToJson())
                .log("${body}")
                .to(format("file://%s/target/%s?fileName=${header.CamelFileName}.json", projectBaseLocation, name));
    }
}
