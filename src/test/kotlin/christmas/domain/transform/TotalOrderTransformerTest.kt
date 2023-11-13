package christmas.domain.transform

import christmas.domain.model.menu.Menu
import christmas.domain.model.menu.MenuBoard
import christmas.domain.model.order.Order
import christmas.domain.model.order.TotalOrder
import christmas.domain.type.ErrorType
import christmas.domain.type.MealType
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TotalOrderTransformerTest {

    private lateinit var transformer: TotalOrderTransformer

    @BeforeEach
    fun setUp() {
        val menuBoard = MenuBoard(
            listOf(
                Menu("고기", 1000, MealType.MAIN),
                Menu("고기", 1000, MealType.MAIN),
                Menu("고기", 1000, MealType.MAIN),
                Menu("물고기", 1000, MealType.MAIN),
                Menu("물고기", 1000, MealType.MAIN),
            )
        )
        transformer = TotalOrderTransformer(menuBoard)
    }

    @Test
    fun `문자열 리스트를 TotalOrder 객체로 변환 한다`() {
        // given
        val preOrders = listOf("물고기-2", "고기-3")
        val expectedOrder =
            TotalOrder(
                listOf(
                    Order(Menu("물고기", 1000, MealType.MAIN), 2),
                    Order(Menu("고기", 1000, MealType.MAIN), 3)
                )
            )
        // when
        val actualOrder = transformer.transform(preOrders)
        // then
        assertThat(actualOrder).isEqualTo(expectedOrder)
    }

    @Test
    fun `변환될 문자열의 menu 와 count 는 '-' 로 구분되어야 한다`() {
        // given
        val preOrders = listOf("물고기*2")
        // then
        assertThrows<IllegalArgumentException>(ErrorType.ORDER.message) {
            // when
            transformer.transform(preOrders)
        }
    }

    @Test
    fun `구분된 count는 숫자형 문자열이여야 한다`() {
        // given
        val preOrders = listOf("물고기-a")
        // then
        assertThrows<IllegalArgumentException>(ErrorType.ORDER.message) {
            // when
            transformer.transform(preOrders)
        }
    }
}
