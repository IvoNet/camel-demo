package nl.ivonet.route.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
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

    @Override
    public void configure() throws Exception {
        final String name = this.getClass().getSimpleName();
        from(String.format("file://{{project.base.folder}}/test-data/%s/?noop=true", name))
                .routeId(name)
                .log("Found file [$simple{header.CamelFileName}] and cp-ing it to: {{project.base.folder}}/target/" + name)
                .to(String.format("file://{{project.base.folder}}/target/%s", name))
                .log(String.format("If you add files to the test-data/%s folder you can see this route happening...", name));

// The same route but then in shortest form...
//        from(String.format("file://{{project.base.folder}}/test-data/%s/?noop=true", name))
//                .to(String.format("file://{{project.base.folder}}/target/%s", name));
    }
}
