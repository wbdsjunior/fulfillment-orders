package io.github.wbdsjunior.fulfillment.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class FileNormalizerServiceTests {

    private Set<BuyerDto> buyers;

    @BeforeEach
    void beforeEach(final TestInfo testInfo) throws IOException {

        if (!"duplicatedBuyerOrderProduct()".equalsIgnoreCase(testInfo.getDisplayName())) {

            buyers = new FileNormalizerService()
                        .normalize(new File(getClass()
                                .getClassLoader()
                                .getResource("data_1.txt")
                                .getPath()
                    )
                );
        }
    }

    @Test
    void hasBuyers() {

        assertEquals(
                  100
                , buyers.size()
            );
    }

    @Test
    void buyerHasOrders() {

        assertEquals(
                  15
                , fisrtBuyer()
                        .orders()
                        .size()
            );
    }

    @Test
    void buyerOrderHasProducts() {

        assertEquals(
                  3
                , firstBuyerOrder()
                        .products()
                        .size()
            );
    }

    @Test
    void buyerOrderTotalAmount() {

        assertEquals(
                  BigDecimal.valueOf(Double.valueOf("2966.46"))
                , firstBuyerOrder()
                        .totalAmount()
            );
    }

    @Test
    void duplicatedBuyerOrderProduct() {

        assertThrows(
                DuplicatedBuyerOrderProductException.class
                , () -> new FileNormalizerService()
                        .normalize(new File(getClass()
                                .getClassLoader()
                                .getResource("data_2.txt")
                                .getPath()))
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
