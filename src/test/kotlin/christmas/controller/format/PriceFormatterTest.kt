package christmas.controller.format

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PriceFormatterTest {

    @ParameterizedTest
    @CsvSource(value = ["1000:1,000", "10000:10,000", "100000:100,000", "1000000:1,000,000"], delimiter = ':')
    fun `가격에 세 자리마다 , 를 추가한다`(target: Int, expected: String) {
        // given
        val priceFormatter = PriceFormatter()
        // when
        val actual = priceFormatter.format(target)
        // then
        assertThat(actual).isEqualTo(expected)
    }
}