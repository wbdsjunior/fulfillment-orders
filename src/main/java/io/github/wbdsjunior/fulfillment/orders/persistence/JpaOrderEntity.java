package io.github.wbdsjunior.fulfillment.orders.persistence;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import io.github.wbdsjunior.fulfillment.orders.OrderDto;

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
@Table(name = "buyer_order")
public class JpaOrderEntity {

    public static final String SALES_DATE__FIELD_NAME = "salesDate";

    @EmbeddedId
    private JpaOrderEntity.PrimaryKey primaryKey;

    @Column(nullable = false)
    private LocalDate salesDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "primaryKey.order", orphanRemoval = true)
    private Set<JpaProductEntity> products;

    @AllArgsConstructor
    @Getter
    @EqualsAndHashCode
    @ToString
    @Embeddable
    @Access(value = AccessType.FIELD)
    public static class PrimaryKey {

        @MapsId
        @ManyToOne
        private JpaBuyerEntity buyer;

        @Id
        private long id;
    }

    public static JpaOrderEntity from(final OrderDto order) {

        return new JpaOrderEntity(
                  null
                , order.salesDate()
                , order.products()
                        .stream()
                        .map(JpaProductEntity::from)
                        .collect(Collectors.toSet())
            );
    }
}
