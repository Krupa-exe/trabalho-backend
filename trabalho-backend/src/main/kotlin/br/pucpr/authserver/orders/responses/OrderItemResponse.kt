package br.pucpr.authserver.orders.responses

import br.pucpr.authserver.orders.OrderItem

data class OrderItemResponse(
    val idProduct: Long,
    val name: String,
    val quantity: Int,
) {
    constructor(item: OrderItem) : this(item.product.id!!, item.product.name, item.quantity)
}
