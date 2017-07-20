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
