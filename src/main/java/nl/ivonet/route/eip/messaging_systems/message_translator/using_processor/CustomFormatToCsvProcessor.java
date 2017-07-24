package nl.ivonet.route.eip.messaging_systems.message_translator.using_processor;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.route.eip.messaging_systems.message_translator.boundary.Address;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.time.LocalDateTime;

/**
 * Processes a custom format to CSV values.
 * The custom formatted file is a completely arbitrarily chosen format for demo purposes.
 * <p>
 * This is a very dirty csv implementation, and only for demo purposes!
 *
 * @author Ivo Woltring
 */
@Slf4j
public class CustomFormatToCsvProcessor implements Processor {

    @Override
    public void process(final Exchange exchange) throws Exception {
        final String body = exchange.getIn().getBody(String.class);
        final String[] lines = body.split("\\n");
        assert lines.length > 3;
        final StringBuilder csv = new StringBuilder();
        for (int i = 3; i < lines.length; i++) {
            final String line = lines[i];
            final String orderId = processOrderId(line);
            csv.append(String.format("\"%s\",", orderId));
            final String dateTime = processDate(line);
            csv.append(String.format("\"%s\",", dateTime));
            final Address address = processAddress(line);
            csv.append(address.toCSV());
            final Integer items = processNumberOfItems(line);
            csv.append(String.format(",\"%s\"\n", items));
        }
        exchange.getIn().setBody(csv.toString());
    }

    private Integer processNumberOfItems(final String input) {
        return Integer.valueOf(input.substring(36, 40));
    }

    private Address processAddress(final String input) {
        return new Address(input.substring(26, 32), Integer.valueOf(input.substring(32, 35).trim()), input.substring(35, 36));
    }

    private String processDate(final String input) {
        final String dateStr = input.substring(10, 26);
        return LocalDateTime.of(Integer.valueOf(dateStr.substring(0, 4)), Integer.valueOf(dateStr.substring(4, 6)), Integer.valueOf(dateStr.substring(6, 8)), Integer.valueOf(dateStr.substring(8, 10)), Integer.valueOf(dateStr.substring(11, 12)), Integer.valueOf(dateStr.substring(14, 16))).toString();
    }

    private String processOrderId(final String input) {
        return input.substring(0, 10);
    }
}
