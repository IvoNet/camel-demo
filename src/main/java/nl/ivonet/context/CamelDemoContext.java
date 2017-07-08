package nl.ivonet.context;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Ivo Woltring
 */
@Component
public class CamelDemoContext {

    /**
     * To determine what the location of this project is on your machine.
     * This way it eliminates the need of providing a property for it.
     *
     * @return base path to this project as a String
     */
    public String projectBaseLocation() {
        String abspath = new File(".").getAbsolutePath();
        abspath = abspath.substring(0, abspath.length() - 1);
        return new File(abspath).getAbsolutePath();
    }
}
