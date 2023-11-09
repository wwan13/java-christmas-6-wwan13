package christmas.domain.menu;

import static christmas.domain.constant.ErrorMessage.INVALID_ORDER;
import static christmas.domain.menu.MenuType.APPETIZER;
import static christmas.domain.menu.MenuType.DESSERT;
import static christmas.domain.menu.MenuType.DRINK;
import static christmas.domain.menu.MenuType.MAIN;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", APPETIZER, 6_000),
    TAPAS("타파스", APPETIZER, 5_500),
    CAESAR_SALAD("시저샐러드", APPETIZER, 8_000),

    T_BONE_STREAK("티본스테이크", MAIN, 55_000),
    BBQ_RIBS("바비큐립", MAIN, 54_000),
    SEAFOOD_PASTA("해산물파스타", MAIN, 34_000),
    CHRISTMAS_PASTA("크리스마스파스타", MAIN, 25_000),

    CHOCOLATE_CAKE("초코케이크", DESSERT, 15_000),
    ICE_CREAM("아이스크림", DESSERT, 5_000),

    ZERO_COKE("제로콜라", DRINK, 3_000),
    RED_WINE("레드와인", DRINK, 60_000),
    CHAMPAGNE("샴페인", DRINK, 25_000);

    private final String name;
    private final MenuType type;
    private final int price;

    Menu(String name, MenuType type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    private static final Map<String, Menu> MENUS = Collections.unmodifiableMap(
        Stream.of(values()).collect(Collectors.toMap(Menu::getName, Function.identity())));

    public static Menu of(String name) {
        validateExists(name);
        return MENUS.get(name);
    }

    private static void validateExists(String name) {
        Set<String> names = MENUS.keySet();
        if (!names.contains(name)) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    public String getName() {
        return name;
    }

    public MenuType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}
