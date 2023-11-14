package christmas.domain.receipt;


import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.badge.EventBadge;
import christmas.domain.date.Date;
import christmas.domain.order.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Receipt 는")
class ReceiptTest {

    @Test
    void Date_와_Order_를_통해_생성된다() {
        // given
        Date date = Date.valueOf(3);
        Order order = Order.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));

        // when
        Receipt receipt = Receipt.of(date, order);

        // then
        assertThat(receipt).isInstanceOf(Receipt.class);
    }

    @Test
    void 할인_전_총_금액을_계산할_수_있다() {
        // given
        Date date = Date.valueOf(3);
        Order order = Order.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));

        Receipt receipt = Receipt.of(date, order);

        // when
        int totalOrderPrice = receipt.calculateTotalOrderPrice();

        // then
        assertThat(totalOrderPrice).isEqualTo(142000);
    }

    @Test
    void 총_혜택_금액을_계산할_수_있다() {
        // given
        Date date = Date.valueOf(3);
        Order order = Order.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));

        Receipt receipt = Receipt.of(date, order);

        // when
        int totalBenefitAmount = receipt.calculateTotalBenefitAmount();

        // then
        assertThat(totalBenefitAmount).isEqualTo(31246);
    }

    @Test
    void 예상_결제_금액을_계산할_수_있다() {
        // given
        Date date = Date.valueOf(3);
        Order order = Order.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));

        Receipt receipt = Receipt.of(date, order);

        // when
        int expectedPayAmount = receipt.calculateExpectedPayAmount();

        // then
        assertThat(expectedPayAmount).isEqualTo(135754);
    }

    @Test
    void 이벤트_배지를_받을_수_있다() {
        // given
        Date date = Date.valueOf(3);
        Order order = Order.of(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));

        Receipt receipt = Receipt.of(date, order);

        // when
        EventBadge eventBadge = receipt.getEventBadge();

        // then
        assertThat(eventBadge).isEqualTo(EventBadge .SANTA);
    }
}