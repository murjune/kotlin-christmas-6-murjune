package christmas.controller

import camp.nextstep.edu.missionutils.Console
import christmas.controller.error.ErrorHandler
import christmas.controller.error.InputErrorHandler
import christmas.controller.format.PriceFormatter
import christmas.controller.validator.DateInputValidator
import christmas.domain.model.Badge
import christmas.domain.model.Date
import christmas.domain.model.discount.ChristmasDiscounter
import christmas.domain.model.discount.DailyDiscounter
import christmas.domain.model.discount.Discount
import christmas.domain.model.discount.SpecialDiscounter
import christmas.domain.model.menu.Menu
import christmas.domain.model.menu.MenuBoard
import christmas.domain.model.order.TotalOrder
import christmas.domain.transform.TotalOrderTransformer
import christmas.service.MenuService
import christmas.view.InputView
import christmas.view.OutPutView

class ChristmasPromotionController {
    private val inputView = InputView()
    private val outPutView = OutPutView()
    private val errorHandler: ErrorHandler = InputErrorHandler()
    private lateinit var today: Date
    private var dayOfMonth = 0
    private val menuBoard: MenuBoard = MenuBoard(MenuService.provideMenus())
    private val orderTransformer = TotalOrderTransformer(menuBoard)
    private lateinit var totalOrder: TotalOrder
    private lateinit var orderMenus: List<Menu>
    private var totalPrice = 0
    private var totalProfits = 0
    private val priceFormatter = PriceFormatter()

    fun start() {
        showDateView()
        showOrderView()
        showOrderMenusView()
        showOrderPriceView()
        showGiftView()
        showProfitView()
        showPaymentView()
        showEventBadgeView()
    }

    private fun showDateView() {
        outPutView.writeWelcome()
        showDateInputView()
    }

    private fun showDateInputView() {
        errorHandler.handle(
            action = {
                outPutView.writeDateGuide()
                val day = inputView.readDate()
                DateInputValidator().validate(day)
                dayOfMonth = day.toInt()
                today = Date(dayOfMonth)
            },
            callback = { showDateInputView() },
            write = { outPutView.writeError(it) },
        )
    }

    private fun showOrderView() {
        errorHandler.handle(
            action = {
                outPutView.writeOrderGuide()
                val input = inputView.readOrder()
                totalOrder = orderTransformer.transform(input).also {
                    orderMenus = it.toMenuList()
                }
            },
            callback = { showOrderView() },
            write = { outPutView.writeError(it) },
        )
    }

    private fun showOrderMenusView() {
        outPutView.writePromotionPreview()
        outPutView.writeOrderMenus(totalOrder)
    }

    private fun showOrderPriceView() {
        totalPrice = totalOrder.calculatePrice()
        outPutView.writeOrderPrice(totalPrice.toPriceFormat())
    }

    private fun showGiftView() {
        val gift = totalOrder.provideGift()?.let {
            CHAMPAGNE
        } ?: NO_EVENT
        outPutView.writeGift(gift)
    }

    private fun showProfitView() {
        val discounts = calculateDiscounts()
        val gift = provideGift()
        showProfitHistory(discounts, gift)
        showSumOfProfitView(discounts, gift)
    }

    private fun showProfitHistory(discounts: List<Discount>, gift: String?) {
        if (totalOrder.isEventExecutable().not()) return outPutView.writeNoProfit()
        val discountProfits = discounts.map { it.toString() }
        val giftProfits = gift?.let { listOf(GIFT_PROFIT_MESSAGE) } ?: listOf()
        outPutView.writeProfitHistory(discountProfits + giftProfits)
    }

    private fun showSumOfProfitView(discounts: List<Discount>, gift: String?) {
        if (totalOrder.isEventExecutable().not()) return outPutView.writeSumOfProfit(0.toPriceFormat())
        val discountProfits = Discount.sumOfProfit(discounts)
        val giftProfit = gift?.let { GIFT_PROFIT } ?: NO_PROFIT
        totalProfits = discountProfits + giftProfit
        outPutView.writeSumOfProfit(totalProfits.toProfitFormat())
    }

    private fun showPaymentView() {
        val payment = totalPrice - totalProfits
        outPutView.writePaymentPreview(payment.toPriceFormat())
    }

    private fun showEventBadgeView() {
        val badge = Badge.find(totalProfits)
        outPutView.writeEventBadge(badge)
    }

    private fun provideGift(): String? = totalOrder.provideGift()?.let {
        GIFT_PROFIT_MESSAGE
    }

    private fun calculateDiscounts(): List<Discount> {
        return mutableListOf<Discount>().apply {
            val christmasDiscount = ChristmasDiscounter(today, dayOfMonth).discount()
            val dailyDiscount = DailyDiscounter(today, orderMenus).discount()
            val specialDiscount = SpecialDiscounter(today).discount()
            if (christmasDiscount.canApply()) add(christmasDiscount)
            if (dailyDiscount.canApply()) add(dailyDiscount)
            if (specialDiscount.canApply()) add(specialDiscount)
        }
    }

    private fun Int.toPriceFormat() = priceFormatter.format(this) + WON
    private fun Int.toProfitFormat() = MINUS + priceFormatter.format(this) + WON

    private companion object {
        const val MINUS = "-"
        const val WON = "원"
        const val CHAMPAGNE = "샴페인 1개"
        const val NO_EVENT = "없음"
        const val GIFT_PROFIT_MESSAGE = "증정 이벤트: -25,000원"
        const val GIFT_PROFIT = 25000
        const val NO_PROFIT = 0
    }
}