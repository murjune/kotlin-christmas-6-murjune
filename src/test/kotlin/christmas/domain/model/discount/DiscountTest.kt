package christmas.domain.model.discount

import christmas.domain.model.discount.type.DiscountType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DiscountTest {

    @Test
    fun `할인 혜택이 0 보다 커야 할인이 적용가능`() {
        // given
        val discount = Discount(1000, DiscountType.WEEK_DAY)
        val noDiscount = Discount(0, DiscountType.WEEK_DAY)
        // when
        val actualDiscount = discount.canApply()
        val actualNoDiscount = noDiscount.canApply()
        // then
        assertThat(actualDiscount).isTrue()
        assertThat(actualNoDiscount).isFalse()
    }
}