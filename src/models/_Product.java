package models;

import utils.RandomString;

public class _Product {
    protected String id;
    protected float price = 0;
    protected String description;
    protected int amount;

    public _Product(String description, int amount) {
        this.id = RandomString.getRandomString();
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
