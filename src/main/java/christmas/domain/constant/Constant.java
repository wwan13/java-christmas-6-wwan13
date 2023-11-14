package christmas.domain.constant;

import christmas.domain.date.Date;
import java.util.List;

public class Constant {

    public static final int ORDER_MINIMUM_PRICE = 10_000;
    public static final int ORDER_MINIMUM_AMOUNT = 1;
    public static final int ORDER_MAXIMUM_AMOUNT = 20;

    public static final int DECEMBER_START_DATE = 1;
    public static final int DECEMBER_END_DATE = 31;

    public static final int SIZE_OF_WEEK = 7;
    public static final List<Integer> REMAINDERS_OF_WEEKDAYS = List.of(3, 4, 5, 6, 0);
    public static final List<Integer> REMAINDERS_OF_WEEKENDS = List.of(1, 2);

    public static final Date CHRISTMAS_DAY_EVENT_START_DATE = Date.valueOf(1);
    public static final Date CHRISTMAS_DAY_EVENT_END_DATE = Date.valueOf(25);
    public static final int CHRISTMAS_DAY_EVENT_DEFAULT_DISCOUNT_AMOUNT = 1000;
    public static final int CHRISTMAS_DAY_EVENT_ADDITIONAL_DISCOUNT_AMOUNT = 100;

    public static final int WEEK_EVENT_DISCOUNT_AMOUNT = 2023;

    public static final List<Date> SPECIAL_EVENT_STARRED_DATES = List.of(Date.valueOf(3),
        Date.valueOf(10), Date.valueOf(17), Date.valueOf(24), Date.valueOf(25), Date.valueOf(31));
    public static final int SPECIAL_EVENT_DISCOUNT_AMOUNT = 1000;


    private Constant() {
    }
}
