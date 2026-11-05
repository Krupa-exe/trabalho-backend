package br.pucpr.authserver.products.requests

import br.pucpr.authserver.products.Product
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class CreateProductRequest(
    @NotBlank
    val name: String?,

    @NotNull
    @Positive
    val price: BigDecimal?,

    @NotNull
    val idCategory: Long?
) {
    fun toProduct() = Product(name = name!!, price = price!!)
}
