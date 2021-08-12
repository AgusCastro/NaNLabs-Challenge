package com.scyula.trelloconnector.trello.impl;

import com.scyula.trelloconnector.dto.CardDTO;
import com.scyula.trelloconnector.dto.MemberDTO;
import com.scyula.trelloconnector.dto.SearchDTO;
import com.scyula.trelloconnector.exception.TrelloAPIException;
import com.scyula.trelloconnector.trello.TrelloAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TrelloAPIImplementation implements TrelloAPI {

    private static final Logger LOG = LoggerFactory.getLogger(TrelloAPIImplementation.class);

    /**
     * These values are read from the application properties.
     */
    @Value("${trello.token}")
    private String trelloToken;
    @Value("${trello.key}")
    private String trelloKey;

    private static final String SEARCH_BY_BUG_LABEL="label:red";


    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Method that generate the request to create a card, set the url and apply the params, then return the card created.
     * @param url The url where create the card
     * @param params All the params needed to the request
     * @return The new card generated.
     * @throws TrelloAPIException after processing response.
     */
    @Override
    public CardDTO postCard(String url, MultiValueMap<String, String> params) throws TrelloAPIException{
        LOG.info("PostCard method started - {} - {}", url, params);
        //Set the URL to consult and params.
        String uri = generateUriComponent(url)
                .queryParams(params)
                .build(false)
                .toUriString();
        //Consult Trello API Endpoint.
        try{
            ResponseEntity<CardDTO> response = restTemplate.postForEntity(uri, null, CardDTO.class);
            LOG.info("Response - {}", response.getStatusCode());
            return response.getBody();
        }catch (RestClientException restClientException){
            throw new TrelloAPIException( "Error occurred - " + restClientException.getMessage(), restClientException);
        }
    }

    /**
     * Method that generate the request to get all the members of a board then return an array which contain the members.
     * @param url The url where get the members.
     * @return An array withh the members of the board.
     * @throws TrelloAPIException after processing response.
     */
    @Override
    public MemberDTO[] getMembers(String url) throws TrelloAPIException {
        LOG.info("getMembers method started - {}", url);
        //Set the URL to consult.
        String uri = generateUriComponent(url)
                .build(false)
                .toUriString();
        //Consult Trello API Endpoint.
        try{
            ResponseEntity<MemberDTO[]> response = restTemplate.getForEntity(uri, MemberDTO[].class);
            LOG.info("Response - {}", response.getStatusCode());
            return response.getBody();
        }catch (RestClientException restClientException){
            throw new TrelloAPIException( "Error occurred - " + restClientException.getMessage(), restClientException);
        }
    }

    /**
     * Method that generate the request to get the bug issues quantity and returns that number.
     * @param url The url where get the quantity of bug issues.
     * @return The number of bug issues created.
     * @throws TrelloAPIException after processing response.
     */
    @Override
    public int getBugIssuesQuantity(String url) throws TrelloAPIException {
        LOG.info("PostCard method started - {}", url);
        //Set the URL to consult and params.
        String uri = generateUriComponent(url)
                .queryParam("query", SEARCH_BY_BUG_LABEL)
                .build(false)
                .toUriString();
        //Consult Trello API Endpoint.
        try{
            ResponseEntity<SearchDTO> response = restTemplate.getForEntity(uri, SearchDTO.class);
            LOG.info("Response - {}", response.getStatusCode());
            if(response.hasBody()){
                SearchDTO aux = response.getBody();
                // if body exists, check nulls and cards attribute to safe access and get the size.
                if (aux != null && aux.getCards() != null){
                    return aux.getCards().size();
                }
            }
            return 0;
        }catch (RestClientException restClientException){
            throw new TrelloAPIException( "Error occurred - " + restClientException.getMessage(), restClientException);
        }
    }

    /**
     * Method that generate and UriComponentsBuilder object, fill it with the token and key and returns that object.
     * @param url The url where will be done the request.
     * @return The object UriComponentsBuilder with url, the Trello token and key applied.
     */
    private UriComponentsBuilder generateUriComponent(String url){
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("token", trelloToken)
                .queryParam("key", trelloKey);
    }
}
