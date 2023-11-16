package christmas.domain.event.discount;

import static christmas.domain.constant.Constant.ORDER_MINIMUM_PRICE;

import christmas.domain.date.Date;
import christmas.domain.order.Order;

public interface Discount {

    default boolean isApplicable(Date date, Order order) {
        if (order.calculateTotalPrice() < ORDER_MINIMUM_PRICE) {
            return false;
        }

        return isApplicableCore(date, order);
    }

    boolean isApplicableCore(Date date, Order order);

    int offer(Date date, Order order);
}
