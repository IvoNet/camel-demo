package nl.ivonet.route.jms;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ivonet
 */
@Slf4j
@Component
public class SimpleJmsRoute extends RouteBuilder {

    @Autowired
    private CamelDemoContext context;


    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();
        final String queue = String.format("jms:queue:%s", name);


        from(String.format("file://%s/test-data/%s/", projectBaseLocation, name))
                .routeId(name + "_1")
                .to(queue);

        from(queue)
                .routeId(name + "_2")
                .process(exchange -> {
                    final Object body = exchange.getIn().getBody();
                    log.info(new String((byte[]) body));
                })
                .to(String.format("file://%s/test-data/ftp/admin/", projectBaseLocation));
    }
}
