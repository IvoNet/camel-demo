package nl.ivonet.route.eip.messaging_systems.message_translator.using_beans.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivo Woltring
 */
@Data
@XmlRootElement
@EqualsAndHashCode(callSuper = true)
public class Order extends JsonObject {
    private List<OrderLine> orderLines;

    public void add(final OrderLine orderLine) {
        if (this.orderLines == null) {
            this.orderLines = new ArrayList<>();
        }
        this.orderLines.add(orderLine);
    }

    public static Order fromJson(final String json) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            return mapper.readValue(json, Order.class);
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }
}

