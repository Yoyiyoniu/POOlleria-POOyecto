package enums;

public enum Accompaniment {
    SALAD("Ensalada de coll"),
    MACARRONI("Macarrones"),
    POTATO("Puere de papa"),
    GUACAMOLE("Guacamole"),
    TORTILLAS("Tortillas");

    private final String stringValue;

    Accompaniment(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
