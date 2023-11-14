package christmas.domain.model.discount

import christmas.domain.model.discount.type.DiscountType

data class Discount(val amount: Int, val type: DiscountType) {

}