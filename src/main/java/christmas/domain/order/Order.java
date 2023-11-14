package christmas.domain.order;

import static christmas.domain.constant.Constant.ORDER_MAXIMUM_AMOUNT;
import static christmas.domain.constant.Constant.ORDER_MINIMUM_AMOUNT;
import static christmas.domain.constant.Constant.ORDER_MINIMUM_PRICE;
import static christmas.domain.constant.ErrorMessage.INVALID_ORDER;
import static christmas.domain.menu.MenuType.DRINK;

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
            Menu menu = Menu.of(menuAmount.split(DELIMITER)[MENU_INDEX]);
            validateMenuContainsInOrder(menu, order);

            int amount = parseIntWithExceptionThrow(menuAmount.split(DELIMITER)[AMOUNT_INDEX]);
            validateAmountPerMenu(amount);

            order.put(menu, amount);
        });

        return order;
    }

    private static void validateMenuContainsInOrder(Menu menu, Map<Menu, Integer> order) {
        if (order.keySet().contains(menu)) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private static int parseIntWithExceptionThrow(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
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
