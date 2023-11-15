package christmas.domain.model.order

import christmas.domain.model.menu.Menu
import christmas.domain.error.ErrorType
import christmas.domain.model.menu.type.MealType

data class Order(private val menu: Menu, private val menuCount: Int) {

    init {
        require(menuCount >= MIN_MENU_COUNT) { ErrorType.ORDER.toString() }
    }

    fun calculatePrice() = menu.calculatePriceFor(menuCount)

    fun toMenuList() = List(menuCount) { menu }

    fun isMealType(type: MealType) = menu.isMealType(type)
    override fun toString() = "$menu ${menuCount}개"

    companion object {
        private const val MIN_MENU_COUNT = 1
    }
}