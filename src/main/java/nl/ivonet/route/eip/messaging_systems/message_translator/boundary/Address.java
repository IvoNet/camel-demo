package nl.ivonet.route.eip.messaging_systems.message_translator.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * I'm an Address man can't you see :-).
 *
 * @author Ivo Woltring
 */
@Data
@AllArgsConstructor
public class Address {
    private String zip;
    private int houseNumber;
    private String houseNumberAddition;

    public String toCSV() {
        return String.format("\"%s\",\"%s\",\"%s\"", this.zip, this.houseNumber, this.houseNumberAddition);
    }
}
