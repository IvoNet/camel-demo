package nl.ivonet.route.eip.message_routing.reciepent_list.boundary;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.RecipientList;
import org.apache.camel.language.XPath;
import org.springframework.stereotype.Component;

/**
 * I decide where messages are being sent to for the EIP RecipientList demo.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class AnnotatedRecipientList {

    /**
     * As you can see is the return type an array of strings.
     * These strings are the endpoints to the places where the messages need to be delivered.
     * in this example I only put in one destination per list but you can of course play around with this.
     *
     * @param attribute the "test" attribute in the message element
     * @return an array of endpoints
     */
    @RecipientList
    public String[] route(@XPath("/message/@test") final String attribute) {

        if (isATestMessage(attribute)) {
            log.info("Is a test message");
            return new String[]{"jms:test"};
        } else {
            log.info("Is not a test message");
            return new String[]{"jms:production"};
        }
    }

    private boolean isATestMessage(final String input) {
        return "true".equals(input);
    }
}
