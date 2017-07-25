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
 * Because this Bean only has one method you do not have to specify which method is needed for the Translation.
 * so in the route you can do something like "...bean(new CsvToJson())..." and it will find the one method.
 *
 * This not necessarily good practice because it makes maintaining the code more difficult
 * and something might fail if you add methods at a later date. In general I would advice to always use the method reference
 * of the bean in the route.
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
