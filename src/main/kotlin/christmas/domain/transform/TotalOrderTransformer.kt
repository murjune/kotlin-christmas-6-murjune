package christmas.domain.transform

import christmas.domain.model.order.Order
import christmas.domain.model.order.TotalOrder
import christmas.domain.error.ErrorType
import christmas.domain.model.menu.Menu
import christmas.domain.model.menu.MenuBoard

class TotalOrderTransformer(
    private val menuBoard: MenuBoard,
) {
    fun transform(from: List<String>): TotalOrder {
        val orders: List<Order> = from
            .map { it.trim().toOrder() }
        return TotalOrder(orders)
    }

    private fun String.toOrder(): Order {
        validateCanSplit(split(DASH))
        val order = split(DASH)
        val menu = provideMenu(order[0].trim())
        val count = provideMenuCount(order[1].trim())
        return Order(menu, count)
    }

    private fun validateCanSplit(order: List<String>) {
        require(order.size >= 2) { ErrorType.ORDER.toString() }
    }

    private fun provideMenu(menuName: String): Menu {
        return menuBoard.findOrNull(menuName)
            ?: throw IllegalArgumentException(ErrorType.ORDER.toString())
    }

    private fun provideMenuCount(count: String): Int {
        return count.toIntOrNull()
            ?: throw IllegalArgumentException(ErrorType.ORDER.toString())
    }

    companion object {
        private const val DASH = "-"
    }
}