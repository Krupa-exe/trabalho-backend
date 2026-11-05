package br.pucpr.authserver.products

import br.pucpr.authserver.categories.CategoryRepository
import br.pucpr.authserver.exception.NotFoundException
import br.pucpr.authserver.exceptions.BadRequestException
import br.pucpr.authserver.orders.OrderItemRepository
import br.pucpr.authserver.users.SortDir
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService(
    val repository: ProductRepository,
    val categoryRepository: CategoryRepository,
    val orderItemRepository: OrderItemRepository
) {
    fun insert(product: Product, idCategory: Long): Product {
        product.category = categoryRepository.findByIdOrNull(idCategory)
            ?: throw BadRequestException("Category $idCategory not found")
        val saved = repository.save(product)
        log.info("Product ${saved.id} created successfully")
        return saved
    }

    fun findAll(dir: SortDir = SortDir.ASC, category: Long? = null): List<Product> {
        val sort = when (dir) {
            SortDir.ASC -> Sort.by("name").ascending()
            SortDir.DESC -> Sort.by("name").descending()
        }
        return if (category != null) repository.findByCategory(category, sort)
        else repository.findAll(sort)
    }

    fun findById(id: Long) = repository.findByIdOrNull(id) ?: throw NotFoundException(id)

    fun delete(id: Long) {
        val product = findById(id)
        if (orderItemRepository.existsByProductId(id)) {
            throw BadRequestException("Cannot delete a product that is in an order")
        }
        repository.delete(product)
        log.info("Product $id deleted successfully")
    }

    companion object {
        val log = LoggerFactory.getLogger(ProductService::class.java)
    }
}
