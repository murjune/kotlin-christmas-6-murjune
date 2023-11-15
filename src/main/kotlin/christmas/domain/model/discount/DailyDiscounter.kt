package christmas.domain.model.discount

import christmas.domain.model.VisitDate
import christmas.domain.model.discount.type.DiscountType
import christmas.domain.model.menu.Menu
import christmas.domain.model.menu.type.MealType

class DailyDiscounter(private val date: VisitDate, private val menus: List<Menu>) : Discounter {

    override fun create(): Discount {
        if (date.isWeekday()) return Discount(calculateWeekDayDiscount(), DiscountType.WEEK_DAY)
        return Discount(calculateWeekendDiscount(), DiscountType.WEEKEND)
    }

    private fun calculateWeekDayDiscount(): Int {
        val dessertCount = menus.count { it.isMealType(MealType.DESSERT) }
        return dessertCount * DISCOUNT_UNIT
    }

    private fun calculateWeekendDiscount(): Int {
        val mainCount = menus.count { it.isMealType(MealType.MAIN) }
        return mainCount * DISCOUNT_UNIT
    }

    companion object {
        private const val DISCOUNT_UNIT = 2023
    }
}