package christmas.domain.model

data class Date(private val dayOfMonth: Int, private val specialDays: List<Int> = listOf(3, 10, 17, 24, 25, 31)) {
    init {
        require(dayOfMonth in DAY_RANGE)
    }

    fun isWeekday() = ((dayOfMonth + DAY_WEIGHT_UNIT) % MOD_UNIT) in WEEKDAY_RANGE

    fun isSpecial() = dayOfMonth in specialDays

    fun isBefore(dayOfMonth: Int) = this.dayOfMonth < dayOfMonth

    fun isAfter(dayOfMonth: Int) = this.dayOfMonth > dayOfMonth

    companion object {
        private const val DAY_WEIGHT_UNIT = 6
        private const val START_DAY = 1
        private const val END_DAY = 31
        private val DAY_RANGE = START_DAY..END_DAY
        private const val START_WEEKDAY = 2
        private const val END_WEEKDAY = 6
        private val WEEKDAY_RANGE = START_WEEKDAY..END_WEEKDAY
        private const val MOD_UNIT = 7
    }
}
