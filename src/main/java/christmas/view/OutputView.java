package christmas.view;

import christmas.common.formatter.NumberFormatter;
import christmas.controller.dto.BenefitDetailDto;
import christmas.controller.dto.OrderMenuDto;
import christmas.controller.dto.PresentedMenuDto;

public class OutputView {

    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_PREVIEW_MESSAGE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENU_TITLE = "\n<주문 메뉴>";
    private static final String TOTAL_ORDER_PRICE_TITLE = "\n<할인 전 총주문 금액>";
    private static final String PRESENTED_MENU_TITLE = "\n<증정 메뉴>";
    private static final String BENEFIT_DETAIL_TITLE = "\n<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_TITLE = "\n<총혜택 금액>";
    private static final String EXPECTED_PAY_AMOUNT_TITLE = "\n<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_TITLE = "\n<12월 이벤트 배지>";
    private static final String MENU_AMOUNT_MESSAGE_FORMAT = "%s %d개\n";
    private static final String PRICE_FORMAT = "%s원\n";
    private static final String MINUS_PRICE_FORMAT = "%s원\n";
    private static final String BENEFIT_DETAIL_MESSAGE_FORMAT = "%s: -%s원\n";
    private static final String NOTHING_MESSAGE = "없음";

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printEventPreviewMessage(int date) {
        System.out.printf(EVENT_PREVIEW_MESSAGE_FORMAT, date);
    }

    public void printOrderMenu(OrderMenuDto orderMenuDto) {
        System.out.println(ORDER_MENU_TITLE);
        orderMenuDto.orders()
            .forEach((menu, amount) ->
                System.out.printf(MENU_AMOUNT_MESSAGE_FORMAT, menu, amount));
    }

    public void printTotalOrderPrice(int totalOrderPrice) {
        System.out.println(TOTAL_ORDER_PRICE_TITLE);
        System.out.printf(PRICE_FORMAT, NumberFormatter.format(totalOrderPrice));
    }

    public void printPresentedMenu(PresentedMenuDto presentedMenuDto) {
        System.out.println(PRESENTED_MENU_TITLE);
        if (presentedMenuDto.amount() == 0) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        System.out.printf(MENU_AMOUNT_MESSAGE_FORMAT, presentedMenuDto.menu(),
            presentedMenuDto.amount());
    }

    public void printBenefitDetail(BenefitDetailDto benefitDetailDto) {
        System.out.println(BENEFIT_DETAIL_TITLE);
        if (benefitDetailDto.benefits().isEmpty()) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        benefitDetailDto.benefits().forEach((event, amount) ->
            System.out.printf(BENEFIT_DETAIL_MESSAGE_FORMAT,
                event, NumberFormatter.format(amount)));
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT_TITLE);
        System.out.printf(MINUS_PRICE_FORMAT, NumberFormatter.format(totalBenefitAmount));
    }

    public void printExpectedPayAmount(int expectedPayAmount) {
        System.out.println(EXPECTED_PAY_AMOUNT_TITLE);
        System.out.printf(PRICE_FORMAT, NumberFormatter.format(expectedPayAmount));
    }

    public void printEventBadge(String badge) {
        System.out.println(EVENT_BADGE_TITLE);
        if (badge.isEmpty()) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        System.out.println(badge);
    }
}
