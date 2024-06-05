package io.github.wbdsjunior.fulfillment.orders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderDto(
          @JsonProperty("order_id") long id
        , @JsonProperty("date") LocalDate salesDate
        , Set<ProductDto> products
    ) {

    @JsonProperty("total")
    public BigDecimal totalAmount() {

        return products.stream()
                .map(ProductDto::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static OrderDto from(final FileLineOrder order) {

        return new OrderDto(
                  order.id()
                , order.salesDate()
                , new HashSet<>(Arrays.asList(ProductDto.from(order.product())))
            );
    }

    public void add(FileLineProduct product) {

        products.stream()
                .filter(existingProduct -> existingProduct.id() == product.id())
                .findFirst()
                .ifPresentOrElse(
                          productFound -> {
                                throw new DuplicatedBuyerOrderProductException(String.format(
                                          "Duplicated product={id=%d,price=%f}"
                                        , product.id()
                                        , product.price().doubleValue()
                                    ));
                            }
                        , () -> products.add(ProductDto.from(product))
                    );
    }
}
