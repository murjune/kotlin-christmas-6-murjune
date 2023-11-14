package christmas.domain.model.order

import christmas.domain.model.menu.Menu
import christmas.domain.type.ErrorType
import christmas.domain.type.MealType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class OrderTest {
    @Test
    fun `메뉴는 1개 이상 주문 해야한다`() {
        assertThrows<IllegalArgumentException>(ErrorType.ORDER.message) {
            Order(Menu("고기", 1000, MealType.MAIN), 0)
        }
    }

    @Test
    fun `주문 가격을 계산한다`() {
        // given
        val order = Order(Menu("고기", 1000, MealType.MAIN), 2)
        val expectedPrice = 2000
        // when
        val price = order.calculatePrice()
        // then
        assertThat(price).isEqualTo(expectedPrice)
    }
}