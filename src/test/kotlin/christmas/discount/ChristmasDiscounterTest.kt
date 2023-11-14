package christmas.discount

import christmas.domain.model.Date
import christmas.domain.model.discount.ChristmasDiscounter
import christmas.domain.model.discount.Discount
import christmas.domain.model.discount.type.DiscountType
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

internal class ChristmasDiscounterTest {
    @Test
    fun `1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가`() {
        // given
        val firstDay = 1
        val endDay = 25
        val firstDayDiscounter = ChristmasDiscounter(Date(firstDay), firstDay)
        val endDayDiscounter = ChristmasDiscounter(Date(endDay), endDay)
        // when
        val firstDayDiscount = firstDayDiscounter.discount()
        val endDayDiscount = endDayDiscounter.discount()
        // then
        assertThat(firstDayDiscount).isEqualTo(Discount(1000, DiscountType.CHRISTMAS))
        assertThat(endDayDiscount).isEqualTo(Discount(3400, DiscountType.CHRISTMAS))

    }

    @Test
    fun `25일 지나면 0 원 할인`() {
        // given
        val afterChristmasDay = 26
        val discounter = ChristmasDiscounter(Date(afterChristmasDay), afterChristmasDay)
        // when
        val noneDiscount = discounter.discount()
        // then
        assertThat(noneDiscount).isEqualTo(Discount(0, DiscountType.CHRISTMAS))
    }
}