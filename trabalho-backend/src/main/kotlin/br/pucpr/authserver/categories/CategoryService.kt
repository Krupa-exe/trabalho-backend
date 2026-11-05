package br.pucpr.authserver.categories

import br.pucpr.authserver.exceptions.BadRequestException
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CategoryService(val repository: CategoryRepository) {
    fun insert(category: Category): Category {
        if (repository.findByName(category.name) != null) {
            throw BadRequestException("Category ${category.name} already exists.")
        }
        val saved = repository.save(category)
        log.info("Category ${saved.id} created successfully")
        return saved
    }

    fun findAll() = repository.findAll(Sort.by("name").ascending())

    companion object {
        val log = LoggerFactory.getLogger(CategoryService::class.java)
    }
}
