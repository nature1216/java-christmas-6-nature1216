package christmas.enumeration;

public enum BadgeType {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NONE("없음",0);
    private String name;
    private int amount;

    BadgeType(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
    public int getAmount() {
        return amount;
    }
}
