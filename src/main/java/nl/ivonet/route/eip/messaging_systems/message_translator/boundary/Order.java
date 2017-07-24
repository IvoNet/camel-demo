package nl.ivonet.route.eip.messaging_systems.message_translator.boundary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * I'm an Order and I have {@link OrderLine}s
 *
 * @author Ivo Woltring
 */
@Data
@XmlRootElement
public class Order {
    private List<OrderLine> orderLine;

    public void add(final OrderLine orderLine) {
        if (this.orderLine == null) {
            this.orderLine = new ArrayList<>();
        }
        this.orderLine.add(orderLine);
    }

    public String asJson() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            return mapper.writeValueAsString(this);
        } catch (final JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
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

