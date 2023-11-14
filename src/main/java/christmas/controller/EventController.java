package christmas.controller;

import christmas.domain.Benefit;
import christmas.domain.Order;
import christmas.service.EventService;
import christmas.validator.Validator;
import christmas.view.InputView;
import christmas.view.OutPutView;

public class EventController {
    InputView inputView;
    OutPutView outPutView;
    EventService eventService;

    public EventController(InputView inputView, OutPutView outPutView, EventService eventService) {
        this.inputView = inputView;
        this.outPutView = outPutView;
        this.eventService = eventService;
    }

    public void run() {
        outPutView.printStart();
        final int day = getDateInput();
        final Order order = getMenuInput();
        outPutView.printPreviewNotice(day);
        outPutView.printOrder(order);
        int totalBeforeAmount = eventService.calcTotalBeforeDiscount(order);
        outPutView.printTotalBeforeDiscount(totalBeforeAmount);
        final Benefit benefit = getBenefit(order, day);
    }

    public int getDateInput() {
        while (true) {
            try {
                String input = inputView.readDate();
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Order getMenuInput() {
        while (true) {
            try {
                String input = inputView.readMenu();
                Validator.validateMenuInput(input.replaceAll("\\s", ""));
                return eventService.stringToOrder(input.replaceAll("\\s", ""));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Benefit getBenefit(Order order, int day) {
        Benefit benefit = eventService.applyBenefit(order, day);
        String giftOutput = eventService.getGiftOutput(benefit);
        outPutView.printGift(giftOutput);
        outPutView.printBenefits(benefit);

        return benefit;
    }
}
