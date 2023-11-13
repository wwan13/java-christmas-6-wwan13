package christmas.domain.event.discount;

import christmas.domain.date.Date;
import christmas.domain.order.Order;
import java.util.List;

public class SpecialDiscount implements Discount {

    private static final List<Date> STARRED_DAYS = List.of(Date.valueOf(3), Date.valueOf(10),
        Date.valueOf(17), Date.valueOf(24), Date.valueOf(25), Date.valueOf(31));
    private static final int DISCOUNT_AMOUNT = 1000;
    private static final String ERROR_FORMAT_INVALID_DATE = "%d일에는 특별할인을를 적용할 수 없습니다.";

    @Override
    public boolean isApplicable(Date date) {
        return STARRED_DAYS.contains(date);
    }

    @Override
    public int offer(Date date, Order order) {
        validateOffer(date);
        return DISCOUNT_AMOUNT;
    }

    private void validateOffer(Date date) {
        if (!isApplicable(date)) {
            throw new IllegalArgumentException(
                String.format(ERROR_FORMAT_INVALID_DATE, date.getValue())
            );
        }
    }
}
