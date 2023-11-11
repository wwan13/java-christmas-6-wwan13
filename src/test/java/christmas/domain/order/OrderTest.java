package christmas.domain.order;

import static christmas.domain.menu.MenuType.DESSERT;
import static christmas.domain.menu.MenuType.DRINK;
import static christmas.domain.menu.MenuType.MAIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Menu;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Order 는")
class OrderTest {

    @Test
    void 메뉴와_수량을_가진_문자열_리스트로_생성된다() {
        // given
        List<String> menuAmounts = List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1");

        // when
        Order order = Order.of(menuAmounts);

        // then
        assertThat(order.getOrder())
            .containsKeys(Menu.of("티본스테이크"), Menu.of("바비큐립"),
                Menu.of("초코케이크"), Menu.of("제로콜라"));
    }

    @Test
    void 총_주문_금액을_계산할_수_있다() {
        // given
        List<String> menuAmounts = List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1");
        Order order = Order.of(menuAmounts);

        // when
        int totalPrice = order.calculateTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(142_000);
    }

    @Test
    void 주문에_입력한_메뉴_타입이_몇_개_있는지_알_수_있다() {
        // given
        List<String> menuAmounts = List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1");
        Order order = Order.of(menuAmounts);

        // when
        int mainMenuAmount = order.countMenuTypeAmount(MAIN);
        int dessertAmount = order.countMenuTypeAmount(DESSERT);
        int drinkAmount = order.countMenuTypeAmount(DRINK);

        // then
        assertThat(mainMenuAmount).isEqualTo(2);
        assertThat(dessertAmount).isEqualTo(2);
        assertThat(drinkAmount).isEqualTo(1);
    }

    @DisplayName("다음과 같은 상황에 예외가 발생한다.")
    @Nested
    class OrderExceptionTest {

        @Test
        void 총_주문_금액이_10000원_미만인_경우() {
            // given
            List<String> menuAmounts = List.of("타파스-1");

            // when, then
            assertThatThrownBy(() -> Order.of(menuAmounts))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 주문_개수가_20_을_초과하는_경우() {
            // given
            List<String> menuAmounts = List.of("양송이수프-10", "바비큐립-11");

            // when, then
            assertThatThrownBy(() -> Order.of(menuAmounts))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 주문_메뉴_중_음료만_있는_경우() {
            // given
            List<String> menuAmounts = List.of("제로콜라-1", "레드와인-1");

            // when, then
            assertThatThrownBy(() -> Order.of(menuAmounts))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 메뉴의_주문_개수가_1_미만인_경우() {
            // given
            List<String> menuAmounts = List.of("양송이수프-0", "바비큐립-1");

            // when, then
            assertThatThrownBy(() -> Order.of(menuAmounts))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 중복_메뉴를_입력한_경우() {
            // given
            List<String> menuAmounts = List.of("양송이수프-1", "양송이수프-1");

            // when, then
            assertThatThrownBy(() -> Order.of(menuAmounts))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 존재하지_않는_메뉴를_입력한_경우() {
            // given
            List<String> menuAmounts = List.of("양송이수프-1", "민트초코-1");

            // when, then
            assertThatThrownBy(() -> Order.of(menuAmounts))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}