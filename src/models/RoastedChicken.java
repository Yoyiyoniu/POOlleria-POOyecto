// src/models/RoastedChicken.java
package models;

import enums.AccompanimentType;
import enums.ChickenCutType;
import enums.RostTypes;

import java.io.Serializable;

public class RoastedChicken extends _Product implements Serializable {

    private RostTypes rostTypes;
    private ChickenCutType cutType;
    private final AccompanimentType accompaniment;
    private String description;

    public RoastedChicken(
            RostTypes rostTypes, ChickenCutType chickenCutType, String description,int price , AccompanimentType acc) {
        this.description = description;
        this.accompaniment = acc;
        this.rostTypes = rostTypes;
        this.cutType = chickenCutType;
        this.price = price;
    }

    public void setCutType(ChickenCutType cutType) {
        this.cutType = cutType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRostTypes(RostTypes rostTypes) {
        this.rostTypes = rostTypes;
    }

    public ChickenCutType getCutType() {
        return cutType;
    }

    public String getDescription() {
        return description;
    }

    public RostTypes getRostTypes() {
        return rostTypes;
    }

    @Override
    public String toString(){
        return id + "~" + rostTypes + "~" + cutType + "~" + accompaniment + "~" + price + "~" + description;
    }
}