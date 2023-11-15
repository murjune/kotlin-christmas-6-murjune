package christmas

import christmas.domain.model.VisitDate
import christmas.domain.error.ErrorType
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class DateTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 32])
    fun `1 ~ 31이 아닌 day 는 Date 객체를 만들 수 없다`(dayOfMonth: Int) {
        assertThrows<IllegalArgumentException>(ErrorType.DATE.toString()) {
            VisitDate(dayOfMonth)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["1:false", "2:false", "3:true", "4:true", "5:true", "6:true", "7:true"], delimiter = ':')
    fun `오늘이 평일인지 판단 가능하다`(dayOfMonth: Int, expected: Boolean) {
        // when
        val actual = VisitDate(dayOfMonth).isWeekday()
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `오늘이 특별 할인 받는 날인지 확인 가능`() {
        // given
        val specialDate = VisitDate(dayOfMonth = 1, specialDays = listOf(1))
        val generalDate = VisitDate(dayOfMonth = 2, specialDays = listOf(1))
        // then
        assertThat(specialDate.isSpecialDay()).isTrue()
        assertThat(generalDate.isSpecialDay()).isFalse()

    }

    @Test
    fun `특정 day 가 지났는지 판단 가능`() {
        // given
        val today = VisitDate(2)
        // then
        assertThat(today.isAfter(3)).isFalse()
        assertThat(today.isAfter(1)).isTrue()
    }
}