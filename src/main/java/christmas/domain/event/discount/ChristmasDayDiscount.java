package christmas.domain.event.discount;

import static christmas.domain.constant.Constant.CHRISTMAS_DAY_EVENT_ADDITIONAL_DISCOUNT_AMOUNT;
import static christmas.domain.constant.Constant.CHRISTMAS_DAY_EVENT_DEFAULT_DISCOUNT_AMOUNT;
import static christmas.domain.constant.Constant.CHRISTMAS_DAY_EVENT_END_DATE;
import static christmas.domain.constant.Constant.CHRISTMAS_DAY_EVENT_START_DATE;
import static christmas.domain.constant.ErrorMessage.CANNOT_APPLY_FORMAT;

import christmas.domain.date.Date;
import christmas.domain.order.Order;

public class ChristmasDayDiscount implements Discount {

    @Override
    public boolean isApplicableCore(Date date, Order order) {
        return date.compareTo(CHRISTMAS_DAY_EVENT_START_DATE) >= 0
            && date.compareTo(CHRISTMAS_DAY_EVENT_END_DATE) <= 0;
    }

    @Override
    public int offer(Date date, Order order) {
        validateOffer(date, order);
        int dateGap = date.compareTo(CHRISTMAS_DAY_EVENT_START_DATE);

        return CHRISTMAS_DAY_EVENT_DEFAULT_DISCOUNT_AMOUNT
            + (dateGap * CHRISTMAS_DAY_EVENT_ADDITIONAL_DISCOUNT_AMOUNT);
    }

    private void validateOffer(Date date, Order order) {
        if (!isApplicableCore(date, order)) {
            throw new IllegalArgumentException(
                String.format(CANNOT_APPLY_FORMAT, date.getValue(), getClass().getName())
            );
        }
    }
}
