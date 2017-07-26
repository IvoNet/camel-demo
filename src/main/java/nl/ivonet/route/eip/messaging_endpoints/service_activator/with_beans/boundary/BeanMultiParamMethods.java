package nl.ivonet.route.eip.messaging_endpoints.service_activator.with_beans.boundary;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.apache.camel.language.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.lang.String.format;

/**
 * Just a small demo of calling a bean with multiple parameters in a route.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class BeanMultiParamMethods {

    public String sayAll(@Body final String body,
                         @Headers final Map<String, Object> headers,
                         @Bean(ref = "guid", method = "generate") final int id) {

        for (final String key : headers.keySet()) {
            log.info(format("%-25s = %s", key, headers.get(key)));
        }

        return format("hello [%s] with id [%s]", body, id);
    }
}
