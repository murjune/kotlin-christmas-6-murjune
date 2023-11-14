package christmas.domain.model.discount

import christmas.domain.model.discount.type.DiscountType

data class Discount(
    private val profit: Int,
    private val type: DiscountType
) {

    fun canApply() = profit > 0

    override fun toString(): String {
        return "${type}: -${profit}원"
    }

    companion object {
        fun sumOfProfit(discounts: List<Discount>): Int {
            return discounts.sumOf { it.profit }
        }
    }
}