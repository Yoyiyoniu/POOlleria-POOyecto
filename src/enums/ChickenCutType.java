package enums;

public enum ChickenCutType {
    COMPLETE("Completo"),
    BONELESS("Deshuesado"),
    STRIPS("Tiras dobles");

    private final String stringValue;

    ChickenCutType(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
