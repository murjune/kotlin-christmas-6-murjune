package christmas.view

import christmas.domain.model.Badge
import christmas.domain.model.order.TotalOrder

class OutPutView {

    fun writeWelcome() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun writeDateGuide() {
        println("12 월 중 식당 예상 방문 날짜는 언제인가요 ?(숫자만 입력해 주세요!)")
    }

    fun writeOrderGuide() {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
    }

    fun writePromotionPreview() {
        println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")
    }

    fun writeOrderMenus(order: TotalOrder) {
        println("<주문 메뉴>")
        println(order)
    }

    fun writeTotalOrderPrice(price: String) {
        println("<할인 전 총주문 금액>")
        println(price)
    }

    fun writeGift(gift: String) {
        println("<증정 메뉴>")
        println(gift)
    }

    fun writeProfits(profits: List<String>) {
        println("<혜택 내역>")
        profits.forEach { println(it) }
    }

    fun writeSumOfProfit(profit: String) {
        println("<총혜택 금액>")
        println(profit)
    }

    fun writePaymentPreview(payment: String) {
        println("<할인 후 예상 결제 금액>")
        println(payment)
    }

    fun writeEventBadge(badge: Badge){
        println("<12월 이벤트 배지>")
        println(badge)
    }

    fun writeError(message: String) {
        println(message)
    }
}