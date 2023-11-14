package christmas.domain.model.discount.type

enum class DiscountType(val label: String) {
    CHRISTMAS("크리스마스 디데이 할인"),
    WEEK_DAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
}