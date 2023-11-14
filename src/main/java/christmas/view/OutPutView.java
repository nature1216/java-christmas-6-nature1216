package christmas.view;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.enumeration.*;
import christmas.util.FormatUtil;

public class OutPutView {
    public void printStart() {
        System.out.println(NoticeType.START_MESSAGE.getMessage());
    }

    public void printPreviewNotice(int date) {
        System.out.printf(NoticeType.BENEFIT_PREVIEW.getMessage() + "%n", date);
        System.out.println();
    }

    public void printOrder(Order order) {
        System.out.println(NoticeType.ORDERED_MENU.getMessage());
        for(MenuType menuType : MenuType.values()) {
            if(order.getNum(menuType) > 0) {
                System.out.println(menuType.getName() + " " + order.getNum(menuType) + "개");
            }
        }
        System.out.println();
    }

    public void printTotalBeforeDiscount(int amount) {
        System.out.println(NoticeType.TOTAL_BEFORE_DISCOUNT.getMessage());
        System.out.printf("%s원\n", FormatUtil.toWonFormat(amount));
        System.out.println();
    }

    public void printGift(String output) {
        System.out.println(NoticeType.GIFT.getMessage());
        System.out.println(output);
        System.out.println();
    }

    public void printBenefits(Benefit benefit) {
        System.out.println(NoticeType.BENEFIT.getMessage());

        StringBuilder output = new StringBuilder();
        for (BenefitType benefitType : BenefitType.values()) {
            if (benefit.getAmount(benefitType) == 0) {
                continue;
            }
            output.append(benefitType.getTitle()).append(": ").append(FormatUtil.toWonFormat(benefit.getAmount(benefitType) * (-1))).append("원\n");
        }
        if(output.length() == 0) {
            output = new StringBuilder(NoticeType.NONE.getMessage());
        }

        System.out.println(output);
    }

    public void printTotalBenefit(int amount) {
        System.out.println(NoticeType.TOTAL_BENEFIT.getMessage());
        System.out.println(FormatUtil.toWonFormat(amount * (-1)) + "원");
        System.out.println();
    }

    public void printFinalAmount(int amount) {
        System.out.println(NoticeType.FINAL_AMOUNT.getMessage());
        System.out.println(FormatUtil.toWonFormat(amount) + "원");
        System.out.println();
    }
}
