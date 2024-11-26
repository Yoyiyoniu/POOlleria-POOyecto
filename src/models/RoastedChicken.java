package models;

import enums.Accompaniment;
import enums.ChickenCutType;
import enums.RostTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoastedChicken extends _Product implements Serializable {

    private RostTypes rostTypes;
    private ChickenCutType cutType;
    private List<Accompaniment> accompaniment = new ArrayList<>();

    public RoastedChicken(
            RostTypes rostTypes, ChickenCutType chickenCutType, String id,
            float price, String description, int amount, Accompaniment acc) {
        super(id, price, description, amount);
        accompaniment.add(acc);
        this.rostTypes = rostTypes;
        this.cutType = chickenCutType;
    }

    public void setAccompaniment(Accompaniment acc) {
        accompaniment.add(acc);
    }

    public void setCutType(ChickenCutType cutType) {
        this.cutType = cutType;
    }

    public void setRostTypes(RostTypes rostTypes) {
        this.rostTypes = rostTypes;
    }

    public List<Accompaniment> getAccompaniment() {
        return accompaniment;
    }

    public ChickenCutType getCutType() {
        return cutType;
    }

    public RostTypes getRostTypes() {
        return rostTypes;
    }
}
