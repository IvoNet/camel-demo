package nl.ivonet.route.ftp;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Ftp consuming example.
 * <p>
 * To see this route work just move the file from test-data/ftp/user/camel to test-data/ftp/user.
 * just look at the log and see that the file has been moved back to the camel folder but also
 * appears in target/FtpRoute.
 *
 * @author ivonet
 */
@Slf4j
@Component
public class FtpToFileRoute extends RouteBuilder {

    @Autowired
    private CamelDemoContext context;

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();
//        from("ftp://{{ftp.user.name}}:{{ftp.user.password}}@{{ftp.host}}:{{ftp.port}}?passiveMode=true&delete=true") // Deletes the file
        from("ftp://{{ftp.user.name}}:{{ftp.user.password}}@{{ftp.host}}:{{ftp.port}}?passiveMode=true&move=.camel")
                .routeId(name)
                .log(String.format("Found file [$simple{header.CamelFileName}] and copying it to: %s/target/", projectBaseLocation))
                .to(String.format("file://%s/target/%s/", projectBaseLocation, name));
    }
}