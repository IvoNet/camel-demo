package nl.ivonet.route.eip.messaging_systems.message_translator.using_beans;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.route.eip.messaging_systems.message_translator.boundary.Address;
import nl.ivonet.route.eip.messaging_systems.message_translator.boundary.Order;
import nl.ivonet.route.eip.messaging_systems.message_translator.boundary.OrderLine;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This bean transforms a specific csv to json.
 *
 * @author Ivo Woltring
 */
@Slf4j
@Component
@SuppressWarnings({"UtilityClass", "UtilityClassWithoutPrivateConstructor"})
public final class CsvToJson {

    /**
     * This is the static method called by the bean.
     */
    public String map(final List<List<String>> csv) {

        final Order order = new Order();
        for (final List<String> record : csv) {
            final OrderLine orderLine = new OrderLine();
            orderLine.setOrderId(record.get(0));
            orderLine.setDateTime(LocalDateTime.parse(record.get(1)));
            orderLine.setAddress(new Address(record.get(2), Integer.valueOf(record.get(3)), record.get(4)));
            orderLine.setNumberOfItems(Integer.valueOf(record.get(5)));
            log.info(orderLine.toString());
            order.add(orderLine);
        }
        return order.asJson();
    }

}
