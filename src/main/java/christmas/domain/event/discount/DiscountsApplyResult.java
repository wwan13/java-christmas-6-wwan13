package christmas.domain.event.discount;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DiscountsApplyResult {

    private final Map<Discounts, Integer> applyResult;

    private DiscountsApplyResult(Map<Discounts, Integer> events) {
        this.applyResult = events;
    }

    protected static DiscountsApplyResult init() {
        return new DiscountsApplyResult(new EnumMap<>(Discounts.class));
    }

    protected void put(Discounts discount, int benefitAmount) {
        applyResult.put(discount, benefitAmount);
    }

    public boolean hasAppliedDiscounts() {
        return !applyResult.isEmpty();
    }

    public int calculateTotalDiscountAmount() {
        return applyResult.keySet().stream()
            .mapToInt(this::getDiscountAmount)
            .sum();
    }

    public List<Discounts> discounts() {
        return new ArrayList<>(applyResult.keySet());
    }

    public int getDiscountAmount(Discounts discount) {
        return applyResult.get(discount);
    }
}
