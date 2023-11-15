package christmas.domain.model

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GiftTest {

    @Test
    fun `총 주문 금액이 12만원 이상이면 샴페인을 증정`() {
        // given
        val orderPrice = 120_000
        val nonGiftOrderPrice = 119_999
        // when
        val gift = Gift.of(orderPrice)
        val nonGift = Gift.of(nonGiftOrderPrice)
        // then
        assertThat(gift.toString()).isEqualTo("샴페인 1개")
        assertThat(nonGift.toString()).isEqualTo("없음")
    }

    @Test
    fun `증정품의 혜택을 계산`() {
        // given
        val gift = Gift.of(120_000)
        val nonGift = Gift.of(119_999)
        // when
        val profit = gift.calculateProfit()
        val nonProfit = nonGift.calculateProfit()
        // then
        assertThat(profit).isEqualTo(25_000)
        assertThat(nonProfit).isEqualTo(0)
    }
}