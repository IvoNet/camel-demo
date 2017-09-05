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

package nl.ivonet.route.jms;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * JMS ActiveMQ demo.
 * <p>
 * ActiveMQ needs to be configured to work.
 * The {@link org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration} class will
 * take care of the auto configuration of the {@link org.apache.activemq.ActiveMQConnectionFactory}.
 * The endpoint provided in the application.yml (or application.properties) file will tell the
 * autoconfigure with which url to create the connectionFactory.
 * <p>
 * In the first route a file test-data/SimpleJmsRoute folder will be routed to the
 * SimpleJmsRoute Queue in ActiveMQ as configured by the spring.activemq.broker-url property
 * in the application.yml file.
 *
 * For this route to work you need the the following dependencies:
 * <ul>
 * <li>org.apache.activemq:activemq-all or in our case the org.springframework.boot:spring-boot-starter-activemq</li>
 * <li>org.apache.camel:camel-jms</li>
 * </ul>
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class SimpleJmsRoute extends RouteBuilder {

    private final String projectBaseLocation;

    @Autowired
    public SimpleJmsRoute(final CamelDemoContext context) {
        this.projectBaseLocation = context.projectBaseLocation();
    }


    @Override
    public void configure() throws Exception {
        final String name = this.getClass().getSimpleName();
        final String queue = String.format("jms:queue:%s", name);


        from(String.format("file://%s/test-data/%s/", this.projectBaseLocation, name))
              .routeId(name + "_1")
              .to(queue);

        from(queue)
              .routeId(name + "_2")
              .process(exchange -> {
                  String body = exchange.getIn()
                                        .getBody(String.class);
                  log.info(body);
              })
              .to(String.format("file://%s/test-data/ftp/admin/", this.projectBaseLocation));
    }
}
