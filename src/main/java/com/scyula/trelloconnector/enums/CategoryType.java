package com.scyula.trelloconnector.enums;

public enum CategoryType {
    Maintenance("maintenance"),
    Research("research"),
    Test("test");

    private CategoryType(String category){
        this.category = category;
    }

    private String category;


    public String getCategory() {
        return category;
    }
}
