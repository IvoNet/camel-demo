package nl.ivonet.route.eip.reciepentlist.boundary;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.RecipientList;
import org.apache.camel.language.XPath;

/**
 * I decide where messages are being sent to for the EIP RecipientList demo.
 *
 * @author Ivo Woltring
 */
@Slf4j
public class AnnotatedReciepentList {

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
