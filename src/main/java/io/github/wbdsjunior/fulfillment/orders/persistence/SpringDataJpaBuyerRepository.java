package io.github.wbdsjunior.fulfillment.orders.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaBuyerRepository extends JpaRepository<JpaBuyerEntity, Long> { }
