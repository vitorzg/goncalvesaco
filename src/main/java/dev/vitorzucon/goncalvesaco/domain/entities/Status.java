package dev.vitorzucon.goncalvesaco.domain.entities;

public enum Status {
    ON_HOLD(0),
    IN_PROGRESS(1),
    COMPLETED(2),
    CANCELLED(3);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Status fromValue(int value) {
        for (Status status : Status.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}