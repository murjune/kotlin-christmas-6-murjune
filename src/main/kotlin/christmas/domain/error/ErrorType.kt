package christmas.domain.error

enum class ErrorType(private val message: String) {
    ORDER("유효하지 않은 주문입니다."),
    DATE("유효하지 않은 날짜입니다.");

    override fun toString(): String {
        return message
    }
}