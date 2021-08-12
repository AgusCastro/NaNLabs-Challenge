package com.scyula.trelloconnector.helper;

import com.scyula.trelloconnector.exception.MissingValuesException;
import com.scyula.trelloconnector.dto.TaskDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputValidator {

    private static final String MISSING_VALUES_ERROR = "Task with missing values";
    private static final Logger LOG = LoggerFactory.getLogger(InputValidator.class);

    private InputValidator(){}



    /**
     * This method checks the fields in the input object to guarantee which all the necessary values exist to operate.
     * @param taskDTO Input object to be checked.
     * @throws MissingValuesException This exception is thrown in case of a required value is missing.
     */
    public static void checkInput(TaskDTO taskDTO) throws MissingValuesException {
        if(taskDTO.getType() == null){
            LOG.error("Task type is missing");
            throw new MissingValuesException("Task type is empty.");
        }
        switch (taskDTO.getType()){
            case task:
                if(taskDTO.getTitle() == null || taskDTO.getCategory() == null){
                    LOG.error(MISSING_VALUES_ERROR);
                    throw new MissingValuesException(MISSING_VALUES_ERROR);
                }
                break;
            case bug:
                if(taskDTO.getDescription() == null){
                    LOG.error(MISSING_VALUES_ERROR);
                    throw new MissingValuesException(MISSING_VALUES_ERROR);
                }
                break;
            default:
                if(taskDTO.getTitle() == null || taskDTO.getDescription() == null){
                    LOG.error(MISSING_VALUES_ERROR);
                    throw new MissingValuesException(MISSING_VALUES_ERROR);
                }
                break;
        }

    }

}
