package com.scyula.trelloconnector.service.impl;

import com.scyula.trelloconnector.dto.CardDTO;
import com.scyula.trelloconnector.dto.MemberDTO;
import com.scyula.trelloconnector.dto.TaskDTO;
import com.scyula.trelloconnector.enums.CategoryType;
import com.scyula.trelloconnector.exception.TrelloAPIException;
import com.scyula.trelloconnector.service.TaskAPIService;
import com.scyula.trelloconnector.trello.TrelloAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Random;


@Component
public class TaskAPIServiceImplementation implements TaskAPIService {

    @Autowired
    private Environment env;
    private static final Logger LOG = LoggerFactory.getLogger(TaskAPIServiceImplementation.class);

    /**
     * These values are read from the application properties.
     */
    @Value("${trello.board_id}")
    private String boardId;
    @Value("${trello.to_do_list_id}")
    private String toDoListId;
    @Value("${trello.bug_list_id}")
    private String bugListId;
    @Value("${trello.bug_label_id}")
    private String bugLabelId;

    public static final String TRELLO_URL = "https://api.trello.com/1";
    public static final String CARD_URL = "/cards";
    public static final String MEMBERS_URL = "/members";
    public static final String BOARD_URL = "/boards/";
    public static final String SEARCH_URL = "/search";

    private final TrelloAPI trelloAPI;

    @Autowired
    public TaskAPIServiceImplementation(TrelloAPI trelloAPI) {
        this.trelloAPI = trelloAPI;
    }

    /**
     * Method that create a new card in the board with the input.
     * @param taskDTO the input with all the required values to create a new card.
     * @return The new card generated.
     * @throws TrelloAPIException after processing response.
     */
    @Override
    public CardDTO createNewTask(TaskDTO taskDTO) throws TrelloAPIException {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        LOG.info("Filling params");
        switch (taskDTO.getType()){
            case task:
                multiValueMap.set("idLabels", getCatogryId(taskDTO.getCategory()));
                multiValueMap.set("name", taskDTO.getTitle());
                multiValueMap.set("idList", toDoListId);
                break;
            case bug:
                multiValueMap.set("idLabels", bugLabelId);
                multiValueMap.set("idList", bugListId);
                multiValueMap.set("desc", taskDTO.getDescription());
                multiValueMap.set("idMembers", getRandomMember());
                multiValueMap.set("name", generateBugTitle(taskDTO.getDescription()));
                break;
            default:
                multiValueMap.set("name", taskDTO.getTitle());
                multiValueMap.set("desc", taskDTO.getDescription());
                multiValueMap.set("idList", toDoListId);
                break;
        }
        LOG.info("params filled - {}", multiValueMap);
        return trelloAPI.postCard(TRELLO_URL+CARD_URL, multiValueMap);
    }

    /**
     * Receive the category and search the id of the category to return it.
     * @return id of the category.
     * @param category the category which id is needed.
     */
    private String getCatogryId(CategoryType category) {
        return env.getProperty("trello.category_label."+category.getCategory());
    }

    /**
     * This method generates the title of a bug issue using the description.
     * @return The title of the bug issue.
     * @param description Description of the issue, used to generate the title.
     */
    private String generateBugTitle(String description) throws TrelloAPIException {
        return "BUG-"+getRandomWord(description)+"-"+ getNextBugNumber();
    }

    /**
     * Receives the description and select one word randomly from it.
     * @return One word randomly.
     * @param description the description of the issue.
     */
    private String getRandomWord(String description) {
        String[] array = description.split(" ");
        return array[new Random().nextInt(array.length)];
    }

    /**
     * Method that consults Trello API to get quantity of bug issues.
     * @return The next number to bug issues.
     * @throws TrelloAPIException after processing response.
     */
    private String getNextBugNumber() throws TrelloAPIException {
        return String.valueOf(trelloAPI.getBugIssuesQuantity(TRELLO_URL+SEARCH_URL)+1);
    }

    /**
     * Method that consults Trello API to get all members of the board an select one of then randomly.
     * @return The id of the selected member.
     * @throws TrelloAPIException after processing response.
     */
    private String getRandomMember() throws TrelloAPIException {
        MemberDTO[] members = trelloAPI.getMembers(TRELLO_URL+BOARD_URL+ boardId +MEMBERS_URL);
        return members.length == 0 ? "" : members[new Random().nextInt(members.length)].getId();
    }


}
