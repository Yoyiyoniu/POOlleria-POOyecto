package models;

public class _Product {
    private String id;
    private float price;
    private String description;
    private int amount;

    public _Product(String id, float price, String description, int amount) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.amount = amount;

    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }
}
