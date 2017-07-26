package nl.ivonet.route.eip.messaging_systems.message_translator.boundary;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.jsonpath.JsonPath;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * Just to demonstrate the usage of the {@link JsonPath} annotation.
 * I'm used in {@link nl.ivonet.route.eip.messaging_systems.message_translator.using_transform.MessageTranslatorUsingTransform}
 * for the rest this method will only return the body unmodified.
 *
 * will produce something like the following in the console log:
 * <pre>
 * 2017-07-26 17:41:46.306  INFO 13452 --- [latorUsingBean/] n.i.r.e.m.m.boundary.JsonPathBean        : Zip = 5261BH
 * </pre>
 *
 * In order for this annotation to work you need to add the org.apache.camel:camel-jsonpath dependency to your pom.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class JsonPathBean {

    public String printFirstOrderlineZip(@Body final String body,
                                         @JsonPath("$.orderLine[0].address.zip") final String zip) {

        log.info(format("Zip = %s", zip));
        return body;
    }
}
