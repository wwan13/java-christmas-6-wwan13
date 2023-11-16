package christmas.domain.badge;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("EventBadge 는")
class EventBadgeTest {

    @ParameterizedTest(name = "총 혜택 금액이 {0}원 이면 {1} 을(를) 제공된다.")
    @CsvSource(value = {"2000, ''", "7000, 별", "12000, 트리", "21000, 산타"})
    void 총_혜택_금액에_따라_배지를_제공한다(final int totalBenefitAmount, final String expected) {
        // when
        EventBadge eventBadge = EventBadge.of(totalBenefitAmount);

        // then
        assertThat(eventBadge.getName()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "총 혜택 금액이 {0}원 이면 배지 적용 여부는 {1} 이다.")
    @CsvSource(value = {"2000, false", "7000, true", "12000, true", "21000, true"})
    void 배지의_적용_여부를_알_수_있다(final int totalBenefitAmount, final boolean expected) {
        // given
        EventBadge eventBadge = EventBadge.of(totalBenefitAmount);

        // when
        boolean result = eventBadge.hasBadge();

        // then
        assertThat(result).isEqualTo(expected);
    }
}