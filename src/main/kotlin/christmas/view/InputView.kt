package christmas.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readDate(input: String = Console.readLine()) = input

    fun readOrder(input: String = Console.readLine()) = input.split(COMMA)

    companion object {
        const val COMMA = ","
    }
}