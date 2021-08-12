package com.scyula.trelloconnector.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchDTO {

    private List<CardDTO> cards;

}
