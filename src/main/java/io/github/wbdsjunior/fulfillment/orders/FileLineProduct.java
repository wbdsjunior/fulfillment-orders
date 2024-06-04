package io.github.wbdsjunior.fulfillment.orders;

import java.math.BigDecimal;

public record FileLineProduct(
          long id
        , BigDecimal price
    ) {

    public static FileLineProduct from(String fileLine) {

        return new FileLineProduct(
                  Long.valueOf(fileLine.substring(66, 75))
                , BigDecimal.valueOf(Double.valueOf(fileLine.substring(76, 87)))
            );
    }
}
