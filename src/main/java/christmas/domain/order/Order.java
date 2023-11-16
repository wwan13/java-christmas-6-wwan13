package christmas.domain.order;

import static christmas.domain.constant.Constant.ORDER_MAXIMUM_AMOUNT;
import static christmas.domain.constant.Constant.ORDER_MINIMUM_AMOUNT;
import static christmas.domain.constant.ErrorMessage.INVALID_ORDER;
import static christmas.domain.menu.MenuType.DRINK;

import christmas.common.converter.StringConverter;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Order {

    private static final String DELIMITER = "-";
    private static final int MENU_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    private final Map<Menu, Integer> order;

    private Order(Map<Menu, Integer> order) {
        this.order = order;
    }

    public static Order of(List<String> menuAmounts) {
        Map<Menu, Integer> order = createOrder(menuAmounts);
        validateMaximumOrderAmount(order);
        validateOnlyDrink(order);
        return new Order(order);
    }

    private static void validateMaximumOrderAmount(Map<Menu, Integer> order) {
        int totalAmount = order.keySet().stream()
            .mapToInt(order::get)
            .sum();

        if (totalAmount > ORDER_MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private static void validateOnlyDrink(Map<Menu, Integer> order) {
        boolean isOnlyDrink = order.keySet().stream()
            .map(Menu::getType)
            .allMatch(menuType -> menuType.equals(DRINK));

        if (isOnlyDrink) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private static Map<Menu, Integer> createOrder(List<String> menuAmounts) {
        Map<Menu, Integer> order = new EnumMap<>(Menu.class);
        menuAmounts.forEach(menuAmount -> {
            Menu menu = createMenu(menuAmount.split(DELIMITER)[MENU_INDEX], order);
            int amount = parseAmount(menuAmount.split(DELIMITER)[AMOUNT_INDEX]);
            order.put(menu, amount);
        });
        return order;
    }

    private static Menu createMenu(String value, Map<Menu, Integer> order) {
        Menu menu = Menu.of(value);
        validateMenuContainsInOrder(menu, order);
        return menu;
    }

    private static void validateMenuContainsInOrder(Menu menu, Map<Menu, Integer> order) {
        if (order.keySet().contains(menu)) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private static int parseAmount(String value) {
        int amount = StringConverter.toIntWithExceptionThrow(value,
            () -> new IllegalArgumentException(INVALID_ORDER)
        );
        validateAmountPerMenu(amount);
        return amount;
    }

    private static void validateAmountPerMenu(int amount) {
        if (amount < ORDER_MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    public int calculateTotalPrice() {
        return order.keySet().stream()
            .mapToInt(money -> money.getPrice() * order.get(money))
            .sum();
    }

    public int countMenuTypeAmount(MenuType menuType) {
        return order.keySet().stream()
            .filter(menu -> menu.getType().equals(menuType))
            .mapToInt(order::get)
            .sum();
    }

    public Map<Menu, Integer> getOrder() {
        return Collections.unmodifiableMap(order);
    }
}
