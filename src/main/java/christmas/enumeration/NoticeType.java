package christmas.enumeration;

public enum NoticeType {
    START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    DATE_INPUT("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    MENU_INPUT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    BENEFIT_PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERED_MENU("<주문 메뉴>"),
    TOTAL_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIFT("<증정 메뉴>"),
    NONE("없음\n"),
    BENEFIT("<혜택 내역>"),
    TOTAL_BENEFIT("<총혜택 금액>"),
    FINAL_AMOUNT("<할인 후 예상 결제 금액>"),
    BADGE("<12월 이벤트 배지>");

    private String message;

    NoticeType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
