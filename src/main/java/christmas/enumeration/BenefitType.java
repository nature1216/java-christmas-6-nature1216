package christmas.enumeration;

import java.time.LocalDate;

public enum BenefitType {
    X_MAS_DISCOUNT("크리스마스 디데이 할인", LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 25)),
    WEEKDAY_DISCOUNT("평일 할인", LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)),
    WEEKEND_DISCOUNT("주말 할인", LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)),
    SPECIAL_DISCOUNT("특별 할인", LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)),
    GIFT_EVENT("증정 이벤트", LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31));

    private String title;
    private LocalDate start;
    private LocalDate end;

    BenefitType(String title, LocalDate start, LocalDate end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
