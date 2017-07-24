package nl.ivonet.route.eip.messaging_systems.message_translator.using_beans;

import lombok.extern.slf4j.Slf4j;
import nl.ivonet.route.eip.messaging_systems.message_translator.using_beans.boundary.Order;
import nl.ivonet.route.eip.messaging_systems.message_translator.using_beans.boundary.OrderLine;
import nl.ivonet.route.eip.messaging_systems.message_translator.using_processor.boundary.Address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This bean transforms a specific csv to json.
 *
 * I personally do not like this method much as demonstrated by the SuppressWarnings...
 *
 * @author Ivo Woltring
 */
@Slf4j
@SuppressWarnings({"UtilityClass", "UtilityClassWithoutPrivateConstructor"})
public final class CsvToJson {

    public static String map(final String csv) {
        final String[] lines = csv.split("\\n");

        final Order order = new Order();
        for (final String line : lines) {
            final String[] items = line.split(",");
            final List<String> collect = Stream.of(items).map(i -> i.replace("\"", "")).collect(Collectors.toList());
            final OrderLine orderLine = new OrderLine();
            orderLine.setOrderId(collect.get(0));
            orderLine.setDateTime(LocalDateTime.parse(collect.get(1)));
            orderLine.setAddress(new Address(collect.get(2), Integer.valueOf(collect.get(3)), collect.get(4)));
            orderLine.setNumberOfItems(Integer.valueOf(collect.get(5)));
            log.info(orderLine.toString());
            order.add(orderLine);
        }
        return order.asJson();
    }

}
