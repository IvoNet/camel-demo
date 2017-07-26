package nl.ivonet.route.eip.messaging_endpoints.service_activator.with_beans.boundary;

import org.springframework.stereotype.Component;

/**
 * Used in a route with a ".bean" without a method reference.
 * The resolving of which method to use goes fine because the body is a string and therefore there is but
 * one method to handle that kind of input.
 *
 * @author Ivo Woltring
 */
@Component
public class MultiMethodBeanWithClearTypes {

    /**
     * Chosen by route ServiceActivatorRoutesWithBeanResolving_1 because body is a string.
     */
    public String sayHello(final String name) {
        return "hello " + name;
    }

    /**
     * Chosen by route ServiceActivatorRoutesWithBeanResolving_5 because body is a byte[].
     */
    public String sayHello(final byte[] chars) {
        return "hello route 5: " + new String(chars);
    }
}
