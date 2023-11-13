package christmas.domain.event.discount;

import static christmas.domain.date.DateType.WEEKDAY;
import static christmas.domain.date.DateType.WEEKEND;
import static christmas.domain.menu.MenuType.DESSERT;
import static christmas.domain.menu.MenuType.MAIN;

import christmas.domain.date.Date;
import christmas.domain.date.DateType;
import christmas.domain.menu.MenuType;
import christmas.domain.order.Order;

public enum WeekDiscount implements Discount {

    WEEKDAY_DISCOUNT(WEEKDAY, DESSERT),
    WEEKEND_DISCOUNT(WEEKEND, MAIN);

    private static final int DISCOUNT_AMOUNT = 2023;
    private static final String ERROR_FORMAT_INVALID_DATE = "%d일에는 %s를 적용할 수 없습니다.";

    private final DateType dateType;
    private final MenuType target;

    WeekDiscount(DateType dateType, MenuType target) {
        this.dateType = dateType;
        this.target = target;
    }

    @Override
    public boolean isApplicable(Date date) {
        return date.getDateType() == this.dateType;
    }

    @Override
    public int offer(Date date, Order order) {
        validateOffer(date);
        int amount = order.countMenuTypeAmount(this.target);
        return amount * DISCOUNT_AMOUNT;
    }

    private void validateOffer(Date date) {
        if (!isApplicable(date)) {
            throw new IllegalArgumentException(
                String.format(ERROR_FORMAT_INVALID_DATE, date.getValue(), this.name())
            );
        }
    }
}
