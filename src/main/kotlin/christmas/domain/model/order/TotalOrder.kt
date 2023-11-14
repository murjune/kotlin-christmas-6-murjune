package christmas.domain.model.order

import christmas.domain.type.ErrorType
import christmas.domain.type.MealType

data class TotalOrder(val orders: List<Order>) {

    init {
        require(orders.size <= LIMIT_ORDER_SIZE) { ErrorType.ORDER.message }
        require(orders.size == orders.distinct().size) { ErrorType.ORDER.message }
        require(orders.all { it.menu.type == MealType.DRINK }.not()) { ErrorType.ORDER.message }
    }

    fun calculatePrice() = orders.sumOf { order -> order.calculatePrice() }
    override fun toString(): String {
        return StringBuilder().apply {
            orders.forEach { order ->
                append("$order$NEW_LINE")
            }
        }.toString()
    }

    companion object {
        private const val LIMIT_ORDER_SIZE = 20
        private const val NEW_LINE = "\n"
    }
}