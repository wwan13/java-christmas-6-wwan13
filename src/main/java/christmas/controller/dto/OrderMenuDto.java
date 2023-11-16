package christmas.controller.dto;

import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import java.util.LinkedHashMap;
import java.util.Map;

public record OrderMenuDto(
    Map<String, Integer> orders
) {

    public static OrderMenuDto from(Order order) {
        Map<String, Integer> menuAmount = new LinkedHashMap<>();

        Map<Menu, Integer> orders = order.getOrder();
        orders.forEach((key, value) -> menuAmount.put(key.getName(), value));

        return new OrderMenuDto(menuAmount);
    }
}
