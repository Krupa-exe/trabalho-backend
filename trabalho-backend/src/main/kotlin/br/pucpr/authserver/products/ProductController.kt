package br.pucpr.authserver.products

import br.pucpr.authserver.products.requests.CreateProductRequest
import br.pucpr.authserver.products.responses.ProductResponse
import br.pucpr.authserver.users.SortDir
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(val service: ProductService) {
    @GetMapping
    fun list(
        @RequestParam sortDir: String? = null,
        @RequestParam category: Long? = null
    ) = service.findAll(SortDir.find(sortDir ?: "ASC"), category)
        .map { ProductResponse(it) }
        .let { ResponseEntity.ok(it) }

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "jwt-auth")
    @PostMapping
    fun insert(
        @Valid @RequestBody product: CreateProductRequest
    ) = service.insert(product.toProduct(), product.idCategory!!)
        .let { ProductResponse(it) }
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long
    ) = service.findById(id)
        .let { ProductResponse(it) }
        .let { ResponseEntity.ok(it) }

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "jwt-auth")
    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long
    ) = service.delete(id)
}
