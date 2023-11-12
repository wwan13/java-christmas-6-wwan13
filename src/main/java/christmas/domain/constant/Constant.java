package christmas.domain.constant;

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

    private Constant() {
    }
}
