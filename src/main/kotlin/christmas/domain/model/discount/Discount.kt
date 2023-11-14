package christmas.domain.model.discount

import christmas.domain.model.discount.type.DiscountType

data class Discount(
    private val profit: Int,
    private val type: DiscountType
) {

    fun canApply() = profit > 0

    override fun toString(): String {
        return "${type}: -$profit"
    }

    companion object {
        fun sumOfProfit(christmas: Discount, daily: Discount, special: Discount): Int {
            return christmas.profit + daily.profit + special.profit
        }
    }
}