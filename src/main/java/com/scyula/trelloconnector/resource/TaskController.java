package com.scyula.trelloconnector.resource;

import com.scyula.trelloconnector.exception.MissingValuesException;
import com.scyula.trelloconnector.dto.CardDTO;
import com.scyula.trelloconnector.dto.TaskDTO;
import com.scyula.trelloconnector.exception.TrelloAPIException;
import com.scyula.trelloconnector.helper.InputValidator;
import com.scyula.trelloconnector.service.TaskAPIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@Api(value = "TaskController - NanLab exercise")
@RequestMapping("/")
public class TaskController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    private final TaskAPIService taskAPIService;

    @Autowired
    public TaskController(TaskAPIService taskAPIService) {
        this.taskAPIService = taskAPIService;
    }

    @PostMapping
    @ApiOperation(value = "Retrieves the card generated.")
    public ResponseEntity<CardDTO> createTask(@ApiParam(value = "Data to create the card.") @RequestBody TaskDTO task) {
        LOG.info("New task incoming: {}", task);
        try {
            InputValidator.checkInput(task);
            return ResponseEntity.ok(this.taskAPIService.createNewTask(task));
        } catch (TrelloAPIException | MissingValuesException e) {
            return ResponseEntity.internalServerError().body(new CardDTO(e.getMessage()));
        }
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "Redirect the user to the API Documentation.")
    public RedirectView redirect() {
        return new RedirectView("/swagger-ui.html");
    }

}
