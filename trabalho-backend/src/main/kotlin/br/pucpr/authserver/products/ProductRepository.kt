package br.pucpr.authserver.products

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    @Query(
        """
            select p from Product p
            where p.category.id = :category
        """
    )
    fun findByCategory(category: Long, sort: Sort): List<Product>
}
