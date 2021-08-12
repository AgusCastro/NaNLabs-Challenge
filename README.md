# NaNLabs Exercise Challenge 

This app has been done to connect the management team with the developer's team thought an API.

## Getting Started

The following steps are needed to download a working copy of the current project.

1. `git clone` the project
1. Install the IDE that you prefer.
1. Install jdk, at least version 1.8.0_261. Make sure to add the environment variable `JAVA_HOME` with the jdk directory
1. Download the binary for Maven 3.5.4 or 3.5.5 from [maven page](https://maven.apache.org/download.cgi)
1. Add `M2_HOME` as the Maven binary path, and `M2` as `%M2_HOME%/bin` as environment variables
1. Check your JRE and JDK is 1.8, otherwise install that version (and add them to your path)

## Configuration Files
You need to set the required variables in the application.properties file.

- trello.board_id               -- The board id on trello.
- trello.to_do_list_id          -- The id of the list which is your main column in the board
- trello.bug_list_id            -- The id of the list which is your bug column in the board
- trello.bug_label_id           -- The id of the label to indicates the card as a bug
- trello.category.maintenance   -- The id of the label to indicates the card as a maintenance task
- trello.category.research      -- The id of the label to indicates the card as research task
- trello.category.test          -- The id of the label to indicates the card as a test task

- trello.key                    -- key provided by Trello
- trello.token                  -- token provided by Trello

Enter here to know how to get your API key and token
**https://developer.atlassian.com/cloud/trello/guides/rest-api/api-introduction/**

### Executing
In the command line go to the `root` of the project, where is located the pom.xml file and then execute `mvn spring-boot:run`


Once you have the application running, you can use the app through your API tester or you can use Swagger-ui.
To use Swagger-ui, enter to *http://localhost:3000/swagger-ui.html* and try to do a post request to '/' endpoint.

Request example:
```
{
    "type":"issue",
    "title": "Create documentation",
    "description": "Create all the documentation of the application"
}
```

Response example:
```
{
    "id":"611478c33656b40750d48356",
    "name":"Create documentation",
    "idList":"6112ee60dbf1b853ec71f4ae",
    "desc":"Create all the documentation of the application",
    "url":"https://trello.com/c/TukOFpHo/31-create-documentation",
    "due":null,
    "idMembers":[],
    "labels":[],
    "idBoard":"6112e12f09c8e20f93d03aa9",
    "errorMessage":null
}
```

## Built With

* [Java](https://www.java.com/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Swagger](https://swagger.io/) - API documentation


## Authors

* **Agustin Castro**
