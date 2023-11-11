package christmas.controller;

import christmas.domain.Orders;
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
        final int date = getDateInput();
        final Orders orders = getMenuInput();
        outPutView.printPreviewNotice(date);
        outPutView.printOrders(orders);
        int totalBeforeAmount = eventService.calcTotalBeforeDiscount(orders);
        outPutView.printTotalBeforeDiscount(totalBeforeAmount);
    }

    public int getDateInput() {
        while(true) {
            try{
                String input = inputView.readDate();
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Orders getMenuInput() {
        while(true) {
            try {
                String input = inputView.readMenu();
                Validator.validateMenuInput(input.replaceAll("\\s", ""));
                return eventService.stringToOrders(input.replaceAll("\\s", ""));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
