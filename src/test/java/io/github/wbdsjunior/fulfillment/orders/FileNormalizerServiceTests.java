package io.github.wbdsjunior.fulfillment.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileNormalizerServiceTests {

    private Set<BuyerDto> buyers;

    @BeforeEach
    void beforeEach() throws IOException {

        buyers = new FileNormalizerService()
                    .normalize(new File(getClass()
                    .getClassLoader()
                    .getResource("data_1.txt")
                    .getPath()
                )
            );
    }

    @Test
    void hasBuyers() {

        assertEquals(
                  100
                , buyers.size()
            );
    }

    @Test
    void firstBuyerHasOrders() {

        assertEquals(
                  15
                , fisrtBuyer()
                        .orders()
                        .size()
            );
    }

    private BuyerDto fisrtBuyer() {
        return sortedBuyers()
                .get(0);
    }

    private List<BuyerDto> sortedBuyers() {
        return buyers.stream()
                .sorted(Comparator.comparingLong(BuyerDto::id))
                .collect(Collectors.toList());
    }

    @Test
    void firstBuyerOrderHasProducts() {

        assertEquals(
                  2
                , firstBuyerOrder()
                        .products()
                        .size()
            );
    }

    @Test
    void firstBuyerOrderTotalAmount() {

        assertEquals(
                  BigDecimal.valueOf(Double.valueOf("2365.03"))
                , firstBuyerOrder()
                        .totalAmount()
            );
    }

    private OrderDto firstBuyerOrder() {
        return sortedBuyerOrders()
                .get(0);
    }

    private List<OrderDto> sortedBuyerOrders() {
        return fisrtBuyer()
                .orders()
                .stream()
                .sorted(Comparator.comparingLong(OrderDto::id))
                .collect(Collectors.toList());
    }
}
