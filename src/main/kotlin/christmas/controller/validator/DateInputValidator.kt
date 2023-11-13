package christmas.controller.validator

import christmas.domain.type.ErrorType

class DateInputValidator : InputValidator {

    override fun validate(input: String) {
        validateIsNumeric(input)
        validateIsInRange(input)
    }

    private fun validateIsNumeric(date: String) {
        require(date.all { it.isDigit() }) {
            ErrorType.DATE
        }
    }

    private fun validateIsInRange(date: String) {
        require(date.toInt() in RANGE) {
            ErrorType.DATE
        }
    }

    companion object {
        private const val START_DAY = 1
        private const val END_DAY = 31
        private val RANGE = START_DAY..END_DAY
    }
}