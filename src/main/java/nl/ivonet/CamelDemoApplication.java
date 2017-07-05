package nl.ivonet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings({"UtilityClassWithoutPrivateConstructor", "UtilityClass", "NonFinalUtilityClass"})
public class CamelDemoApplication {
    public static void main(final String[] args) {
        SpringApplication.run(CamelDemoApplication.class, args);
    }
}
