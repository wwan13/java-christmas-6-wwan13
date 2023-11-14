package christmas.domain.event.discount;

import christmas.domain.date.Date;
import christmas.domain.order.Order;
import java.util.Arrays;

public enum Discounts {

    CHRISTMAS_DAY_EVENT("크리스마스 디데이 할인", new ChristmasDayDiscount()),
    WEEKDAY_EVENT("평일 할인", WeekDiscount.WEEKDAY_DISCOUNT),
    WEEKEND_EVENT("주말 할인", WeekDiscount.WEEKEND_DISCOUNT),
    SPECIAL_EVENT("특별 할인", new SpecialDiscount());

    private final String name;
    private final Discount discount;

    Discounts(String name, Discount discount) {
        this.name = name;
        this.discount = discount;
    }

    public static DiscountsApplyResult applyAll(Date date, Order order) {
        DiscountsApplyResult applyResult = DiscountsApplyResult.init();

        Arrays.stream(values())
            .filter(discount -> discount.discount.isApplicable(date, order))
            .forEach(discount -> applyResult.put(discount, discount.apply(date, order)));

        return applyResult;
    }

    private int apply(Date date, Order order) {
        return discount.offer(date, order);
    }

    public String getName() {
        return name;
    }
}
