// src/models/Acompaniment.java
package models;

import enums.AccompanimentType;
import enums.Prices;

public class Acompaniment extends _Product{
    private AccompanimentType type;

    public Acompaniment(AccompanimentType type) {
        super(type.toString(), 1);
        this.type = type;
        this.description = type.toString();
        this.setPrice(Prices.ACCOMPANIMENT.getPrice());
    }

    public AccompanimentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.type + " | " + this.description + " | " + this.amount + " | " + this.price;
    }
}