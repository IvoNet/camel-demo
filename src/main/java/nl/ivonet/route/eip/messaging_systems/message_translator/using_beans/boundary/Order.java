package nl.ivonet.route.eip.messaging_systems.message_translator.using_beans.boundary;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivo Woltring
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends JsonObject {
    private List<OrderLine> orderLines;

    public void add(final OrderLine orderLine) {
        if (this.orderLines == null) {
            this.orderLines = new ArrayList<>();
        }
        this.orderLines.add(orderLine);
    }
}

