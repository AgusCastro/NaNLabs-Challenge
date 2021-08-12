package com.scyula.trelloconnector.trello;

import com.scyula.trelloconnector.dto.CardDTO;
import com.scyula.trelloconnector.dto.MemberDTO;
import com.scyula.trelloconnector.exception.TrelloAPIException;
import org.springframework.util.MultiValueMap;

public interface TrelloAPI {
    CardDTO postCard(String url, MultiValueMap<String, String> params) throws TrelloAPIException;
    MemberDTO[] getMembers(String url) throws TrelloAPIException;
    int getBugIssuesQuantity(String url) throws TrelloAPIException;
}
