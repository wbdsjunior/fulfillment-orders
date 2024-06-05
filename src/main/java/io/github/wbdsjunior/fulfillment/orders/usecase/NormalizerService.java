package io.github.wbdsjunior.fulfillment.orders.usecase;

public interface NormalizerService<P, R> {

    R normalize(P p) throws Exception;
}
