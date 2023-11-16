package christmas.domain.receipt;

import christmas.domain.badge.EventBadge;
import christmas.domain.date.Date;
import christmas.domain.event.discount.Discounts;
import christmas.domain.event.discount.DiscountsApplyResult;
import christmas.domain.event.present.PresentEvent;
import christmas.domain.order.Order;

public class Receipt {

    private final Date date;
    private final Order order;
    private final DiscountsApplyResult discountsApplyResult;
    private final PresentEvent presentEvent;

    public Receipt(Date date, Order order, DiscountsApplyResult discountsApplyResult,
        PresentEvent presentEvent) {
        this.date = date;
        this.order = order;
        this.discountsApplyResult = discountsApplyResult;
        this.presentEvent = presentEvent;
    }

    public static Receipt of(Date date, Order order) {
        DiscountsApplyResult discountsApplyResult = Discounts.applyAll(date, order);
        PresentEvent presentEvent = PresentEvent.apply(order.calculateTotalPrice());

        return new Receipt(date, order, discountsApplyResult, presentEvent);
    }

    public Order getOrder() {
        return order;
    }

    public int calculateTotalOrderPrice() {
        return order.calculateTotalPrice();
    }

    public PresentEvent getPresentEvent() {
        return presentEvent;
    }

    public DiscountsApplyResult getDiscountsApplyResult() {
        return discountsApplyResult;
    }

    public int calculateTotalBenefitAmount() {
        return discountsApplyResult.calculateTotalDiscountAmount()
            + presentEvent.calculatePresentedPrice();
    }

    public int calculateExpectedPayAmount() {
        return order.calculateTotalPrice() - discountsApplyResult.calculateTotalDiscountAmount();
    }

    public EventBadge getEventBadge() {
        return EventBadge.of(calculateTotalBenefitAmount());
    }
}
