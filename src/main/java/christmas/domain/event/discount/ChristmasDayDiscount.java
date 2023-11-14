package christmas.domain.event.discount;

import christmas.domain.date.Date;
import christmas.domain.order.Order;

public class ChristmasDayDiscount implements Discount {

    private static final Date EVENT_START_DATE = Date.valueOf(1);
    private static final Date EVENT_END_DATE = Date.valueOf(25);
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int ADDITIONAL_DISCOUNT_AMOUNT = 100;
    private static final String ERROR_FORMAT_INVALID_DATE =
        "%d일에는 크리스마스 디데이 할인을 적용할 수 없습니다.";

    @Override
    public boolean isApplicable(Date date) {
        return date.compareTo(EVENT_START_DATE) >= 0 && date.compareTo(EVENT_END_DATE) <= 0;
    }

    @Override
    public int offer(Date date, Order order) {
        int dateGap = date.compareTo(EVENT_START_DATE);
        return DEFAULT_DISCOUNT_AMOUNT + (dateGap * ADDITIONAL_DISCOUNT_AMOUNT);
    }

    private void validate(Date date) {
        if (!isApplicable(date)) {
            throw new IllegalArgumentException(
                String.format(ERROR_FORMAT_INVALID_DATE, date.getValue())
            );
        }
    }
}
