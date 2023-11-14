package christmas.controller

import camp.nextstep.edu.missionutils.Console
import christmas.controller.error.ErrorHandler
import christmas.controller.error.InputErrorHandler
import christmas.controller.validator.DateInputValidator
import christmas.domain.model.Date
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

    fun start() {
        showDateView()
        showOrderView()
        showOrderMenusView()
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
                val input = Console.readLine().split(COMMA)
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

    private companion object {
        const val COMMA = ","
    }
}

fun main() {
    ChristmasPromotionController().start()
}