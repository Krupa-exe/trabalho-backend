package br.pucpr.authserver.orders

import br.pucpr.authserver.products.Product
import jakarta.persistence.*

@Entity
class OrderItem(
    @Id @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "idOrder", nullable = false)
    var order: Order,

    @ManyToOne
    @JoinColumn(name = "idProduct", nullable = false)
    var product: Product,

    @Column(nullable = false)
    var quantity: Int = 1
)
