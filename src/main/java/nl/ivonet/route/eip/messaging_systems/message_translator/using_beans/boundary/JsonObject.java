package nl.ivonet.route.eip.messaging_systems.message_translator.using_beans.boundary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObject {

    public String asJson() {

        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (final JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
