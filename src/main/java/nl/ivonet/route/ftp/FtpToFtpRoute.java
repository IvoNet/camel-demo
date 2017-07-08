package nl.ivonet.route.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * FTP 2 FTP Demo.
 *
 * if you add a file to the test-data/ftp/admin folder it will be picked up by this route and uploaded to
 * the ftp 'user' where the {@link FtpToFileRoute} will pick it up...
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class FtpToFtpRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        final String name = this.getClass().getSimpleName();
        from("ftp://{{ftp.admin.name}}:{{ftp.admin.password}}@{{ftp.host}}:{{ftp.port}}?passiveMode=true&move=.camel")
                .routeId(name)
                .log("Found file [$simple{header.CamelFileName}] and cp-ing it to the ftp user: user")
                .to("ftp://{{ftp.user.name}}:{{ftp.user.password}}@{{ftp.host}}:{{ftp.port}}?passiveMode=true");

    }
}
