package com.scyula.trelloconnector.mock;

import com.scyula.trelloconnector.dto.CardDTO;
import com.scyula.trelloconnector.dto.LabelDTO;
import com.scyula.trelloconnector.dto.MemberDTO;
import com.scyula.trelloconnector.exception.TrelloAPIException;
import com.scyula.trelloconnector.trello.TrelloAPI;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;

public class TrelloAPIMock implements TrelloAPI {


    @Override
    public CardDTO postCard(String url, MultiValueMap<String, String> params) throws TrelloAPIException {
        CardDTO card = new CardDTO();
        for ( String k : params.keySet()) {
            switch (k){
                case "name": card.setName(params.get(k).get(0));
                    break;
                case "desc": card.setDesc(params.get(k).get(0));
                    break;
                case "idList": card.setIdList(params.get(k).get(0));
                    break;
                case "idMembers": card.setIdMembers(params.get(k));
                    break;
                case "idLabels":
                    ArrayList<LabelDTO> arrayList = new ArrayList<>();
                    arrayList.add(new LabelDTO(params.get(k).get(0)));
                    card.setLabels(arrayList);
                    break;
                default:
                    break;
            }
        }
        return card;
    }

    @Override
    public MemberDTO[] getMembers(String url) throws TrelloAPIException {
        MemberDTO[] memberDTOs = new MemberDTO[1];
        memberDTOs[0] = new MemberDTO("gh5s89hs2cxv", "acastro", "Agustin Castro");
        return memberDTOs;
    }

    @Override
    public int getBugIssuesQuantity(String url) throws TrelloAPIException {
        return 3;
    }

}
