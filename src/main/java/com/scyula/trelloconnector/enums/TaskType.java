package com.scyula.trelloconnector.enums;

public enum TaskType {
    issue("issue"),
    bug("bug"),
    task("task");

    private TaskType(String type){
        this.label = type;
    }

    private String label;

    public String getLabel() {
        return label;
    }

}
