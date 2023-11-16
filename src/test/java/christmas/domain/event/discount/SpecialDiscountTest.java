package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.date.Date;
import christmas.domain.order.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("SpecialDiscount 는")
class SpecialDiscountTest {

    @ParameterizedTest(name = "12월 {0}일의 특별 할인 적용 가능 여부는 {1}이다.")
    @CsvSource(value = {"3, true", "10, true", "24, true", "11, false", "22, false"})
    void 이벤트가_적용_가능한지_알_수_있다(final int dateValue, final boolean expected) {
        // given
        Date date = Date.valueOf(dateValue);
        Order order = Order.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        SpecialDiscount discount = new SpecialDiscount();

        // when
        boolean result = discount.isApplicableCore(date, order);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "12월 {0}일의 특별 할인 금액은 {1}원 이다.")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void 별이_있으면_1000원을_할인해_준다(final int dateValue) {
        // given
        Date date = Date.valueOf(dateValue);
        Order order = Order.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        SpecialDiscount discount = new SpecialDiscount();

        // when
        int result = discount.offer(date, order);

        // then
        assertThat(result).isEqualTo(1_000);
    }

    @Test
    void 별이_없는_날짜를_입력하면_예외가_발생한다() {
        // given
        Date date = Date.valueOf(11);
        Order order = Order.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        SpecialDiscount discount = new SpecialDiscount();

        // when, then
        assertThatThrownBy(() -> discount.offer(date, order))
            .isInstanceOf(IllegalArgumentException.class);
    }
}