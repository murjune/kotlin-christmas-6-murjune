package christmas.domain.model.discount

import christmas.domain.model.VisitDate
import christmas.domain.model.discount.type.DiscountType


class ChristmasDiscounter(private val date: VisitDate, private val dayOfMonth: Int) :
    Discounter {

    override fun discount(): Discount {
        if (date.isAfter(CHRISTMAS_DAY)) return Discount(NO_DISCOUNT, DiscountType.CHRISTMAS)
        return Discount(calculateDiscount(), DiscountType.CHRISTMAS)
    }

    private fun calculateDiscount() = DEFAULT_DISCOUNT + DISCOUNT_UNIT * (dayOfMonth - 1)

    companion object {
        private const val CHRISTMAS_DAY = 25
        private const val DEFAULT_DISCOUNT = 1000
        private const val DISCOUNT_UNIT = 100
        private const val NO_DISCOUNT = 0
    }
}