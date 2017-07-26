package nl.ivonet.route.eip.messaging_endpoints.service_activator.with_beans.boundary;

import org.springframework.stereotype.Component;

/**
 * This bean only has one method and can therefore be used as a bean reference without providing a method reference.
 *
 * It is not recommended to do this because of maintainability and readability.
 *
 * @author Ivo Woltring
 */
@Component
public class SingleMethodBean {
    public String sayHello(final String name) {
        return "hello " + name;
    }
}
