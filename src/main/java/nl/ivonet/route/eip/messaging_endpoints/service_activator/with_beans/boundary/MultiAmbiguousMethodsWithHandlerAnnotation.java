package nl.ivonet.route.eip.messaging_endpoints.service_activator.with_beans.boundary;

import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

/**
 * Only the method containing the {@link Handler} annotation
 * is the one called by the multiAmbiguousMethodsWithHandlerAnnotation_3 route.
 *
 * All the methods have the same signature and without the annotation it would throw an
 * {@link org.apache.camel.component.bean.AmbiguousMethodCallException}. Just try it out
 * by commenting out the annotation.
 *
 * @author Ivo Woltring
 */
@Component
@SuppressWarnings("unused")
public class MultiAmbiguousMethodsWithHandlerAnnotation {

    @Handler
    public String sayHello(final String name) {
        return "hello " + name;
    }

    public String sayHello2(final String name) {
        return "WRONG! " + name;
    }

    public String sayHello3(final String name) {
        return "WRONG! " + name;
    }

    public String sayHello4(final String name) {
        return "WRONG! " + name;
    }

    public String sayHello5(final String name) {
        return "Hello route 4 only:" + name;
    }

}
