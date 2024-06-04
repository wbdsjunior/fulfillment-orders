package io.github.wbdsjunior.fulfillment.orders;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDto(
          @JsonProperty("product_id") long id
        , @JsonProperty("value") BigDecimal price
    ) {

        public static ProductDto from(final FileLineProduct product) {

            return new ProductDto(
                      product.id()
                    , product.price()
                );
        }
    }
