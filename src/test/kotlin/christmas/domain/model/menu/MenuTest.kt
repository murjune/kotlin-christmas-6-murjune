package christmas.domain.model.menu

import christmas.domain.model.order.Order
import christmas.domain.type.MealType
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MenuTest {

    @Test
    fun `count 만큼 price 를 계산한다`() {
        // given
        val menu = Menu("고기", 1000, MealType.MAIN)
        val count = 2
        // when
        val actualPrice = menu.calculatePriceFor(2)
        // then
        assertThat(actualPrice).isEqualTo(2000)
    }

    @Test
    fun `menu 이름이 같은지 확인`() {
        // given
        val menu = Menu("고기", 1000, MealType.MAIN)
        // when
        val actual = menu.isName("고기")
        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `메뉴가 특정 음식 종류인지 검사`() {
        // given
        val menu = Menu("고기", 1000, MealType.MAIN)
        // when
        val actual = menu.isType(MealType.MAIN)
        // then
        assertThat(actual).isTrue()
    }
}