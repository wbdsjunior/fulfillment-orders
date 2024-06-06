package io.github.wbdsjunior.fulfillment.orders.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

// @Repository
@NoRepositoryBean
public interface OrderSpringDataJpaRepository extends JpaRepository<JpaOrderEntity, JpaOrderEntity.PrimaryKey> { }
