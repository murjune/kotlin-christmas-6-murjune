package christmas.domain.model.discount

fun interface Discounter {
    fun discount(): Discount
}