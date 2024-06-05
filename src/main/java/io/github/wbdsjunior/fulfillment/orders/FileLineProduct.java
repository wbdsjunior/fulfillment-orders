package io.github.wbdsjunior.fulfillment.orders;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

public record FileLineProduct(
          @Min(1) long id
        , @DecimalMin("0.01") BigDecimal price
    ) {

    public static FileLineProduct from(String fileLine) {

        var id = Long.valueOf(fileLine.substring(66, 75));

        if (0 >= id) {

            throw new IllegalArgumentException("Product's ID must be positive greater than zero");
        }
        var price = BigDecimal.valueOf(Double.valueOf(fileLine.substring(76, 87)));

        if (BigDecimal.ZERO.compareTo(price) >= 0) {

            throw new IllegalArgumentException("Product's Price must be positive greater than zero");
        }
        return new FileLineProduct(
                  id
                , price
            );
    }
}
