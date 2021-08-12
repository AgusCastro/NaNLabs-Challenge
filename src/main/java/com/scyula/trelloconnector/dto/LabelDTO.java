package com.scyula.trelloconnector.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelDTO {

    private String id;
    private String idBoard;
    private String color;
    private String name;

    public LabelDTO(String id) {
        this.id = id;
    }
}
