package christmas.config;

import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasConfig {

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
