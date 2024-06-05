package io.github.wbdsjunior.fulfillment.orders;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record FileLineBuyer(
          @Min(1) long id
        , @NotBlank String name
        , FileLineOrder order
    ) {

    public static FileLineBuyer from(String fileLine) {

        var id = Long.valueOf(fileLine.substring(0, 10));

        if (0 >= id) {

            throw new IllegalArgumentException("Buyer's ID must be positive greater than zero");
        }
        var name = fileLine.substring(11, 55).trim();

        if (name.isBlank()) {

            throw new IllegalArgumentException("Buyer's Name can't be blank");
        }
        return new FileLineBuyer(
                  id
                , name
                , FileLineOrder.from(fileLine)
            );
    }
}
