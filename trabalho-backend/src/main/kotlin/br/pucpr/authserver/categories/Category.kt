package br.pucpr.authserver.categories

import br.pucpr.authserver.products.Product
import jakarta.persistence.*

@Entity
class Category(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var name: String,

    @OneToMany(mappedBy = "category")
    var products: MutableSet<Product> = mutableSetOf()
)
