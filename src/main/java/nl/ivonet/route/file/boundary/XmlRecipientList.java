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

package nl.ivonet.route.file.boundary;

import nl.ivonet.context.CamelDemoContext;
import org.apache.camel.RecipientList;

import static java.lang.String.format;

/**
 * Returns the location to the recipient_list demo "from" point.
 *
 * @author Ivo Woltring
 */
public class XmlRecipientList {

    @RecipientList
    public String[] recipientListProvider() {
        final String projectBaseLocation = new CamelDemoContext().projectBaseLocation();
        return new String[]{format("file://%s/test-data/eip/message_routing/recipient_list/", projectBaseLocation)};
    }

}
