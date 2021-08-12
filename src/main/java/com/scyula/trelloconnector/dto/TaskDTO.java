package com.scyula.trelloconnector.dto;

import com.scyula.trelloconnector.enums.CategoryType;
import com.scyula.trelloconnector.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@Data
public class TaskDTO {

    TaskType type;
    String title;
    String description;
    CategoryType category;
}
