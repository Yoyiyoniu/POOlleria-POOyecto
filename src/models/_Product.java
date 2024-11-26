package models;

import utils.RandomString;

public class _Product {
    protected String id;
    protected float price;
    protected String description;

    public _Product() {
        this.id = RandomString.getRandomString();
        this.description = "";

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

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }
}
