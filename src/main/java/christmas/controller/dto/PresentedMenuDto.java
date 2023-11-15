package christmas.controller.dto;

import christmas.domain.event.present.PresentEvent;

public record PresentedMenuDto(
    String menu,
    int amount
) {

    public static PresentedMenuDto from(PresentEvent presentEvent) {

        return new PresentedMenuDto(presentEvent.getPresentedMenuName(),
            presentEvent.getPresentAmount());
    }
}
