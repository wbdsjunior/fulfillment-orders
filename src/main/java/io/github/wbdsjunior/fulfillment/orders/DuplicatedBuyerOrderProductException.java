package io.github.wbdsjunior.fulfillment.orders;

public class DuplicatedBuyerOrderProductException extends IllegalArgumentException {

    private static final long serialVersionUID = 980792751271151027L;

    public DuplicatedBuyerOrderProductException() { }

    public DuplicatedBuyerOrderProductException(String message) {

        super(message);
    }

    public DuplicatedBuyerOrderProductException(String message, Throwable cause) {

        super(message, cause);
    }

    public DuplicatedBuyerOrderProductException(Throwable cause) {

        super(cause);
    }
}
