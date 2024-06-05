package io.github.wbdsjunior.fulfillment.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
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

        if (!Arrays.asList(
                          "zeroBuyersId()"
                        , "blankBuyersName()"
                        , "zeroOrdersId()"
                        , "inTheFutureOrdersSalesDate()"
                        , "zeroProductsId()"
                        , "zeroProductsPrice()"
                        , "duplicatedBuyerOrderProduct()"
                    )
                .contains(testInfo.getDisplayName())) {

            buyers = new FileNormalizerService()
                        .normalize(new File(getClass()
                                .getClassLoader()
                                .getResource("data_1.txt")
                                .getPath()));
        }
    }

    @Test
    void hasBuyers() {

        assertEquals(
                  1
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
                  2
                , firstBuyerOrder()
                        .products()
                        .size()
            );
    }

    @Test
    void buyerOrderTotalAmount() {

        assertEquals(
                  BigDecimal.valueOf(Double.valueOf("2168.43"))
                , firstBuyerOrder()
                        .totalAmount()
            );
    }

    @Test
    void zeroBuyersId() {

        assertThrows(
                  IllegalArgumentException.class
                , () -> new FileNormalizerService()
                        .normalize(new File(getClass()
                                .getClassLoader()
                                .getResource("data_2.txt")
                                .getPath()))
                );
    }

    @Test
    void blankBuyersName() {

        assertThrows(
                  IllegalArgumentException.class
                , () -> new FileNormalizerService()
                        .normalize(new File(getClass()
                                .getClassLoader()
                                .getResource("data_3.txt")
                                .getPath()))
                );
    }

    @Test
    void zeroOrdersId() {

        assertThrows(
                  IllegalArgumentException.class
                , () -> new FileNormalizerService()
                        .normalize(new File(getClass()
                                .getClassLoader()
                                .getResource("data_4.txt")
                                .getPath()))
                );
    }

    @Test
    void inTheFutureOrdersSalesDate() {

        assertThrows(
                  IllegalArgumentException.class
                , () -> new FileNormalizerService()
                        .normalize(new File(getClass()
                                .getClassLoader()
                                .getResource("data_7.txt")
                                .getPath()))
                );
    }

    @Test
    void zeroProductsId() {

        assertThrows(
                  IllegalArgumentException.class
                , () -> new FileNormalizerService()
                        .normalize(new File(getClass()
                                .getClassLoader()
                                .getResource("data_5.txt")
                                .getPath()))
                );
    }

    @Test
    void zeroProductsPrice() {

        assertThrows(
                  IllegalArgumentException.class
                , () -> new FileNormalizerService()
                        .normalize(new File(getClass()
                                .getClassLoader()
                                .getResource("data_6.txt")
                                .getPath()))
                );
    }

    @Test
    void duplicatedBuyerOrderProduct() {

        assertThrows(
                  DuplicatedBuyerOrderProductException.class
                , () -> new FileNormalizerService()
                        .normalize(new File(getClass()
                                .getClassLoader()
                                .getResource("data_8.txt")
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
