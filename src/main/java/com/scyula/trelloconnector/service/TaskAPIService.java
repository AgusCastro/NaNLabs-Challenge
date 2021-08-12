package com.scyula.trelloconnector.service;

import com.scyula.trelloconnector.dto.CardDTO;
import com.scyula.trelloconnector.dto.TaskDTO;
import com.scyula.trelloconnector.exception.TrelloAPIException;
import org.springframework.http.ResponseEntity;

public interface TaskAPIService {

    CardDTO createNewTask(TaskDTO taskDTO) throws TrelloAPIException;

}
