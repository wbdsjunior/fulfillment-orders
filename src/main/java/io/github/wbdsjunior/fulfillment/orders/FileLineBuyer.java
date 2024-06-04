package io.github.wbdsjunior.fulfillment.orders;

public record FileLineBuyer(
          long id
        , String name
        , FileLineOrder order
    ) {

    public static FileLineBuyer from(String fileLine) {

        return new FileLineBuyer(
                  Long.valueOf(fileLine.substring(0, 10)), fileLine.substring(11, 55).trim()
                , FileLineOrder.from(fileLine)
            );
    }
}
