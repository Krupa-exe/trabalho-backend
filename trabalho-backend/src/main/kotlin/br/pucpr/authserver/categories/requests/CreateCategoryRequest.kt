package br.pucpr.authserver.categories.requests

import br.pucpr.authserver.categories.Category
import jakarta.validation.constraints.NotBlank

data class CreateCategoryRequest(
    @NotBlank
    val name: String?
) {
    fun toCategory() = Category(name = name!!)
}
