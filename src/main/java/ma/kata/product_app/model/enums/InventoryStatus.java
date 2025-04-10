package ma.kata.product_app.model.enums;

import lombok.Getter;

@Getter
public enum InventoryStatus {
    INSTOCK("INSTOCK"),
    LOWSTOCK("LOWSTOCK"),
    OUTOFSTOCK("OUTOFSTOCK");

    private final String value;

    InventoryStatus(String value) {
        this.value = value;
    }

    public static InventoryStatus fromValue(String value) {
        for (InventoryStatus status : InventoryStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid inventory status: " + value);
    }
}
