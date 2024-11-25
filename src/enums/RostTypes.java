package enums;

public enum RostTypes {
    WINE("Vino y se fue"),
    HONEY("Dulce como ella"),
    ROMERO("Especias y alfredo"),
    SPICY("Super picante");

    private final String stringValue;

    RostTypes(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
