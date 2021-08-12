package com.scyula.trelloconnector.service.impl;

import com.scyula.trelloconnector.dto.CardDTO;
import com.scyula.trelloconnector.exception.TrelloAPIException;
import com.scyula.trelloconnector.mock.TrelloAPIMock;
import com.scyula.trelloconnector.sample.Samples;
import com.scyula.trelloconnector.trello.TrelloAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskAPIServiceImplementationTest {


    TrelloAPI trelloAPI = new TrelloAPIMock();

    @Test
    void createNewTask() {
        boolean thrown = false;
        TaskAPIServiceImplementation taskAPIService = new TaskAPIServiceImplementation(trelloAPI);

        try {
            CardDTO bugCard = taskAPIService.createNewTask(Samples.bugSample);
            assertEquals(Samples.bugSample.getDescription(), bugCard.getDesc());
            assertEquals(1, bugCard.getIdMembers().size());
            assertTrue(checkValidName(bugCard.getName(), bugCard.getDesc()));

        } catch (TrelloAPIException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    private boolean checkValidName(String name, String desc) {
        String[] split = name.split("-");
        if(!"BUG".equals(split[0])){
            return false;
        }
        if(!desc.contains(split[1])){
            return false;
        }
        if(!split[2].equals("4")){
            return false;
        }
        return true;
    }
}
