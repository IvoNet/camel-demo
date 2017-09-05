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
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * FTP 2 FTP Demo.
 *
 * if you add a file to the test-data/ftp/admin folder it will be picked up by this route and uploaded to
 * the ftp 'user' where the {@link FtpToFileRoute} will pick it up...
 *
 * For this route to work you need the org.apache.camel:camel-ftp dependency in your pom
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
