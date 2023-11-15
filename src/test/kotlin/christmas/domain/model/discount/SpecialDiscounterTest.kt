package christmas.domain.model.discount

import christmas.domain.model.VisitDate
import christmas.domain.model.discount.type.DiscountType
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class SpecialDiscounterTest {

    @Test
    fun `특별한 날 전체 금액 에서 1,000원 할인`() {
        // given
        val specialDays = listOf(1, 2)
        val specialDate = VisitDate(1, specialDays)
        val nonSpecialDate = VisitDate(3, specialDays)
        // when
        val specialDiscount = SpecialDiscounter(specialDate).discount()
        val nonDiscount = SpecialDiscounter(nonSpecialDate).discount()
        // then
        assertThat(specialDiscount).isEqualTo(Discount(1000, DiscountType.SPECIAL))
        assertThat(nonDiscount).isEqualTo(Discount(0, DiscountType.SPECIAL))
    }
}