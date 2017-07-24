package nl.ivonet.route.eip.messaging_systems.message_translator.using_processor.boundary;

import lombok.AllArgsConstructor;

/**
 * @author Ivo Woltring
 */
@AllArgsConstructor
public class Address {
    private String zip;
    private int houseNumber;
    private String houseNumberAddition;

    public String toCSV() {
        return String.format("\"%s\",\"%s\",\"%s\"", this.zip, this.houseNumber, this.houseNumberAddition);
    }
}
