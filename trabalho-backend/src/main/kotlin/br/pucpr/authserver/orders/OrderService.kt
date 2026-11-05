package br.pucpr.authserver.orders

import br.pucpr.authserver.exception.NotFoundException
import br.pucpr.authserver.exceptions.BadRequestException
import br.pucpr.authserver.products.ProductRepository
import br.pucpr.authserver.users.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    val repository: OrderRepository,
    val userRepository: UserRepository,
    val productRepository: ProductRepository
) {
    fun insert(idUser: Long): Order {
        val user = userRepository.findByIdOrNull(idUser) ?: throw NotFoundException(idUser)
        val order = repository.save(Order(user = user))
        log.info("Order ${order.id} created by user $idUser")
        return order
    }

    fun findById(id: Long) = repository.findByIdOrNull(id) ?: throw NotFoundException(id)

    fun addProduct(id: Long, idProduct: Long, quantity: Int): Boolean {
        if (quantity <= 0) throw BadRequestException("Quantity must be positive")

        val order = findById(id)
        val product = productRepository.findByIdOrNull(idProduct) ?: throw NotFoundException(idProduct)
        val item = order.items.find { it.product.id == idProduct }
        if (item?.quantity == quantity) return false

        if (item != null) item.quantity = quantity
        else order.items.add(OrderItem(order = order, product = product, quantity = quantity))

        repository.save(order)
        log.info("Order $id now has $quantity of product $idProduct")
        return true
    }

    companion object {
        val log = LoggerFactory.getLogger(OrderService::class.java)
    }
}
