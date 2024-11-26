package models;

import enums.AccompanimentType;
import enums.ChickenCutType;
import enums.RostTypes;

import java.io.Serializable;

public class RoastedChicken extends _Product implements Serializable {

    private RostTypes rostTypes;
    private ChickenCutType cutType;
    private AccompanimentType accompaniment;

    public RoastedChicken(
            RostTypes rostTypes, ChickenCutType chickenCutType,
            float price, String description, AccompanimentType accompaniment) {
        this.price = price;
        this.description = description;
        this.accompaniment = accompaniment;
        this.rostTypes = rostTypes;
        this.cutType = chickenCutType;
    }

    public void setCutType(ChickenCutType cutType) {
        this.cutType = cutType;
    }

    public void setRostTypes(RostTypes rostTypes) {
        this.rostTypes = rostTypes;
    }

    public void setAccompaniment(AccompanimentType accompaniment) {
        this.accompaniment = accompaniment;
    }

    public AccompanimentType getAccompaniment() {
        return accompaniment;
    }

    public ChickenCutType getCutType() {
        return cutType;
    }

    public RostTypes getRostTypes() {
        return rostTypes;
    }

    @Override
    public String toString() {
        return id + "~" + rostTypes + "~" + cutType + "~" + accompaniment + "~" + price + "~" + description;
    }
}