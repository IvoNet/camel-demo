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

package nl.ivonet.context;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * @author ivonet
 */
public class CamelDemoContextTest {

    private CamelDemoContext camelDemoContext;

    @Before
    public void setUp() throws Exception {
        this.camelDemoContext = new CamelDemoContext();
    }

    @Test
    public void projectBaseLocation() throws Exception {
        assertTrue(this.camelDemoContext.projectBaseLocation().endsWith("camel-demo"));
        System.out.println("camelDemoContext.projectBaseLocation() = " + this.camelDemoContext.projectBaseLocation());
    }

}