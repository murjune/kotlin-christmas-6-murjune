package christmas.domain.model.menu

import christmas.domain.type.MealType
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class MenuBoardTest {

    @Test
    fun `메뉴판에서 이름으로 메뉴를 찾을 수 있다`() {
        // given
        val menuName = "고기"
        val menuBoard = MenuBoard(listOf(Menu("고기", 1000, MealType.MAIN)))
        val expectedMenu = Menu("고기", 1000, MealType.MAIN)
        // when
        val actualMenu = menuBoard.findOrNull(menuName)
        // then
        assertThat(actualMenu).isEqualTo(expectedMenu)
    }

    @Test
    fun `메뉴판에 이름으로 메뉴를 찾을 수 없으면 null을 반환`() {
        // given
        val menuBoard = MenuBoard(listOf(Menu("고기", 1000, MealType.MAIN)))
        // when
        val actualMenu = menuBoard.findOrNull("물고기")
        // then
        assertThat(actualMenu).isNull()
    }
}