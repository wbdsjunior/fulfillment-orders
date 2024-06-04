package io.github.wbdsjunior.fulfillment.orders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record FileLineOrder(
          long id
        , LocalDate salesDate
        , FileLineProduct product
    ) {

    public static FileLineOrder from(String fileLine) {

        return new FileLineOrder(
                  Long.valueOf(fileLine.substring(56, 65))
                , LocalDate.parse(fileLine.substring(87), DateTimeFormatter.ofPattern("yyyyMMdd"))
                , FileLineProduct.from(fileLine)
            );
    }
}
