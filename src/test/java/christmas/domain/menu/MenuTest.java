package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTest {

    @DisplayName("메뉴의 이름을 갖고 있다.")
    @ParameterizedTest(name = "[{index}] : menu = {0}, name ={1}")
    @CsvSource(value = {
        "MUSHROOM_SOUP, 양송이수프", "TAPAS, 타파스", "CAESAR_SALAD, 시저샐러드",
        "T_BONE_STREAK, 티본스테이크", "BBQ_RIBS, 바비큐립",
        "SEAFOOD_PASTA, 해산물파스타", "CHRISTMAS_PASTA, 크리스마스파스타",
        "CHOCOLATE_CAKE, 초코케이크", "ICE_CREAM, 아이스크림",
        "ZERO_COKE, 제로콜라", "RED_WINE, 레드와인", "CHAMPAGNE, 샴페인"
    })
    void hasMenuName(final String menuName, final String expectedName) {
        // when
        Menu menu = Menu.valueOf(menuName);

        // then
        assertThat(menu.getName()).isEqualTo(expectedName);
    }

    @DisplayName("메뉴의 타입을 갖고 있다.")
    @ParameterizedTest(name = "[{index}] : menu = {0}, type = {1}")
    @CsvSource(value = {
        "MUSHROOM_SOUP, APPETIZER", "TAPAS, APPETIZER", "CAESAR_SALAD, APPETIZER",
        "T_BONE_STREAK, MAIN", "BBQ_RIBS, MAIN",
        "SEAFOOD_PASTA, MAIN", "CHRISTMAS_PASTA, MAIN",
        "CHOCOLATE_CAKE, DESSERT", "ICE_CREAM, DESSERT",
        "ZERO_COKE, DRINK", "RED_WINE, DRINK", "CHAMPAGNE, DRINK"
    })
    void hasMenuType(final String menuName, final String expectedType) {
        // when
        Menu menu = Menu.valueOf(menuName);

        // then
        assertThat(menu.getType()).isEqualTo(MenuType.valueOf(expectedType));
    }

    @DisplayName("메뉴의 가격을 갖고 있다.")
    @ParameterizedTest(name = "[{index}] : menu = {0}, price = {1}")
    @CsvSource(value = {
        "MUSHROOM_SOUP, 6_000", "TAPAS, 5_500", "CAESAR_SALAD, 8_000",
        "T_BONE_STREAK, 55_000", "BBQ_RIBS, 54_000",
        "SEAFOOD_PASTA, 34_000", "CHRISTMAS_PASTA, 25_000",
        "CHOCOLATE_CAKE, 15_000", "ICE_CREAM, 5_000",
        "ZERO_COKE, 3_000", "RED_WINE, 60_000", "CHAMPAGNE, 25_000"
    })
    void hasMenuPrice(final String menuName, final int expectedPrice) {
        // when
        Menu menu = Menu.valueOf(menuName);

        // then
        assertThat(menu.getPrice()).isEqualTo(expectedPrice);
    }

    @DisplayName("이름을 이용해 Menu를 불러올 수 있다.")
    @ParameterizedTest(name = "[{index}] : name = {0}, menu ={1}")
    @CsvSource(value = {
        "양송이수프, MUSHROOM_SOUP", "타파스, TAPAS", "시저샐러드, CAESAR_SALAD",
        "티본스테이크, T_BONE_STREAK", "바비큐립, BBQ_RIBS",
        "해산물파스타, SEAFOOD_PASTA", "크리스마스파스타, CHRISTMAS_PASTA",
        "초코케이크, CHOCOLATE_CAKE", "아이스크림, ICE_CREAM",
        "제로콜라, ZERO_COKE", "레드와인, RED_WINE", "샴페인, CHAMPAGNE"
    })
    void getMenuByName(final String name, final String expectedMenuName) {
        // when
        Menu menu = Menu.of(name);

        // then
        assertThat(menu.name()).isEqualTo(expectedMenuName);
    }

    @DisplayName("존재하지 않는 이름을 찾을 시 예외가 발생한다.")
    @Test
    void getMenuByNotExistName() {
        // given
        String name = "민트초코";

        // when, then
        assertThatThrownBy(() -> Menu.of(name))
            .isInstanceOf(IllegalArgumentException.class);
    }
}