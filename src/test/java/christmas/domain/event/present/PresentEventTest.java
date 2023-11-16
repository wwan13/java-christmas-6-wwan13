package christmas.domain.event.present;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("PresentEvent 는")
class PresentEventTest {

    @Test
    void 증정_메뉴는_샴페인_이다() {
        // given
        int totalOrderPrice = 130_000;

        // when
        PresentEvent presentEvent = PresentEvent.apply(totalOrderPrice);

        // then
        assertThat(presentEvent.getPresentedMenuName()).isEqualTo("샴페인");
    }

    @ParameterizedTest(name = "총 주문 금액이 {0}원이면 샴페인을 {1}개 증정한다")
    @CsvSource(value = {"130000, 1", "100000, 0, 10000, 0"})
    void 총_주문_금액에_따라_증정_메뉴를_증정한다(final int totalOrderPrice, final int expected) {
        // when
        PresentEvent presentEvent = PresentEvent.apply(totalOrderPrice);

        // then
        assertThat(presentEvent.getPresentAmount()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"130_000, true", "10_000, false"})
    void 이벤트_적용_여부를_알_수_있다(final int totalOrderPrice, final boolean expected) {
        // given
        PresentEvent presentEvent = PresentEvent.apply(totalOrderPrice);

        // when
        boolean result = presentEvent.isApplied();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"130_000, 25_000", "10_000, 0"})
    void 혜택_금액을_계산할_수_있다(final int totalOrderPrice, final int expected) {
        // given
        PresentEvent presentEvent = PresentEvent.apply(totalOrderPrice);

        // when
        int result = presentEvent.calculatePresentedPrice();

        // then
        assertThat(result).isEqualTo(expected);
    }
}