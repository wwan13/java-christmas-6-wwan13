package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.date.Date;
import christmas.domain.order.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ChristmasDayDiscount 는")
class ChristmasDayDiscountTest {

    @ParameterizedTest(name = "12월 {0}일의 크리스마스 디데이 할인 적용 가능 여부는 {1}이다.")
    @CsvSource(value = {"1, true", "10, true", "25, true", "30, false"})
    void 이벤트가_적용_가능한지_알_수_있다(final int dateValue, final boolean expected) {
        // given
        Date date = Date.valueOf(dateValue);
        ChristmasDayDiscount discount = new ChristmasDayDiscount();

        // when
        boolean result = discount.isApplicable(date);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "12월 {0}일의 크리스마스 디데이 할인 금액은 {1}원 이다.")
    @CsvSource(value = {"1, 1_000", "10, 1_900", "25, 3_400"})
    void 할인_금액을_계산할_수_있다(final int dateValue, final int expected) {
        // given
        Date date = Date.valueOf(dateValue);
        Order order = Order.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        ChristmasDayDiscount discount = new ChristmasDayDiscount();

        // when
        int result = discount.offer(date, order);

        // then
        assertThat(result).isEqualTo(expected);
    }
}