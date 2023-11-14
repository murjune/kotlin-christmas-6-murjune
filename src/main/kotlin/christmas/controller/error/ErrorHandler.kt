package christmas.controller.error

interface ErrorHandler {
    fun handle(action: () -> Unit, callback: () -> Unit, write: (message: String) -> Unit)
}