package io.github.wbdsjunior.fulfillment.orders.persistence;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import io.github.wbdsjunior.fulfillment.orders.BuyerDto;

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
@Entity
@Access(value = AccessType.FIELD)
@Table(name = "buyer")
public class JpaBuyerEntity {

    @Id
    @Column(scale = 10)
    private long id;

    @Column(length = 45, nullable = false)
    private String name;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "primaryKey.buyer", orphanRemoval = true)
    @Transient
    private Set<JpaOrderEntity> orders;

    public static JpaBuyerEntity from(final BuyerDto buyer) {

        return new JpaBuyerEntity(
                  buyer.id()
                , buyer.name()
                , buyer.orders()
                        .stream()
                        .map(JpaOrderEntity::from)
                        .collect(Collectors.toSet())
            );
    }
}
