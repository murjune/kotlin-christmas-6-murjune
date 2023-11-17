package christmas.domain.model.discount

fun interface Discounter {
    fun create(): Discount
}