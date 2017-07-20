package nl.ivonet.route.eip.reciepentlist;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import nl.ivonet.route.eip.reciepentlist.boundary.AnnotatedReciepentList;
import nl.ivonet.route.file.FileCopyRoute;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A RecipientList EIP demo.
 *
 * In this demo we monitor the test-data/eip/recipient-list folder for XML files.
 * The {@link FileCopyRoute} will copy the xml files found in this folder.
 * If it is a XML file then we ask the {@link AnnotatedReciepentList} class to decide where to sent the message.
 * If it is not an XML file we will not do anything with the file.
 *
 * We also read from the routes decided by the {@link  AnnotatedReciepentList} class to log the message put on those queues
 * just to show that it happened as we expected.
 * In these rotes we get the actual message from the xml file by XPath and put them in the body to log them later.
 *
 * You can also follow in the <a href="http://localhost:8161/">ActiveMQ console</a> what happens (user: admin / pwd: secret)
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class RecipientListRoute extends RouteBuilder {

    private final CamelDemoContext context;

    @Autowired
    public RecipientListRoute(final CamelDemoContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();

        from(String.format("file://%s/test-data/eip/recipient-list/", projectBaseLocation))
                .routeId(name)
                .choice()
                .when(header("CamelFileName").endsWith(".xml"))
                .log("Found file [$simple{header.CamelFileName}] processing xml files in this route.")
                .bean(AnnotatedReciepentList.class)
                .recipientList(header("recipients"))
                .end()
                .otherwise()
                .log("Found file [$simple{header.CamelFileName}] will not process")
                .stop();

        from("jms:test")
                .routeId(name+"_test")
                .setBody().xpath("//message/text()")
                .log("${body}");

        from("jms:production")
                .routeId(name+"_prod")
                .setBody().xpath("//message/text()")
                .log("${body}");

    }
}
