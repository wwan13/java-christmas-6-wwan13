package christmas.domain.event.discount;

import christmas.domain.date.Date;
import christmas.domain.order.Order;

public interface Discount {

    boolean isApplicable(Date date);

    int offer(Date date, Order order);
}
