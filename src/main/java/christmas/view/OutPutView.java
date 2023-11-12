package christmas.view;

import christmas.domain.Order;
import christmas.enumeration.MenuType;
import christmas.enumeration.NoticeType;
import christmas.util.FormatUtil;

public class OutPutView {
    public void printStart() {
        System.out.println(NoticeType.START_MESSAGE.getMessage());
    }

    public void printPreviewNotice(int date) {
        System.out.printf((NoticeType.BENEFIT_PREVIEW.getMessage()) + "%n", date);
        System.out.println();
    }

    public void printOrder(Order order) {
        System.out.println(NoticeType.ORDERED_MENU.getMessage());
        for (MenuType menuType : MenuType.values()) {
            if (order.getNum(menuType) > 0) {
                System.out.println(menuType.getName() + " " + order.getNum(menuType) + "개");
            }
        }
        System.out.println();
    }

    public void printTotalBeforeDiscount(int amount) {
        System.out.println(NoticeType.TOTAL_BEFORE_DISCOUNT.getMessage());
        System.out.printf("%s원", FormatUtil.toWonFormat(amount));
    }

}
