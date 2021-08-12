package com.scyula.trelloconnector.sample;

import com.scyula.trelloconnector.dto.TaskDTO;
import com.scyula.trelloconnector.enums.CategoryType;
import com.scyula.trelloconnector.enums.TaskType;

public class Samples {

    public static final TaskDTO taskSample = new TaskDTO(TaskType.task, "Clean the rocket", null, CategoryType.Maintenance);
    public static final TaskDTO bugSample = new TaskDTO(TaskType.bug, null,"Cockpit is not depressurizing correctly", null);
    public static final TaskDTO issueSample = new TaskDTO(TaskType.issue, "Send message", "Let pilots send messages to Central", null);

}
