package com.scyula.trelloconnector.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private String id;
    private String name;
    private String idList;
    private String desc;
    private String url;
    private Date due;
    private List<String> idMembers;
    private List<LabelDTO> labels;
    private String idBoard;

    private String errorMessage;

    public CardDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
