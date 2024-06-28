package syed.abdullah.demo.entity;

public enum OrderStatus {
    SHIPPED("Shipped"),
    RESOLVED("Resolved"),
    CANCELLED("Cancelled"),
    ON_HOLD("On Hold"),
    DISPUTED("Disputed"),
    IN_PROCESS("In Process");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    // Optional: Create a static method to convert from String to enum
    public static OrderStatus fromString(String text) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getStatus().equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    public String getStatus() {
        return status;
    }
}

