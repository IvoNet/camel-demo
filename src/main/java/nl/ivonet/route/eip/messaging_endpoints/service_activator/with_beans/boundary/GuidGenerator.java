package nl.ivonet.route.eip.messaging_endpoints.service_activator.with_beans.boundary;

import org.apache.camel.language.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * I'm being used as a {@link Bean} in a ".bean" method reference
 *
 * @author Ivo Woltring
 */
@Component("guid")
public class GuidGenerator {

    /**
     * Just a dummy generator.
     */
    public int generate() {
        final Random ran = new Random();
        return ran.nextInt(10000000);
    }
}

