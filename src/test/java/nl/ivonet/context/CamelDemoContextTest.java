package nl.ivonet.context;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * @author ivonet
 */
public class CamelDemoContextTest {

    private CamelDemoContext camelDemoContext;

    @Before
    public void setUp() throws Exception {
        this.camelDemoContext = new CamelDemoContext();
    }

    @Test
    public void projectBaseLocation() throws Exception {
        assertTrue(this.camelDemoContext.projectBaseLocation().endsWith("camel-demo"));
        System.out.println("camelDemoContext.projectBaseLocation() = " + this.camelDemoContext.projectBaseLocation());
    }

}