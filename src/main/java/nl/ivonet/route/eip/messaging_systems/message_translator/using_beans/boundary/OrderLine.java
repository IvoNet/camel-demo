package nl.ivonet.route.eip.messaging_systems.message_translator.using_beans.boundary;

import lombok.Data;
import nl.ivonet.route.eip.messaging_systems.message_translator.using_processor.boundary.Address;

import java.time.LocalDateTime;

/**
 * @author Ivo Woltring
 */
@Data
public class OrderLine {
    private String orderId;
    private LocalDateTime dateTime;
    private Address address;
    private Integer numberOfItems;
}
