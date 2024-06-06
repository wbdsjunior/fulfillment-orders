package io.github.wbdsjunior.fulfillment.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FulfillmentOrdersApplication {

    public static void main(String[] args) {

        SpringApplication.run(FulfillmentOrdersApplication.class, args);
    }
}
