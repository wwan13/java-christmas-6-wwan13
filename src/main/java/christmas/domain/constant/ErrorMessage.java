package christmas.domain.constant;

public class ErrorMessage {

    public static final String INVALID_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String INVALID_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String CANNOT_APPLY_FORMAT = "12월 %d일에는 %d 이벤트를 적용할 수 없습니다.";

    private ErrorMessage() {
    }
}
