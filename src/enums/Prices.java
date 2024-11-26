package enums;

public enum Prices {
    ROASTED_CHICKEN(150),
    CHICKEN_STRIPS(230),
    ACCOMPANIMENT(24.99f);

    private final float price;

    Prices(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}
