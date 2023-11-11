package christmas.view;

import christmas.domain.Orders;
import christmas.enumeration.MenuType;
import christmas.enumeration.NoticeType;

public class OutPutView {
    public void printStart() {
        System.out.println(NoticeType.START_MESSAGE.getMessage());
    }

    public void printPreviewNotice(int date) {
        System.out.printf((NoticeType.BENEFIT_PREVIEW.getMessage()) + "%n", date);
        System.out.println();
    }

    public void printOrders(Orders orders) {
        System.out.println(NoticeType.ORDERED_MENU.getMessage());
        for(MenuType menuType : MenuType.values()) {
            if(orders.getNum(menuType) > 0) {
                System.out.println(menuType.getName() + " " + orders.getNum(menuType) + "개");
            }
        }
    }

}
