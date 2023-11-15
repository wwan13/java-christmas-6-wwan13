package christmas.common.converter;

import java.util.function.Supplier;

public class StringConverter {

    private StringConverter() {
    }

    public static int toIntWithExceptionThrow(String value,
        Supplier<RuntimeException> exception) throws RuntimeException {

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw exception.get();
        }
    }
}
