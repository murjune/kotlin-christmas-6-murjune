package christmas.controller.error

class InputErrorHandler : ErrorHandler {
    override fun handle(
        action: () -> Unit,
        callback: () -> Unit,
        write: (message: String) -> Unit
    ) {
        try {
            return action()
        } catch (e: NoSuchElementException) {
            write(ERROR_MESSAGE_FORMAT.format(NO_LINE_ERROR))
        } catch (e: IllegalArgumentException) {
            write(ERROR_MESSAGE_FORMAT.format(e.message))
        } catch (e: IllegalStateException) {
            write(ERROR_MESSAGE_FORMAT.format(ILLEGAL_STATE_ERROR))
        }
        callback()
    }

    companion object {
        private const val ERROR_MESSAGE_FORMAT = "[ERROR] %s 다시 입력해 주세요."
        private const val NO_LINE_ERROR = "읽을 line이 없습니다"
        private const val ILLEGAL_STATE_ERROR = "잘못된 입력 양식입니다."
    }
}