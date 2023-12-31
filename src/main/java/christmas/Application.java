package christmas;

import christmas.controller.EventController;
import christmas.service.BenefitService;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutPutView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutPutView outPutView = new OutPutView();
        EventService eventService = new EventService();
        BenefitService benefitService = new BenefitService();
        EventController eventController = new EventController(inputView, outPutView, eventService, benefitService);

        eventController.run();
    }
}
