package christmas.domain.badge;

import java.util.Arrays;

public enum EventBadge {

    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NOTHING("", 0);

    private final String name;
    private final int criticalBenefitAmount;

    EventBadge(String name, int criticalBenefitAmount) {
        this.name = name;
        this.criticalBenefitAmount = criticalBenefitAmount;
    }

    public static EventBadge of(int totalBenefitAmount) {
        return Arrays.stream(values())
            .filter(badge -> badge.criticalBenefitAmount < totalBenefitAmount)
            .findFirst()
            .orElseGet(() -> NOTHING);
    }

    public boolean hasBadge() {
        return !this.equals(NOTHING);
    }

    public String getName() {
        return name;
    }
}
