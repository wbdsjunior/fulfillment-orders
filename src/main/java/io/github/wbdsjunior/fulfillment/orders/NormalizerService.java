package io.github.wbdsjunior.fulfillment.orders;

import java.util.Set;

public interface NormalizerService<P, R extends Set<?>> {

    R normalize(P p) throws Exception;
}
