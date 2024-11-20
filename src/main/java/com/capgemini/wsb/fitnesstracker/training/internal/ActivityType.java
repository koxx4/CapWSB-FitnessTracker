package com.capgemini.wsb.fitnesstracker.training.internal;


/**
 * Typ wyliczeniowy reprezentujący różne rodzaje aktywności fizycznej.
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    private final String displayName;

    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Zwraca nazwę wyświetlaną aktywności.
     *
     * @return nazwa wyświetlana aktywności
     */
    public String getDisplayName() {
        return displayName;
    }
}