package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.date.Date;
import christmas.domain.order.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("WeekDiscount 중")
class WeekDiscountTest {

    @Nested
    @DisplayName("WeekdayDiscount 는")
    class WeekdayDiscountTest {

        @ParameterizedTest(name = "12월 {0}일의 평일 할인 적용 가능 여부는 {1}이다.")
        @CsvSource(value = {"1, false", "3, true", "10, true", "15, false", "31, true"})
        void 이벤트가_적용_가능한지_알_수_있다(final int dateValue, final boolean expected) {
            // given
            Date date = Date.valueOf(dateValue);
            Order order = Order.of(List.of("티본스테이크-1", "바비큐립-2", "초코케이크-2", "제로콜라-1"));

            WeekDiscount discount = WeekDiscount.WEEKDAY_DISCOUNT;

            // when
            boolean result = discount.isApplicableCore(date, order);

            // then
            assertThat(result).isEqualTo(expected);
        }

        @Test
        void 디저트_메뉴_1개당_2023_원을_할인해_준다() {
            // given
            Date date = Date.valueOf(3);
            Order order = Order.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));

            WeekDiscount discount = WeekDiscount.WEEKDAY_DISCOUNT;

            // when
            int result = discount.offer(date, order);

            // then
            assertThat(result).isEqualTo(2023 * 2);
        }

        @Test
        void 주말을_입력하면_예외가_발생한다() {
            // given
            Date date = Date.valueOf(1);
            Order order = Order.of(List.of("티본스테이크-1", "바비큐립-2", "초코케이크-2", "제로콜라-1"));

            WeekDiscount discount = WeekDiscount.WEEKDAY_DISCOUNT;

            // when, then
            assertThatThrownBy(() -> discount.offer(date, order))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("WeekendDiscount 는")
    class WeekendDiscountTest {

        @ParameterizedTest(name = "12월 {0}일의 주말 할인 적용 가능 여부는 {1}이다.")
        @CsvSource(value = {"1, true", "3, false", "10, false", "15, true", "31, false"})
        void 이벤트가_적용_가능한지_알_수_있다(final int dateValue, final boolean expected) {
            // given
            Date date = Date.valueOf(dateValue);
            Order order = Order.of(List.of("티본스테이크-1", "바비큐립-2", "초코케이크-2", "제로콜라-1"));

            WeekDiscount discount = WeekDiscount.WEEKEND_DISCOUNT;

            // when
            boolean result = discount.isApplicableCore(date, order);

            // then
            assertThat(result).isEqualTo(expected);
        }

        @Test
        void 메인_메뉴_1개당_2023_원을_할인해_준다() {
            // given
            Date date = Date.valueOf(1);
            Order order = Order.of(List.of("티본스테이크-1", "바비큐립-2", "초코케이크-2", "제로콜라-1"));

            WeekDiscount discount = WeekDiscount.WEEKEND_DISCOUNT;

            // when
            int result = discount.offer(date, order);

            // then
            assertThat(result).isEqualTo(2023 * 3);
        }

        @Test
        void 평일을_입력하면_예외가_발생한다() {
            // given
            Date date = Date.valueOf(3);
            Order order = Order.of(List.of("티본스테이크-1", "바비큐립-2", "초코케이크-2", "제로콜라-1"));

            WeekDiscount discount = WeekDiscount.WEEKEND_DISCOUNT;

            // when, then
            assertThatThrownBy(() -> discount.offer(date, order))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}