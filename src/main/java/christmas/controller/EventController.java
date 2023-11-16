package christmas.controller;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.enumeration.BadgeType;
import christmas.service.BenefitService;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutPutView;

public class EventController {
    private final InputView inputView;
    private final OutPutView outPutView;
    private final EventService eventService;
    private final BenefitService benefitService;

    public EventController(InputView inputView, OutPutView outPutView, EventService eventService, BenefitService benefitService) {
        this.inputView = inputView;
        this.outPutView = outPutView;
        this.eventService = eventService;
        this.benefitService = benefitService;
    }

    public void run() {
        outPutView.printStart();
        final int day = getDateInput();
        final Order order = getMenuInput();
        outPutView.printPreviewNotice(day);
        outPutView.printOrder(order);
        int totalBeforeAmount = order.calcTotalCost();
        outPutView.printTotalBeforeDiscount(totalBeforeAmount);
        final Benefit benefit = getBenefit(order, day);
        outPutView.printFinalAmount(eventService.calcTotalAfterDiscount(totalBeforeAmount, benefit));
        final BadgeType badge = eventService.awardBadge(benefit.getTotalBenefit());
        outPutView.printBadge(badge);
    }

    public int getDateInput() {
        try {
            String input = inputView.readDate();
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDateInput();
        }
    }

    public Order getMenuInput() {
        try {
            String input = inputView.readMenu();
            return eventService.stringToOrder(input.replaceAll("\\s", ""));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMenuInput();
        }
    }

    public Benefit getBenefit(Order order, int day) {
        Benefit benefit = benefitService.applyBenefit(order, day, order.calcTotalCost());
        String giftOutput = benefitService.getGiftOutput(benefit);

        outPutView.printGift(giftOutput);
        outPutView.printBenefits(benefit);
        outPutView.printTotalBenefit(benefit.getTotalBenefit());

        return benefit;
    }
}
