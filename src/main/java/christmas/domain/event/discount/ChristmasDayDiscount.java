package christmas.domain.event.discount;

import christmas.domain.date.Date;
import christmas.domain.order.Order;

public class ChristmasDayDiscount implements Discount {

    private static final Date EVENT_START_DATE = Date.valueOf(1);
    private static final Date EVENT_END_DATE = Date.valueOf(25);
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int ADDITIONAL_DISCOUNT_AMOUNT = 100;

    @Override
    public boolean isApplicable(Date date) {
        return date.compareTo(EVENT_START_DATE) >= 0 && date.compareTo(EVENT_END_DATE) <= 0;
    }

    @Override
    public int offer(Date date, Order order) {
        int dateGap = date.compareTo(EVENT_START_DATE);
        return DEFAULT_DISCOUNT_AMOUNT + (dateGap * ADDITIONAL_DISCOUNT_AMOUNT);
    }
}
