package christmas.controller;

import static christmas.domain.constant.ErrorMessage.INVALID_DATE;

import christmas.common.converter.StringConverter;
import christmas.common.logger.Logger;
import christmas.config.ChristmasConfig;
import christmas.controller.dto.BenefitDetailDto;
import christmas.controller.dto.OrderMenuDto;
import christmas.controller.dto.PresentedMenuDto;
import christmas.domain.date.Date;
import christmas.domain.order.Order;
import christmas.domain.receipt.Receipt;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ChristmasController {

    private static final String ORDER_DELIMITER = ",";

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(ChristmasConfig config) {
        this.inputView = config.inputView();
        this.outputView = config.outputView();
    }

    public void execute() {
        outputView.printStartMessage();
        Date date = executeWithExceptionHandle(this::readVisitDate);
        Order order = executeWithExceptionHandle(this::readOrder);
        outputView.printEventPreviewMessage(date.getValue());
        Receipt receipt = Receipt.of(date, order);
        printResults(receipt);
    }

    private Date readVisitDate() {
        int date = StringConverter.toIntWithExceptionThrow(inputView.readDate(),
            () -> new IllegalArgumentException(INVALID_DATE));
        return Date.valueOf(date);
    }

    private Order readOrder() {
        String input = inputView.readMenuAmount();
        List<String> menuAmounts = Arrays.stream(input.split(ORDER_DELIMITER)).toList();
        return Order.of(menuAmounts);
    }

    private void printResults(Receipt receipt) {
        outputView.printOrderMenu(OrderMenuDto.from(receipt.getOrder()));
        outputView.printTotalOrderPrice(receipt.calculateTotalOrderPrice());
        outputView.printPresentedMenu(PresentedMenuDto.from(receipt.getPresentEvent()));
        outputView.printBenefitDetail(
            BenefitDetailDto.of(receipt.getDiscountsApplyResult(), receipt.getPresentEvent()));
        outputView.printTotalBenefitAmount(receipt.calculateTotalBenefitAmount());
        outputView.printExpectedPayAmount(receipt.calculateExpectedPayAmount());
        outputView.printEventBadge(receipt.getEventBadge().getName());
    }

    private <T> T executeWithExceptionHandle(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                Logger.error(e.getMessage());
            }
        }
    }
}
