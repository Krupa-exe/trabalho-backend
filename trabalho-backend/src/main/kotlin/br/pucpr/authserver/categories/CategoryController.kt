package br.pucpr.authserver.categories

import br.pucpr.authserver.categories.requests.CreateCategoryRequest
import br.pucpr.authserver.categories.responses.CategoryResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(val service: CategoryService) {
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "jwt-auth")
    @PostMapping
    fun insert(
        @Valid @RequestBody category: CreateCategoryRequest
    ) = service.insert(category.toCategory())
        .let { CategoryResponse(it) }
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun list() = service.findAll()
        .map { CategoryResponse(it) }
        .let { ResponseEntity.ok(it) }
}
