package christmas.common.formatter;

import java.text.DecimalFormat;

public class NumberFormatter {

    private NumberFormatter() {
    }

    public static String format(int value) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(value);
    }
}
