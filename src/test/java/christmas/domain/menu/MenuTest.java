package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Menu 는")
class MenuTest {

    @ParameterizedTest(name = "{0}의 이름은 {1}이다.")
    @CsvSource(value = {
        "MUSHROOM_SOUP, 양송이수프", "TAPAS, 타파스", "CAESAR_SALAD, 시저샐러드",
        "T_BONE_STREAK, 티본스테이크", "BBQ_RIBS, 바비큐립",
        "SEAFOOD_PASTA, 해산물파스타", "CHRISTMAS_PASTA, 크리스마스파스타",
        "CHOCOLATE_CAKE, 초코케이크", "ICE_CREAM, 아이스크림",
        "ZERO_COKE, 제로콜라", "RED_WINE, 레드와인", "CHAMPAGNE, 샴페인"
    })
    void 메뉴의_이름을_갖고_있다(final String menuName, final String expectedName) {
        // when
        Menu menu = Menu.valueOf(menuName);

        // then
        assertThat(menu.getName()).isEqualTo(expectedName);
    }

    @ParameterizedTest(name = "{0}는 {1}이다.")
    @CsvSource(value = {
        "MUSHROOM_SOUP, APPETIZER", "TAPAS, APPETIZER", "CAESAR_SALAD, APPETIZER",
        "T_BONE_STREAK, MAIN", "BBQ_RIBS, MAIN",
        "SEAFOOD_PASTA, MAIN", "CHRISTMAS_PASTA, MAIN",
        "CHOCOLATE_CAKE, DESSERT", "ICE_CREAM, DESSERT",
        "ZERO_COKE, DRINK", "RED_WINE, DRINK", "CHAMPAGNE, DRINK"
    })
    void 메뉴의_종류를_갖고_있다(final String menuName, final String expectedType) {
        // when
        Menu menu = Menu.valueOf(menuName);

        // then
        assertThat(menu.getType()).isEqualTo(MenuType.valueOf(expectedType));
    }

    @ParameterizedTest(name = "{0}의 가격은 {1}원이다.")
    @CsvSource(value = {
        "MUSHROOM_SOUP, 6_000", "TAPAS, 5_500", "CAESAR_SALAD, 8_000",
        "T_BONE_STREAK, 55_000", "BBQ_RIBS, 54_000",
        "SEAFOOD_PASTA, 34_000", "CHRISTMAS_PASTA, 25_000",
        "CHOCOLATE_CAKE, 15_000", "ICE_CREAM, 5_000",
        "ZERO_COKE, 3_000", "RED_WINE, 60_000", "CHAMPAGNE, 25_000"
    })
    void 메뉴의_가격을_갖고_있다(final String menuName, final int expectedPrice) {
        // when
        Menu menu = Menu.valueOf(menuName);

        // then
        assertThat(menu.getPrice()).isEqualTo(expectedPrice);
    }

    @ParameterizedTest(name = "{0}로 {1}를 불러올 수 있다.")
    @CsvSource(value = {
        "양송이수프, MUSHROOM_SOUP", "타파스, TAPAS", "시저샐러드, CAESAR_SALAD",
        "티본스테이크, T_BONE_STREAK", "바비큐립, BBQ_RIBS",
        "해산물파스타, SEAFOOD_PASTA", "크리스마스파스타, CHRISTMAS_PASTA",
        "초코케이크, CHOCOLATE_CAKE", "아이스크림, ICE_CREAM",
        "제로콜라, ZERO_COKE", "레드와인, RED_WINE", "샴페인, CHAMPAGNE"
    })
    void 메뉴_이름을_이용해_불러올_수_있다(final String name, final String expectedMenuName) {
        // when
        Menu menu = Menu.of(name);

        // then
        assertThat(menu.name()).isEqualTo(expectedMenuName);
    }

    @DisplayName("다음과 같은 상황에 예외가 발생한다.")
    @Nested
    class MenuExceptionTest {

        @Test
        void 존재하지_않는_메뉴를_찾는_경우() {
            // given
            String name = "민트초코";

            // when, then
            assertThatThrownBy(() -> Menu.of(name))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}