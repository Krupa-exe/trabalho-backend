package br.pucpr.authserver.products

import br.pucpr.authserver.categories.Category
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class Product(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "idCategory", nullable = false)
    var category: Category? = null
)
