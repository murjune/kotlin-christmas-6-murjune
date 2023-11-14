package christmas.controller.validator

import christmas.domain.type.ErrorType

class DateInputValidator : InputValidator {

    override fun validate(input: String) {
        validateIsNumeric(input)
    }

    private fun validateIsNumeric(date: String) {
        require(date.all { it.isDigit() }) {
            ErrorType.DATE
        }
    }
}