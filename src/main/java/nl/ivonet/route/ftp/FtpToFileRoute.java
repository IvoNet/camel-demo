/*
 * Copyright 2017 Ivo Woltring <WebMaster@ivonet.nl>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.ivonet.route.ftp;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Ftp consuming example.
 * <p>
 * To see this route work just move the file from test-data/ftp/user/.camel to test-data/ftp/user.
 * just look at the log and see that the file has been moved back to the camel folder but also
 * appears in target/FtpRoute.
 *
 * For this route to work you need the org.apache.camel:camel-ftp dependency in your pom
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class FtpToFileRoute extends RouteBuilder {

    private final CamelDemoContext context;

    @Autowired
    public FtpToFileRoute(final CamelDemoContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();
        from("ftp://{{ftp.user.name}}:{{ftp.user.password}}@{{ftp.host}}:{{ftp.port}}?passiveMode=true&move=.camel")
              .routeId(name)
              .log(String.format("Found file [$simple{header.CamelFileName}] and copying it to: %s/target/",
                                 projectBaseLocation))
              .to(String.format("file://%s/target/%s/", projectBaseLocation, name));
    }
}
