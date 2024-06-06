package io.github.wbdsjunior.fulfillment.orders.persistence;

import java.math.BigDecimal;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import io.github.wbdsjunior.fulfillment.orders.ProductDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
// @Entity
@Access(value = AccessType.FIELD)
@Table(name = "buyer_order_product")
public class JpaProductEntity {

    @EmbeddedId
    private JpaProductEntity.PrimaryKey primaryKey;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @AllArgsConstructor
    @Getter
    @EqualsAndHashCode
    @ToString
    @Embeddable
    @Access(value = AccessType.FIELD)
    public static class PrimaryKey {

        @MapsId
        @ManyToOne
        private JpaOrderEntity order;

        @Id
        private long id;
    }

    public static JpaProductEntity from(final ProductDto product) {

        return new JpaProductEntity(
                  null
                , product.price()
            );
    }
}
