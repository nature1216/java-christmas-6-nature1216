package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutPutView;

public class EventController {
    InputView inputView;
    OutPutView outPutView;
    public EventController(InputView inputView, OutPutView outPutView) {
        this.inputView = inputView;
        this.outPutView = outPutView;
    }

    public void run() {
        outPutView.printStart();
        final int date = getDateInput();
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
}
