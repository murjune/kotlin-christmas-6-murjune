package christmas.domain.error

interface ErrorHandler {
    fun handle(action: () -> Unit, callback: () -> Unit)
}