package io.github.wbdsjunior.fulfillment.orders.controller;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.wbdsjunior.fulfillment.orders.OrderDto;


@RestController
@RequestMapping("/buyers/{buyerId:\\d+}/orders")
public class BuyerOrdersRestController {

    @GetMapping
    public ResponseEntity<Set<OrderDto>> orders(
              @PathVariable long buyerId
            , @RequestParam LocalDate startSalesDate
            , @RequestParam LocalDate endSalesDate
        ) {

        throw new IllegalStateException("Sorry! Not yet.");
    }
}
