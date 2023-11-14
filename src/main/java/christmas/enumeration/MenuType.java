package christmas.enumeration;

public enum MenuType {
    MUSHROOM_SOUP("APPETIZER", "양송이수프", 6000),
    TAPAS("APPETIZER", "타파스", 5500),
    CAESAR_SALAD("APPETIZER", "시저샐러드", 8000),
    T_BONE_STEAK("MAIN", "티본스테이크", 55000),
    BARBEQUE_RIP("MAIN", "바비큐립", 54000),
    SEAFOOD_PASTA("MAIN", "해산물파스타", 35000),
    CHRISTMAS_PASTA("MAIN", "크리스마스파스타", 25000),
    CHOCOLATE_CAKE("DESSERT", "초코케이크", 15000),
    ICE_CREAM("DESSERT", "아이스크림", 5000),
    ZERO_COKE("DESSERT", "제로콜라", 3000),
    RED_WINE("DESSERT", "레드와인", 60000),
    CHAMPAGNE("DESSERT", "샴페인", 25000);

    private String category;
    private String name;
    private int cost;

    MenuType(String category, String name, int cost) {
        this.category = category;
        this.name = name;
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public static MenuType getByName(String name) {
        for(MenuType menuType : values()) {
            if(menuType.name.equals(name)) {
                return menuType;
            }
        }
        throw new IllegalArgumentException(ExceptionType.INVALID_MENU_FORMAT.getMessage());
    }
}
