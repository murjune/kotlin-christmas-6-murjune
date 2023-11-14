package christmas.controller.format

class PriceFormatter {

    /**
     * ex) 1000 -> 1,000 으로 변환시키는 함수!
     *
     * 1) 입력받은 숫자를 문자열로 변환한다. (ex. 1000 -> "1000")
     * 2) 문자열을 뒤집는다 (ex. "1000" -> "0001")
     * 3) 뒤집은 문자열을 세 자리마다 쉼표를 추가한다. (ex. "0001" -> "000,1")
     *      3-1) 첫 번째 자리는 쉼표를 추가하지 않는다.
     *      3-2) 뒤에서부터 세어서 세 번째 자리마다 쉼표를 추가한다.
     * 3) 다시 문자열을 뒤집어서 반환한다.
     * */
    fun format(target: Int): String {
        val price = target.toString()
        val formattedPrice = StringBuilder().apply {
            price.indices.reversed().forEach {
                append(price[it])
                if (canAddComma(it, price.length)) append(COMMA)
            }
        }
        return formattedPrice.reverse().toString()
    }

    /**
     * 세 자리마다 쉼표를 추가해도 되는지 확인
     *
     * 1) 첫 번째 자리는 쉼표를 추가하지 않는다.
     * 2) 뒤에서부터 세어서 세 번째 자리마다 쉼표를 추가한다.
     *
     * ex) 10000 에 쉼표를 추가해야 하는 경우
     *  reversed numbers  : 0 0 0 0 1
     *  index             : 4 3 2 1 0
     *  length - 1 - index: 0 1 2 3 4 <- 뒤에서부터 세어서 몇 번째 자리인지 확인
     *  (length - 1 - index) % 3 == 2 : 세 번째 자리인지 확인
     *  맞으면 true, 아니면 false
     */
    private fun canAddComma(index: Int, length: Int): Boolean {
        val isNotFirstPosition = index != FIRST_POSITION
        val isThirdPosition = (length - 1 - index) % 3 == 2
        return isNotFirstPosition && isThirdPosition
    }


    private companion object {
        private const val COMMA = ","
        private const val FIRST_POSITION = 0
    }
}
