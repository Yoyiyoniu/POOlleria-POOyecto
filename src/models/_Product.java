package models;

import utils.RandomString;

public class _Product {
    protected String id;
    protected float price = 0;

    public _Product() {
        this.id = RandomString.getRandomString();

    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }
}
