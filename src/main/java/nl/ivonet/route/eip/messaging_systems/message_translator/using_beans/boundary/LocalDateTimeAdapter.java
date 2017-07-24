package nl.ivonet.route.eip.messaging_systems.message_translator.using_beans.boundary;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

/**
 * Adaptor to get the XML date string to a Java 8 LocalDateTime object.
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(final String inputDate) throws Exception {
        return isNull(inputDate) ? null : LocalDateTime.parse(inputDate);
    }

    @Override
    public String marshal(final LocalDateTime inputDate) throws Exception {
        return isNull(inputDate) ? null : inputDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}