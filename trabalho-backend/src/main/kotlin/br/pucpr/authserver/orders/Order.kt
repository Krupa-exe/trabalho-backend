package br.pucpr.authserver.orders

import br.pucpr.authserver.users.User
import jakarta.persistence.*

@Entity
@Table(name = "OrderTable")
class Order(
    @Id @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    var user: User,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    var items: MutableSet<OrderItem> = mutableSetOf()
)
