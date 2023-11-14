package christmas.domain.event.discount;

import static christmas.domain.constant.Constant.WEEK_EVENT_DISCOUNT_AMOUNT;
import static christmas.domain.constant.ErrorMessage.CANNOT_APPLY_FORMAT;
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

    private final DateType dateType;
    private final MenuType target;

    WeekDiscount(DateType dateType, MenuType target) {
        this.dateType = dateType;
        this.target = target;
    }

    @Override
    public boolean isApplicableCore(Date date, Order order) {
        return isSameDateType(date) && containsMenuType(order);
    }

    private boolean isSameDateType(Date date) {
        return dateType.equals(date.getDateType());
    }

    private boolean containsMenuType(Order order) {
        return order.countMenuTypeAmount(target) > 0;
    }

    @Override
    public int offer(Date date, Order order) {
        validateOffer(date, order);
        int amount = order.countMenuTypeAmount(this.target);
        return amount * WEEK_EVENT_DISCOUNT_AMOUNT;
    }

    private void validateOffer(Date date, Order order) {
        if (!isApplicableCore(date, order)) {
            throw new IllegalArgumentException(
                String.format(CANNOT_APPLY_FORMAT, date.getValue(), getClass().getName())
            );
        }
    }
}
