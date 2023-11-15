package christmas.domain.model

import christmas.domain.model.menu.Menu
import christmas.domain.model.menu.type.MealType

class Gift private constructor(private val menu: Menu?, private val count: Int) {

    fun calculateProfit(): Int = menu?.calculatePriceFor(count) ?: 0

    override fun toString(): String {
        if (count > 0) return "$menu ${count}개"
        return NO_GIFT
    }

    companion object {
        private val cachedMenu = Menu("샴페인", 25_000, MealType.DRINK)
        private const val MIN_ORDER_PRICE = 120_000
        private const val NO_GIFT = "없음"

        fun of(orderPrice: Int): Gift {
            if (orderPrice >= MIN_ORDER_PRICE) return Gift(cachedMenu, 1)
            return Gift(null, 0)
        }
    }
}