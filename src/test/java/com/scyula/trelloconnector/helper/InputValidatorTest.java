package com.scyula.trelloconnector.helper;

import com.scyula.trelloconnector.dto.TaskDTO;
import com.scyula.trelloconnector.enums.CategoryType;
import com.scyula.trelloconnector.enums.TaskType;
import com.scyula.trelloconnector.exception.MissingValuesException;
import com.scyula.trelloconnector.sample.Samples;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @Test
    void checkValidInput() {
        boolean thrown = false;
        try {
            InputValidator.checkInput(Samples.bugSample);
            InputValidator.checkInput(Samples.issueSample);
            InputValidator.checkInput(Samples.taskSample);
        } catch (MissingValuesException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void checkInvalidInputs() {

        TaskDTO bugSample1 = new TaskDTO(null, "Clean the rocket", null, null);
        assertThrows(MissingValuesException.class,
                () -> InputValidator.checkInput(bugSample1),
                "InputValidator.checkInput to throw MissingValuesException but it didn't");

        TaskDTO bugSample2 = new TaskDTO(TaskType.bug, null , null, null);
        assertThrows(MissingValuesException.class,
                () -> InputValidator.checkInput(bugSample2),
                "InputValidator.checkInput to throw MissingValuesException but it didn't");

        TaskDTO taskSample1 = new TaskDTO(TaskType.task, null, null, CategoryType.Maintenance);
        assertThrows(MissingValuesException.class,
                () -> InputValidator.checkInput(taskSample1),
                "InputValidator.checkInput to throw MissingValuesException but it didn't");

        TaskDTO taskSample2 = new TaskDTO(TaskType.task, "Clean the rocket", null, null);
        assertThrows(MissingValuesException.class,
                () -> InputValidator.checkInput(taskSample2),
                "InputValidator.checkInput to throw MissingValuesException but it didn't");

        assertThrows(MissingValuesException.class,
                () -> InputValidator.checkInput(new TaskDTO(TaskType.task, null, null, null)),
                "InputValidator.checkInput to throw MissingValuesException but it didn't");

        TaskDTO issueSample1 = new TaskDTO(TaskType.issue, "Send message", null, null);
        assertThrows(MissingValuesException.class,
                () -> InputValidator.checkInput(issueSample1),
                "InputValidator.checkInput to throw MissingValuesException but it didn't");

        TaskDTO issueSample2 = new TaskDTO(TaskType.issue, null, "Let pilots send messages to Central", null);
        assertThrows(MissingValuesException.class,
                () -> InputValidator.checkInput(issueSample2),
                "InputValidator.checkInput to throw MissingValuesException but it didn't");

        assertThrows(MissingValuesException.class,
                () -> InputValidator.checkInput(new TaskDTO(TaskType.issue, null, null, null)),
                "InputValidator.checkInput to throw MissingValuesException but it didn't");
    }


}
