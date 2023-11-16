package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.date.Date;
import christmas.domain.order.Order;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Discounts 는")
class DiscountsTest {

    @Test
    void 적용_가능한_모든_할인을_적용한_결과를_DiscountApplyResult_에_담아_알려준다() {
        // given
        Date date = Date.valueOf(10);
        Order order = Order.of(List.of("티본스테이크-1", "바비큐립-2", "초코케이크-2", "제로콜라-1"));

        // when
        DiscountsApplyResult result = Discounts.applyAll(date, order);

        // then
        assertThat(result).isInstanceOf(DiscountsApplyResult.class);
    }

    @Nested
    @DisplayName("DiscountApplyResult 는")
    class DiscountApplyResult {

        @ParameterizedTest(name = "12월 {0}일에 {1}를 주문하면 {2}원의 할인이 적용된다.")
        @MethodSource("calculateTotalAmountArgument")
        void 총_할인_금액을_계산할_수_있다(final int dateValue,
                                   final List<String> menuAmounts,
                                   final int expected) {
            // given
            Date date = Date.valueOf(dateValue);
            Order order = Order.of(menuAmounts);

            DiscountsApplyResult result = Discounts.applyAll(date, order);

            // when
            int totalDiscountAmount = result.calculateTotalDiscountAmount();

            // then
            assertThat(totalDiscountAmount).isEqualTo(expected);
        }

        static Stream<Arguments> calculateTotalAmountArgument() {
            return Stream.of(
                Arguments.of(
                    3,
                    List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"),
                    6246
                ),
                Arguments.of(
                    8,
                    List.of("티본스테이크-1", "바비큐립-2", "초코케이크-2", "제로콜라-1"),
                    7769
                ),
                Arguments.of(
                    16,
                    List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2",
                        "제로콜라-1", "타파스-2", "해산물파스타-2"),
                    10592
                )
            );
        }

        @ParameterizedTest(name = "12월 {0}일에 {1}를 주문하면 적용된 할인은 {2}이다.")
        @MethodSource("hasAppliedDiscountsArgument")
        void 적용된_할인이_있는지_알려준다(final int dateValue,
                                   final List<String> menuAmounts,
                                   final boolean expected) {
            // given
            Date date = Date.valueOf(dateValue);
            Order order = Order.of(menuAmounts);

            DiscountsApplyResult result = Discounts.applyAll(date, order);

            // when
            boolean hasAppliedDiscounts = result.hasAppliedDiscounts();

            // then
            assertThat(hasAppliedDiscounts).isEqualTo(expected);
        }

        static Stream<Arguments> hasAppliedDiscountsArgument() {
            return Stream.of(
                Arguments.of(
                    10,
                    List.of("타파스-1", "제로콜라-1"),
                    false
                ),
                Arguments.of(
                    26,
                    List.of("타파스-1", "제로콜라-1", "시저샐러드-1"),
                    false
                ),
                Arguments.of(
                    3,
                    List.of("티본스테이크-1", "바비큐립-2", "초코케이크-2", "제로콜라-1"),
                    true
                )
            );
        }
    }
}