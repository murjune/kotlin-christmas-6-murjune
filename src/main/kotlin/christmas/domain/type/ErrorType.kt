package christmas.domain.type

enum class ErrorType(val message: String) {
    ORDER("유효하지 않은 주문입니다."),
    DATE("유효하지 않은 날짜입니다."),
}