package br.pucpr.authserver.orders

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository : JpaRepository<OrderItem, Long> {
    fun existsByProductId(id: Long): Boolean
}
