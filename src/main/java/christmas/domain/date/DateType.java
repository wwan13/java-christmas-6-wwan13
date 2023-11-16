package christmas.domain.date;

import static christmas.domain.constant.Constant.REMAINDERS_OF_WEEKDAYS;
import static christmas.domain.constant.Constant.REMAINDERS_OF_WEEKENDS;
import static christmas.domain.constant.Constant.SIZE_OF_WEEK;
import static christmas.domain.constant.ErrorMessage.INVALID_DATE;

import java.util.Arrays;
import java.util.function.Predicate;

public enum DateType {
    WEEKDAY((date) -> REMAINDERS_OF_WEEKDAYS.contains(date.calculateRemainder(SIZE_OF_WEEK))),
    WEEKEND((date) -> REMAINDERS_OF_WEEKENDS.contains(date.calculateRemainder(SIZE_OF_WEEK)));

    private final Predicate<Date> condition;

    DateType(Predicate<Date> condition) {
        this.condition = condition;
    }

    static DateType of(Date date) {
        return Arrays.stream(DateType.values())
            .filter(dateType -> dateType.condition.test(date))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_DATE));
    }
}
