package christmas.controller.error

import christmas.domain.error.ErrorHandler

class InputErrorHandler : ErrorHandler {
    override fun handle(action: () -> Unit, callback: () -> Unit) {
        try {
            return action()
        } catch (e: IllegalArgumentException) {
            println(ERROR_MESSAGE_FORMAT.format(e.message))
        } catch (e: IllegalStateException) {
            println(ERROR_MESSAGE_FORMAT.format(ILLEGAL_STATE_ERROR))
        }
        callback()
    }

    fun handle(action: () -> Unit) {
        handle(action) {}
    }

    companion object {
        private const val ERROR_MESSAGE_FORMAT = "[ERROR] %s 다시 입력해 주세요."
        private const val NUMBER_FORMAT_ERROR = "숫자를 입력해 주세요."
        private const val ILLEGAL_STATE_ERROR = "잘못된 입력 양식입니다."
    }
}