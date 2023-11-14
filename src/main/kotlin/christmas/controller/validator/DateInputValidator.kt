package christmas.controller.validator

import christmas.domain.type.ErrorType

class DateInputValidator : InputValidator {

    override fun validate(input: String) {
        validateIsBlank(input)
        validateIsNumeric(input)
    }
    private fun validateIsBlank(date: String) {
        require(date.isNotBlank()) {
            ErrorType.DATE.toString()
        }
    }
    private fun validateIsNumeric(date: String) {
        require(date.all { it.isDigit() }) {
            ErrorType.DATE.toString()
        }
    }
}