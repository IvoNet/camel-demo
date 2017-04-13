package nl.ivonet.route.file;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * When running this example and you have configured the project.base.folder
 * correctly it will copy all files from the test-data/SimpleFileCopyRoute folder
 * to target/SimpleFileCopyRoute folder.
 * <p>
 * It is named to illustrate that and it adds some logs and a bit of Simple language
 * usage.
 * <p>
 * If you remove '?noop=true' the copy will become a move.
 *
 * @author ivonet
 */
@Slf4j
@Component
public class SimpleFileCopyRoute extends RouteBuilder {

    @Autowired
    private CamelDemoContext context;

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();
        from(String.format("file://%s/test-data/%s/?noop=true", projectBaseLocation, name))
                .routeId(name)
                .log(String.format("Found file [$simple{header.CamelFileName}] and copying it to: %s/test-data/ftp/admin/", projectBaseLocation))
                .to(String.format("file://%s/test-data/ftp/admin/", projectBaseLocation));
    }
}
