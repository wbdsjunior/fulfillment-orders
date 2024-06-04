package io.github.wbdsjunior.fulfillment.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
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
                , buyers.stream()
                        .sorted(Comparator.comparingLong(BuyerDto::id))
                        .collect(Collectors.toList())
                        .get(0)
                        .orders()
                        .size()
            );
    }

    @Test
    void firstBuyerOrderHasProducts() {

        assertEquals(
                  2
                , buyers.stream()
                        .sorted(Comparator.comparingLong(BuyerDto::id))
                        .collect(Collectors.toList())
                        .get(0)
                        .orders()
                        .stream()
                        .sorted(Comparator.comparingLong(OrderDto::id))
                        .collect(Collectors.toList())
                        .get(0)
                        .products()
                        .size()
            );
    }
}
