package br.pucpr.authserver.orders.responses

import br.pucpr.authserver.orders.Order

data class OrderResponse(
    val id: Long,
    val idUser: Long,
    val items: List<OrderItemResponse>,
) {
    constructor(order: Order) : this(
        order.id!!,
        order.user.id!!,
        order.items.map { OrderItemResponse(it) }
    )
}
