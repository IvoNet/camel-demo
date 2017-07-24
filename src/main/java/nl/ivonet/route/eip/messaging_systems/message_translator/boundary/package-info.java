/**
 * @author Ivo Woltring
 */
@XmlJavaTypeAdapters(value = {
        @XmlJavaTypeAdapter(type = LocalDateTime.class, value = LocalDateTimeAdapter.class)
})
package nl.ivonet.route.eip.messaging_systems.message_translator.boundary;

import nl.ivonet.route.eip.messaging_systems.message_translator.using_transform.LocalDateTimeAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.time.LocalDateTime;