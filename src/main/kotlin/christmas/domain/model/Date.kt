package christmas.domain.model

data class Date(private val dayOfMonth: Int) {
    init {
        require(dayOfMonth in DAY_RANGE)
    }

    private val specialDays = listOf(3, 10, 17, 24, 25, 31)

    fun isWeekday() = (dayOfMonth % MOD_UNIT) in WEEKDAY_MOD_RANGE

    fun isSpecial() = dayOfMonth in specialDays

    fun isBefore(dayOfMonth: Int) = this.dayOfMonth < dayOfMonth

    fun isAfter(day: Int) = this.dayOfMonth > dayOfMonth

    companion object {
        private const val START_DAY = 1
        private const val END_DAY = 31
        private val DAY_RANGE = START_DAY..END_DAY
        private const val WEEKDAY_START_MOD = 3
        private const val WEEKDAY_END_MOD = 7
        private val WEEKDAY_MOD_RANGE = WEEKDAY_START_MOD..WEEKDAY_END_MOD
        private const val MOD_UNIT = 7
    }
}
