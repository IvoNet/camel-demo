package nl.ivonet.route.file;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import nl.ivonet.route.eip.reciepentlist.boundary.AnnotatedRecipientList;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * When running this example and you have configured the project.base.folder
 * correctly it will copy all files from the test-data/SimpleFileCopyRoute folder
 * to test-data/ftp/admin folder which is also the 'home' folder for the ftp admin user.
 * <p>
 * It is named to illustrate that and it adds some logs and a bit of Simple language
 * usage.
 * <p>
 * If you remove '?noop=true' the copy will become a move.
 * <p>
 * In this example a kind of filter is build in.
 * This specific route takes all files but send them to different end-points.
 * The xml files will be send to the eip/recipient-list. In the RecipientList demo I will do the same but illustrate more.
 * The {@link AnnotatedRecipientList} class is being reused here.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class FileCopyRoute extends RouteBuilder {

    private final CamelDemoContext context;

    @Autowired
    public FileCopyRoute(final CamelDemoContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();

        from(String.format("file://%s/test-data/startingPoint/?noop=true", projectBaseLocation))
                .routeId(name)
                .choice()
                .when(header("CamelFileName").endsWith(".xml"))
                .log("Found file [$simple{header.CamelFileName}] and will copy them to eip recipient-list.")
                .bean(AnnotatedRecipientList.class)
                .recipientList(header("recipients"))
                .end()
                .otherwise()
                .log(String.format("Found file [$simple{header.CamelFileName}] and copying it to: %s/test-data/SimpleJmsRoute/", projectBaseLocation))
                .to(String.format("file://%s/test-data/SimpleJmsRoute/", projectBaseLocation))
                .end();
    }
}
