package christmas.controller.validator

import christmas.controller.error.InputErrorType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class DateInputValidatorTest {

    @Test
    fun `숫자 문자열이 아니면 에러`() {
        assertThrows<IllegalArgumentException>(InputErrorType.DATE.message) {
            DateInputValidator().validate("a")
        }
    }

    @ValueSource(strings = ["0", "32"])
    @ParameterizedTest
    fun `1 ~ 31 범위 밖 숫자 문자열은 에러`(input: String) {
        assertThrows<IllegalArgumentException>(InputErrorType.DATE.message) {
            DateInputValidator().validate(input)
        }
    }
}