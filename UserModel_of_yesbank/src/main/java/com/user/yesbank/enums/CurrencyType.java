package com.user.yesbank.enums;

public enum CurrencyType {

    INR("Indian Rupee"),
    USD("US Dollar"),
    EUR("Euro");

    private final String description;

    CurrencyType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}