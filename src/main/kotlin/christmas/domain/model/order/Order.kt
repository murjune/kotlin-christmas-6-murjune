package christmas.domain.model.order

import christmas.domain.model.menu.Menu
import christmas.domain.type.ErrorType

data class Order(val menu: Menu, val menuCount: Int) {

    init {
        require(menuCount >= MIN_MENU_COUNT) { ErrorType.ORDER.message }
    }

    fun calculatePrice() = menu.price * menuCount
    override fun toString() = "${menu.name} ${menuCount}개"

    companion object {
        private const val MIN_MENU_COUNT = 1
    }
}