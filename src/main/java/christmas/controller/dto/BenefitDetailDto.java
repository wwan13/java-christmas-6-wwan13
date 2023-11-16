package christmas.controller.dto;

import christmas.domain.event.discount.DiscountsApplyResult;
import christmas.domain.event.present.PresentEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public record BenefitDetailDto(
    Map<String, Integer> benefits
) {

    public static BenefitDetailDto of(DiscountsApplyResult discountsApplyResult,
                                      PresentEvent presentEvent) {
        Map<String, Integer> benefits = new LinkedHashMap<>();

        discountsApplyResult.discounts().forEach(discount ->
            benefits.put(discount.getName(), discountsApplyResult.getDiscountAmount(discount)));

        if (presentEvent.isApplied()) {
            benefits.put(presentEvent.getEventName(), presentEvent.calculatePresentedPrice());
        }

        return new BenefitDetailDto(benefits);
    }
}
