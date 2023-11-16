package christmas.domain.date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Date 는")
class DateTest {

    @Test
    void 정수를_이용해_생성할_수_있다() {
        // given
        int dateValue = 12;

        // when
        Date date = Date.valueOf(dateValue);

        // then
        assertThat(date).isInstanceOf(Date.class);
    }

    @ParameterizedTest(name = "12월 {0}일은 {1}이다.")
    @CsvSource(value = {
        "1, WEEKEND", "2, WEEKEND",
        "3, WEEKDAY", "4, WEEKDAY", "5, WEEKDAY", "6, WEEKDAY", "7, WEEKDAY",
        "8, WEEKEND", "9, WEEKEND",
        "10, WEEKDAY", "11, WEEKDAY", "12, WEEKDAY", "13, WEEKDAY", "14, WEEKDAY",
        "15, WEEKEND", "16, WEEKEND",
        "17, WEEKDAY", "18, WEEKDAY", "19, WEEKDAY", "20, WEEKDAY", "21, WEEKDAY",
        "22, WEEKEND", "23, WEEKEND",
        "24, WEEKDAY", "25, WEEKDAY", "26, WEEKDAY", "27, WEEKDAY", "28, WEEKDAY",
        "29, WEEKEND", "30, WEEKEND", "31, WEEKDAY",
    })
    void 평일인지_주말인지_알_수_있다(final int dateValue, final String dateTypeName) {
        // given
        Date date = Date.valueOf(dateValue);

        // when
        DateType dateType = date.getDateType();

        // then
        assertThat(dateType.name()).isEqualTo(dateTypeName);
    }

    @DisplayName("다음과 같은 상황에 예외가 발생한다.")
    @Nested
    class DateExceptionTest {

        @ParameterizedTest(name = "12월 {0}일은 존재하지 않는다.")
        @ValueSource(ints = {0, 32, -1, 50})
        void 날짜의_범위가_1일에서_31일_사이가_아닌_경우(final int dataValue) {
            // when, then
            assertThatThrownBy(() -> Date.valueOf(dataValue))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}