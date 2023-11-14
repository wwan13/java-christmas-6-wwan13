package christmas.domain.event.present;

import static christmas.domain.constant.Constant.PRESENT_AMOUNT;
import static christmas.domain.constant.Constant.PRESENT_AMOUNT_NOTHING;
import static christmas.domain.constant.Constant.PRESENT_EVENT_MENU;
import static christmas.domain.constant.Constant.PRESENT_EVENT_PRICE_CONDITION;

import christmas.domain.menu.Menu;

public class PresentEvent {

    private static final String EVENT_NAME = "증정 이벤트";

    private final Menu presentedMenu;
    private final int presentAmount;

    public PresentEvent(Menu presentedMenu, int presentAmount) {
        this.presentedMenu = presentedMenu;
        this.presentAmount = presentAmount;
    }

    public static PresentEvent apply(int totalPrice) {
        if (!isApplicable(totalPrice)) {
            return new PresentEvent(PRESENT_EVENT_MENU, PRESENT_AMOUNT_NOTHING);
        }

        return new PresentEvent(PRESENT_EVENT_MENU, PRESENT_AMOUNT);
    }

    private static boolean isApplicable(int totalPrice) {
        return totalPrice > PRESENT_EVENT_PRICE_CONDITION;
    }

    public boolean isApplied() {
        return presentAmount > 0;
    }

    public int calculatePresentedPrice() {
        return presentedMenu.getPrice() * presentAmount;
    }

    public String getPresentedMenuName() {
        return presentedMenu.getName();
    }

    public int getPresentAmount() {
        return presentAmount;
    }

    public String getEventName() {
        return EVENT_NAME;
    }
}
