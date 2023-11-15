package christmas.domain.model.discount

import christmas.domain.model.VisitDate
import christmas.domain.model.discount.type.DiscountType

class SpecialDiscounter(private val date: VisitDate) : Discounter {

    override fun discount(): Discount {
        if (date.isSpecialDay()) return Discount(DISCOUNT, DiscountType.SPECIAL)
        return Discount(NO_DISCOUNT, DiscountType.SPECIAL)
    }

    companion object {
        private const val DISCOUNT = 1000
        private const val NO_DISCOUNT = 0
    }
}