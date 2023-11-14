package christmas.domain.model.order

import christmas.domain.model.menu.Menu
import christmas.domain.type.ErrorType
import christmas.domain.type.MealType

data class TotalOrder(private val orders: List<Order>) {

    init {
        require(orders.size <= LIMIT_ORDER_SIZE) { ErrorType.ORDER.message }
        require(orders.size == orders.distinct().size) { ErrorType.ORDER.message }
        require(orders.all { it.menu.type == MealType.DRINK }.not()) { ErrorType.ORDER.message }
    }

    fun calculatePrice() = orders.sumOf { order -> order.calculatePrice() }

    fun provideGift(): Menu? = cachedMenu.takeIf { calculatePrice() >= GIVE_AWAY_LIMIT }

    fun isEventExecutable(): Boolean = calculatePrice() >= EVENT_LIMIT

    fun toList() = orders.flatMap { (menu, count) -> List(count) { menu } }
    override fun toString(): String {
        return StringBuilder().apply {
            orders.forEach { order ->
                append("$order$NEW_LINE")
            }
        }.toString()
    }

    companion object {
        private val cachedMenu = Menu("샴페인", 25_000, MealType.DESSERT)
        private const val GIVE_AWAY_LIMIT = 120_000
        private const val EVENT_LIMIT = 10_000
        private const val LIMIT_ORDER_SIZE = 20
        private const val NEW_LINE = "\n"
    }
}