package io.github.wbdsjunior.fulfillment.orders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record FileLineOrder(
          @Min(1) long id
        , @NotNull @PastOrPresent LocalDate salesDate
        , FileLineProduct product
    ) {

    public static FileLineOrder from(String fileLine) {

        var id = Long.valueOf(fileLine.substring(56, 65));

        if (0 >= id) {
            throw new IllegalArgumentException("Order's ID must be positive greater than zero");
        }
        var salesDate = LocalDate.parse(
                  fileLine.substring(87)
                , DateTimeFormatter.ofPattern("yyyyMMdd")
            );

        if (salesDate.isAfter(LocalDate.now())) {

            throw new IllegalArgumentException("Order's Sales Date must be in the present or past");
        }
        return new FileLineOrder(
                  id
                , salesDate
                , FileLineProduct.from(fileLine)
            );
    }
}
