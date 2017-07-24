package nl.ivonet.route.eip.messaging_systems.message_translator.boundary;

import lombok.Data;

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
