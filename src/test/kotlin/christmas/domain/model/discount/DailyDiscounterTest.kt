package christmas.domain.model.discount

import christmas.domain.model.VisitDate
import christmas.domain.model.discount.type.DiscountType
import christmas.domain.model.menu.Menu
import christmas.domain.model.menu.type.MealType
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

internal class DailyDiscounterTest {

    @Test
    fun `평일은 디저트 메뉴 개당 2,023원 할인`() {
        // given
        val weekDate = VisitDate(3)
        val discounter =
            DailyDiscounter(
                weekDate, listOf(
                    Menu("코코아", 3000, MealType.DESSERT),
                    Menu("고기", 3000, MealType.MAIN),
                    Menu("콜라", 3000, MealType.DRINK),
                    Menu("모닝빵", 3000, MealType.APPETIZER),
                )
            )
        // when
        val discount = discounter.create()
        // then
        assertThat(discount).isEqualTo(Discount(2023, DiscountType.WEEK_DAY))
    }

    @Test
    fun `주말은 메인 메뉴 개당 2,023원 할인`() {
        // given
        val weekendDate = VisitDate(1)
        val discounter =
            DailyDiscounter(
                weekendDate, listOf(
                    Menu("코코아", 3000, MealType.DESSERT),
                    Menu("고기", 3000, MealType.MAIN),
                    Menu("콜라", 3000, MealType.DRINK),
                    Menu("모닝빵", 3000, MealType.APPETIZER),
                )
            )
        // when
        val discount = discounter.create()
        // then
        assertThat(discount).isEqualTo(Discount(2023, DiscountType.WEEKEND))
    }
}