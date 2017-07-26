package nl.ivonet.route.eip.messaging_endpoints.service_activator.with_beans.boundary;

import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * Used in a route with a ".bean" without a method reference.
 * The resolving of which method to use goes fine because the body is a string and therefore there is but
 * one method to handle that kind of input.
 *
 * @author Ivo Woltring
 */
@Component
public class MultiMethodBeanWithClearTypes {

    public String sayHello(final String name) {
        return "hello " + name;
    }

    /**
     * This method should never be called in the ServiceActivatorRoutesWithBeanResolving_2 route.
     * This is because the other method has a string as a parameter and the body is a string.
     */
    @SuppressWarnings("unused")
    public String sayHello(final InputStream name) {
        return "WRONG! " + name;
    }

}
