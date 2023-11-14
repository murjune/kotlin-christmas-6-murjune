package christmas.domain.model

enum class Badge(val label: String, val price: Int) {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
    NONE("없음", 0);

    companion object {
        private const val ERROR_MESSAGE_FORMAT = "예상치 못한 값 %d가 들어왔습니다."
        fun find(totalProfit: Int): Badge {
            return when {
                totalProfit < STAR.price -> NONE
                totalProfit in STAR.price until TREE.price -> STAR
                totalProfit in TREE.price until SANTA.price -> TREE
                totalProfit >= SANTA.price -> SANTA
                else -> throw IllegalArgumentException(ERROR_MESSAGE_FORMAT.format(totalProfit))
            }
        }
    }
}