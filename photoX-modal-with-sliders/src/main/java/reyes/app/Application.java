package reyes.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApplicationEndpoints.ALGORITHMS_ENDPOINT)
@ComponentScan(basePackages = "reyes.app")
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @PostMapping(ApplicationEndpoints.CMYK_ENDPOINT)
    public void cmyk(@RequestBody Cmyk cmyk){
        LOGGER.info("cmyk request: {}",cmyk);
    }

    @PostMapping(ApplicationEndpoints.HSL_ENDPOINT)
    public void hsl(@RequestBody Hsl hsl){
        LOGGER.info("hsl request: {}",hsl);
    }
}