package io.github.wbdsjunior.fulfillment.orders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyerDto(
          @JsonProperty("user_id") long id
        , String name
        , Set<OrderDto> orders
    ) { 

        public static BuyerDto from(FileLineBuyer buyer) {

            return new BuyerDto(
                      buyer.id()
                    , buyer.name()
                    , new HashSet<>(Arrays.asList(OrderDto.from(buyer.order())))
                );
        }

        public void add(FileLineOrder order) {

            try {

                orders.stream()
                        .filter(existingOrder -> existingOrder.id() == order.id())
                        .findFirst()
                        .ifPresentOrElse(
                                  orderFound -> orderFound.add(order.product())
                                , () -> orders.add(OrderDto.from(order))
                            );
            } catch (DuplicatedBuyerOrderProductException e) {

                throw new DuplicatedBuyerOrderProductException(
                          String.format(
                                  "Duplicated product in order {id=%d}"
                                , order.id()
                            )
                        , e
                    );
            }
        }
}
