package christmas.enumeration;

public enum NoticeType {
    START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    DATE_INPUT("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

    private String message;

    NoticeType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
