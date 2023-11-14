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
    void 총_주문_금액이_120000원을_넘으면_샴페인_1_개를_증정한다() {
        // given
        int totalOrderPrice = 130_000;

        // when
        PresentEvent presentEvent = PresentEvent.apply(totalOrderPrice);

        // then
        assertThat(presentEvent.getPresentedMenuName()).isEqualTo("샴페인");
        assertThat(presentEvent.getPresentAmount()).isEqualTo(1);
    }

    @Test
    void 총_주문_금액이_120000원을_넘지_않으면_증정하지_않는다() {
        // given
        int totalOrderPrice = 100_000;

        // when
        PresentEvent presentEvent = PresentEvent.apply(totalOrderPrice);

        // then
        assertThat(presentEvent.getPresentAmount()).isEqualTo(0);
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