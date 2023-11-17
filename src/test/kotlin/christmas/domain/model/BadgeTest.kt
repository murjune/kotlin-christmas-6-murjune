package christmas.domain.model

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class BadgeTest {
    @ParameterizedTest
    @CsvSource(
        value = ["4999:NONE", "5000:STAR", "9999:STAR", "10000:TREE", "19999:TREE", "20000:SANTA"],
        delimiter = ':'
    )
    fun `총혜택 금액에 따라 배지 증정`(totalProfit: Int, expectedBadge: Badge) {
        // when
        val actualBadge = Badge.find(totalProfit)
        // then
        assertThat(actualBadge).isEqualTo(expectedBadge)
    }
}