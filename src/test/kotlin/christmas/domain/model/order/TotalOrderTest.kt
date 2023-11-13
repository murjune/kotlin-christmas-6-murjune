package christmas.domain.model.order

import christmas.domain.model.menu.Menu
import christmas.domain.type.ErrorType
import christmas.domain.type.MealType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TotalOrderTest {
    @Test
    fun `메뉴는 한 번에 최대 20개까지만 주문 가능`() {
        assertThrows<IllegalArgumentException>(ErrorType.ORDER.message) {
            TotalOrder(
                listOf(Order(Menu("고기", 1, MealType.MAIN), 21))
            )
        }
    }

    @Test
    fun `음료만 주문할 수 없다`() {
        assertThrows<IllegalArgumentException>(ErrorType.ORDER.message) {
            TotalOrder(
                listOf(
                    Order(
                        Menu("샴페인", 1, MealType.DRINK),
                        1
                    )
                )
            )
        }
    }

    @Test
    fun `중복 음료 주문할 수 없다`() {
        assertThrows<IllegalArgumentException>(ErrorType.ORDER.message) {
            TotalOrder(
                listOf(
                    Order(
                        Menu("고기", 1, MealType.MAIN), 1
                    ),
                    Order(
                        Menu("고기", 1, MealType.MAIN), 21
                    ),
                )
            )
        }
    }
}