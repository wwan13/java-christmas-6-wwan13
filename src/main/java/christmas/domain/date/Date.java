package christmas.domain.date;

import static christmas.domain.constant.Constant.DECEMBER_END_DATE;
import static christmas.domain.constant.Constant.DECEMBER_START_DATE;
import static christmas.domain.constant.ErrorMessage.INVALID_DATE;

public class Date {

    private final int value;

    private Date(int value) {
        this.value = value;
    }

    public static Date valueOf(int value) {
        validateIsDecember(value);
        return new Date(value);
    }

    private static void validateIsDecember(int value) {
        if (value < DECEMBER_START_DATE || value > DECEMBER_END_DATE) {
            throw new IllegalArgumentException(INVALID_DATE);
        }
    }

    public DateType getDateType() {
        return DateType.of(this);
    }

    protected int calculateRemainder(int value) {
        return this.value % value;
    }
}
