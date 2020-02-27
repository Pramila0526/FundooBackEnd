package com.bridgelabz.fundooappbackend.elastic.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooappbackend.note.model.Note;
import com.bridgelabz.fundooappbackend.user.service.UserServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;

/*********************************************************************************************************
 * @author :Pramila Mangesh Tawari 
 * Purpose :Elastic Service Implementation to
 * 	        perform fast operations
 *
 ***********************************************************************************************************/

@Service
public class ElasticServiceImplementation implements ElasticService {

	static Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	
	/** 
	 * Java RestHighLevelClient works on top of RestLowLevelClient
	 *	Its main goal is to expose API specific methods, that accept request object as an argument
	 *	and returns response object
	 *
	 * */
	private RestHighLevelClient client;

	//Object Mapper used to convert our Model Document object to a Map Object
	private ObjectMapper objectMapper;

	@Autowired
	public ElasticServiceImplementation(RestHighLevelClient client, ObjectMapper objectMapper) {
		this.client = client;
		this.objectMapper = objectMapper;
	}

	static String INDEX = "notedata"; // Database Name
	static String TYPE = "note"; // Table Name

/**
 * @return Method to Create A Note in Elastic Search Database
 *
 **********************************************************************************************************/
	public String createNote(Note note) throws Exception {

		System.out.println("in elastic");

		// Converts Our Values to json in Key Value Pair Format
		Map<String, Object> documentMapper = objectMapper.convertValue(note, Map.class);

		// It Converts Id into String and Checks The id From that Database and that praticular Table and stores source into index
		// Index request to index a typed JSON document into a specific index and make it searchable
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, String.valueOf(note.getId())).source(documentMapper); 

		logger.info("****" + indexRequest);

		logger.info("Index Requested");		
		
		// The portion of an HTTP request to Elasticsearch that can be manipulated without changing Elasticsearch's behavior.
		IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

		logger.info("****" + indexResponse);

		logger.info("note is :" + indexResponse.getResult().name());
		
		// The change that occurred to the document.
	  	return indexResponse.getResult().name();
	}

/**
 * @return Method to Search a Note By Id Using Elastic Search
 *
 ********************************************************************************************************/
	public Note searchById(String noteId) throws Exception {
		
		// Requsting to find the data by id from particular database and table
		// A request to get a document (its source) from an index based on its type (optional) and id
		GetRequest getRequest = new GetRequest(INDEX, TYPE, noteId);

		// Getting Response from Client
		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
		
		logger.info("Searching By Id");
		
		Map<String, Object> resultMap = getResponse.getSource();

		return objectMapper.convertValue(resultMap, Note.class);
	}

/**
 * @return Method to Update a note In Elastic Search
 *
 ********************************************************************************************************/
	public String updateNote(Note note) throws Exception {

		// Stores the Note Object by Id in resultDocument
		Note resultDocument = searchById(String.valueOf(note.getId()));
		
		// Converts Our Values to json in Key Value Pair Format
		Map<String, Object> documentMapper = objectMapper.convertValue(note, Map.class);

		// It Converts Id into String and Checks The id From that Database and that praticular Table
		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, String.valueOf(resultDocument.getId()));

		// Converts Java Code into JSON Format
		// Sets the doc to use for updates when a script is not specified.
		updateRequest.doc(documentMapper);

		// The portion of an HTTP request to Elasticsearch that can be manipulated without changing Elasticsearch's behavior.
		 UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

		logger.info("Updating By Id");

		// The change that occurred to the document.
		return updateResponse.getResult().name();
	}

/**
 * @return Method to Delete a note In Elastic Search
 *
 ********************************************************************************************************/
	public String deleteNote(Note noteId) throws Exception {

		logger.info("delete");

		// Requesting to Delete by id from Praticular Database and Table
		// A request to delete a document from an index based on its type and id
		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, String.valueOf(noteId));// .index(INDEX).type(TYPE);

		// Gives Response accourding to Operation
		// The response of the delete action.
		DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);

		// The change that occurred to the document.
		return response.getResult().name();
	}

/**
 * @author Method to get Result of Search
 *
 ********************************************************************************************************/
	private List<Note> getSearchResult(SearchResponse response) {

		SearchHit[] searchHit = response.getHits().getHits();
		
		List<Note> note = new ArrayList<>();

		if (searchHit.length > 0) {

			logger.info("Search Result");

			Arrays.stream(searchHit)
					.forEach(hit -> note.add(objectMapper.convertValue(hit.getSourceAsMap(), Note.class)));
		}
		return note;
	}

/**
 * @author Method to Search a note by Title
 *
 ********************************************************************************************************/
	public List<Note> searchByTitle(String title) throws Exception {

		// Search Request Object
		// A request to execute search against one or more indices
		SearchRequest searchRequest = new SearchRequest();
		
		// A search source builder allowing to easily build search source
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		// A Query that matches documents matching boolean combinations of other queries
		// It checks whther it is present in elastic search Database or not
		// matchQuery-Creates a match query with type "BOOLEAN" for the provided field name and text.
		QueryBuilder queryBuilder = QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("title", "*" + title + "*"));
		
		logger.info("Search By Title");
		 
		searchSourceBuilder.query(queryBuilder);

		// Requesting defined data
		searchRequest.source(searchSourceBuilder);

		// Gives Search Response according to our request
		// A response of a search request.
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

		return getSearchResult(response);
	}

/**
 * @author Method to Search a note by any Word
 *
 ********************************************************************************************************/
	public List<Note> searchByWord(String word) throws Exception {

		// Search Request Object
		// A request to execute search against one or more indices
		SearchRequest searchRequest = new SearchRequest();
	
		// A search source builder allowing to easily build search source
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		// A Query that matches documents matching boolean combinations of other queries
		// It checks whther it is present in elastic search Database or not
		QueryBuilder queryBuilder = QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("word", "*" + word + "*"));

		searchSourceBuilder.query(queryBuilder);

		// Requesting defined data
		searchRequest.source(searchSourceBuilder);
		
		logger.info("Search By Word");

		// Gives Serach Response according to our request
		// A response of a search request.
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

		return getSearchResult(response);
	}

/**
 * @author Method to Delete a Note
 *
 ********************************************************************************************************/
	public String deleteNote(int noteId) throws Exception {

		logger.info("Delete From Elastic ");
	
		// Request to delete bu id from particular database and table
		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, String.valueOf(noteId));// .index(INDEX).type(TYPE);
		
		// Delete Response from client
		DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);

		return response.getResult().name();
	}
}

/********************************************************************************************************/











/*
 * public List<Note> autocomplete(String prefixString) { SearchRequest
 * searchRequest = new SearchRequest(INDEX); CompletionSuggestionBuilder
 * suggestBuilder = new CompletionSuggestionBuilder("xsV"); // Note 1
 * 
 * suggestBuilder.size(size) .prefix(prefixString, Fuzziness.ONE) // Note 2
 * .skipDuplicates(true) .analyzer("standard");
 * 
 * SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); // _search
 * sourceBuilder.suggest(new SuggestBuilder().addSuggestion("sAVf",
 * suggestBuilder)); searchRequest.source(sourceBuilder);
 * 
 * SearchResponse response; try { response = client.search(searchRequest);
 * return getSearchResult(response); // Note 3 } catch (IOException ex) { throw
 * new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
 * "Error in ES search"); } }
 */