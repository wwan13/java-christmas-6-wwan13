package christmas.domain.event.discount;

import static christmas.domain.constant.Constant.SPECIAL_EVENT_DISCOUNT_AMOUNT;
import static christmas.domain.constant.Constant.SPECIAL_EVENT_STARRED_DATES;
import static christmas.domain.constant.ErrorMessage.CANNOT_APPLY_FORMAT;

import christmas.domain.date.Date;
import christmas.domain.order.Order;

public class SpecialDiscount implements Discount {

    @Override
    public boolean isApplicableCore(Date date, Order order) {
        return SPECIAL_EVENT_STARRED_DATES.contains(date);
    }

    @Override
    public int offer(Date date, Order order) {
        validateOffer(date, order);
        return SPECIAL_EVENT_DISCOUNT_AMOUNT;
    }

    private void validateOffer(Date date, Order order) {
        if (!isApplicableCore(date, order)) {
            throw new IllegalArgumentException(
                String.format(CANNOT_APPLY_FORMAT, date.getValue(), getClass().getName())
            );
        }
    }
}
