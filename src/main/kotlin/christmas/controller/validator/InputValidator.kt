package christmas.controller.validator

fun interface InputValidator {

    @Throws(IllegalArgumentException::class)
    fun validate(input: String)
}