package io.github.wbdsjunior.fulfillment.orders.persistence;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository
public interface SpringDataJpaOrderRepository { // extends JpaRepository<JpaOrderEntity, JpaOrderEntity.PrimaryKey> {

    static Specification<JpaOrderEntity> salesDateStartsOn(LocalDate startDate) {

        // TODO: validate parameters
        return (root, query, builder) -> builder.greaterThanOrEqualTo(
                  root.get(JpaOrderEntity.SALES_DATE__FIELD_NAME)
                , startDate
            );
    }

    static Specification<JpaOrderEntity> salesDateBetween(
              LocalDate startDate
            , LocalDate endDate
        ) {

        // TODO: validate parameters
        return (root, query, builder) -> builder.between(
                  root.get(JpaOrderEntity.SALES_DATE__FIELD_NAME)
                , startDate
                , endDate
            );
    }

    static Specification<JpaOrderEntity> salesDateEndsOn(LocalDate endDate) {

        // TODO: validate parameters
        return (root, query, builder) -> builder.lessThanOrEqualTo(
                  root.get(JpaOrderEntity.SALES_DATE__FIELD_NAME)
                , endDate
            );
    }
}
