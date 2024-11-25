package models;

import enums.AccompanimentType;
import enums.ChickenCutType;
import enums.Prices;
import enums.RostTypes;

public class ChickenStrips extends RoastedChicken {

    public ChickenStrips(RostTypes rostTypes, String description, int amount, AccompanimentType acc) {
        Acompaniment acompaniment = new Acompaniment(acc);
        super(rostTypes, ChickenCutType.STRIPS, description, amount, acompaniment);
    }
}