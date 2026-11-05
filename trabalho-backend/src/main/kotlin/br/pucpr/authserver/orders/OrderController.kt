package br.pucpr.authserver.orders

import br.pucpr.authserver.exception.ForbiddenException
import br.pucpr.authserver.orders.responses.OrderResponse
import br.pucpr.authserver.security.UserToken
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
@SecurityRequirement(name = "jwt-auth")
class OrderController(val service: OrderService) {
    @PostMapping
    fun insert(auth: Authentication): ResponseEntity<OrderResponse> {
        val token = auth.principal as? UserToken ?: throw ForbiddenException()
        return service.insert(token.id)
            .let { OrderResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long,
        auth: Authentication
    ): ResponseEntity<OrderResponse> {
        val token = auth.principal as? UserToken ?: throw ForbiddenException()
        val order = service.findById(id)
        if (order.user.id != token.id && !token.isAdmin) {
            throw ForbiddenException("Access is not allowed")
        }
        return ResponseEntity.ok(OrderResponse(order))
    }

    @PutMapping("/{id}/products/{idProduct}")
    fun addProduct(
        @PathVariable id: Long,
        @PathVariable idProduct: Long,
        @RequestParam quantity: Int = 1,
        auth: Authentication
    ): ResponseEntity<Void> {
        val token = auth.principal as? UserToken ?: throw ForbiddenException()
        if (service.findById(id).user.id != token.id && !token.isAdmin) {
            throw ForbiddenException("Update is not allowed")
        }
        return service.addProduct(id, idProduct, quantity)
            .let {
                if (it) ResponseEntity.ok().build()
                else ResponseEntity.noContent().build()
            }
    }
}
