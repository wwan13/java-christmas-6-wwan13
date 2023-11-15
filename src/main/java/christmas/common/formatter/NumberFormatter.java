package christmas.common.formatter;

import java.text.DecimalFormat;

public class NumberFormatter {

    private static final String NUMBER_FORMAT = "###,###";

    private NumberFormatter() {
    }

    public static String format(int value) {
        DecimalFormat formatter = new DecimalFormat(NUMBER_FORMAT);
        return formatter.format(value);
    }
}
