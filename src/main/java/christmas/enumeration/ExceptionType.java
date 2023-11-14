package christmas.enumeration;

public enum ExceptionType {
    INVALID_DATE_TYPE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU_FORMAT("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
