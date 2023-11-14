package christmas.controller.validator

import christmas.domain.type.ErrorType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DateInputValidatorTest {

    @Test
    fun `숫자 문자열이 아니면 에러`() {
        assertThrows<IllegalArgumentException>(ErrorType.DATE.toString()) {
            DateInputValidator().validate("a")
        }
    }
}