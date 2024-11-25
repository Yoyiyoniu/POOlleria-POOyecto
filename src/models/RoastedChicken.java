// src/models/RoastedChicken.java
package models;

import enums.ChickenCutType;
import enums.RostTypes;

import java.io.Serializable;

public class RoastedChicken extends _Product implements Serializable {

    private RostTypes rostTypes;
    private ChickenCutType cutType;
    private Acompaniment accompaniment;

    public RoastedChicken(
            RostTypes rostTypes, ChickenCutType chickenCutType, String description, int amount, Acompaniment acc) {
        super(description, amount);

        this.accompaniment = acc;
        this.rostTypes = rostTypes;
        this.cutType = chickenCutType;
        this.setPrice(150);
    }

    public void setAccompaniment(Acompaniment accompaniment) {
        this.accompaniment = accompaniment;
    }

    public void setCutType(ChickenCutType cutType) {
        this.cutType = cutType;
    }

    public void setRostTypes(RostTypes rostTypes) {
        this.rostTypes = rostTypes;
    }

    public Acompaniment getAccompaniment() {
        return accompaniment;
    }

    public ChickenCutType getCutType() {
        return cutType;
    }

    public RostTypes getRostTypes() {
        return rostTypes;
    }

    @Override
    public String toString(){
        return this.rostTypes + " | "+ this.cutType + " | " +
                this.accompaniment + " | " + this.id + " | " +
                this.price + this.description + " | " +
                this.amount + " | " +  this.accompaniment;
    }
}