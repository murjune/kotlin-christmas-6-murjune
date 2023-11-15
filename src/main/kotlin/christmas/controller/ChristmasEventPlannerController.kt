package christmas.controller

import christmas.controller.error.ErrorHandler
import christmas.controller.error.InputErrorHandler
import christmas.controller.format.PriceFormatter
import christmas.controller.validator.DateInputValidator
import christmas.domain.model.Badge
import christmas.domain.model.Gift
import christmas.domain.model.VisitDate
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

class ChristmasEventPlannerController {
    private val inputView = InputView()
    private val outPutView = OutPutView()
    private val errorHandler: ErrorHandler = InputErrorHandler()
    private lateinit var today: VisitDate
    private var dayOfMonth = NOT_INITIALIZE
    private val menuBoard: MenuBoard = MenuBoard(MenuService.provideMenus())
    private val orderTransformer = TotalOrderTransformer(menuBoard)
    private lateinit var totalOrder: TotalOrder
    private lateinit var orderMenus: List<Menu>
    private lateinit var gift: Gift
    private var totalPrice = NOT_INITIALIZE
    private var totalProfits = NOT_INITIALIZE
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
                today = VisitDate(dayOfMonth)
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
        gift = Gift.of(totalPrice)
        outPutView.writeGift(gift.toString())
    }

    private fun showProfitView() {
        val discounts = calculateDiscounts()
        showProfitHistory(discounts)
        showSumOfProfitView(discounts)
    }

    private fun showProfitHistory(discounts: List<Discount>) {
        if (totalOrder.isEventExecutable().not()) return outPutView.writeNoProfit()
        val discountProfits = discounts.map { it.toString() }
        val giftProfits = gift.calculateProfit()
        if (giftProfits > 0) {
            return outPutView.writeProfitHistory(discountProfits + giftProfits.toGiftFormat())
        }
        outPutView.writeProfitHistory(discountProfits)
    }

    private fun showSumOfProfitView(discounts: List<Discount>) {
        if (totalOrder.isEventExecutable().not()) return outPutView.writeSumOfProfit(0.toPriceFormat())
        val discountProfits = Discount.sumOfProfit(discounts)
        val giftProfit = gift.calculateProfit()
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

    private fun calculateDiscounts(): List<Discount> {
        return mutableListOf<Discount>().apply {
            val christmasDiscount = ChristmasDiscounter(today, dayOfMonth).create()
            val dailyDiscount = DailyDiscounter(today, orderMenus).create()
            val specialDiscount = SpecialDiscounter(today).create()
            if (christmasDiscount.canApply()) add(christmasDiscount)
            if (dailyDiscount.canApply()) add(dailyDiscount)
            if (specialDiscount.canApply()) add(specialDiscount)
        }
    }

    private fun Int.toPriceFormat() = PRICE_FORMAT.format(priceFormatter.format(this))

    private fun Int.toProfitFormat() = PROFIT_FORMAT.format(priceFormatter.format(this))

    private fun Int.toGiftFormat() = GIFT_PROFIT_FORMAT.format(this)

    private companion object {
        const val NOT_INITIALIZE = 0
        const val PRICE_FORMAT = "%s원"
        const val PROFIT_FORMAT = "-%s원"
        const val GIFT_PROFIT_FORMAT = "증정 이벤트: -%d원"
    }
}