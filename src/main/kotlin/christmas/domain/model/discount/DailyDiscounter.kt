package christmas.domain.model.discount

import christmas.domain.model.Date
import christmas.domain.model.discount.type.DiscountType
import christmas.domain.model.menu.Menu
import christmas.domain.type.MealType

class DailyDiscounter(private val date: Date, private val menus: List<Menu>) : Discounter {

    override fun discount(): Discount {
        if (date.isWeekday()) return Discount(calculateWeekDayDiscount(), DiscountType.WEEK_DAY)
        return Discount(calculateWeekendDiscount(), DiscountType.WEEKEND)
    }

    private fun calculateWeekDayDiscount(): Int {
        val dessertCount = menus.count { it.isType(MealType.DESSERT) }
        return dessertCount * DISCOUNT_UNIT
    }

    private fun calculateWeekendDiscount(): Int {
        val mainCount = menus.count { it.isType(MealType.MAIN) }
        return mainCount * DISCOUNT_UNIT
    }

    companion object {
        private const val DISCOUNT_UNIT = 2023
    }
}