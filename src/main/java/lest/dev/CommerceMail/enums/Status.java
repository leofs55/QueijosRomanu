package lest.dev.CommerceMail.enums;

public enum Status {
    OPEN,
    SOLD;

    public static Status fromString(String value) {
        if (value == null) return null;
        try {
            return Status.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; // ou lance uma exceção customizada
        }
    }
}
