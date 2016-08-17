package com.walmart.rest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.awt.PageAttributes.MediaType;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.collections.MappingChange.Map;

@Path("/")
public class RESTfulHelloWorld 
{
//	private static HashMap<Integer, Order> orders = new HashMap<Integer, Order>();
	@GET
//	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("text/html")
	public Response getStartingPage()
	{
		String output = "<h1>Hello World!<h1>" +
				"<p>RESTful Service is running ... <br>Ping @ " + new Date().toString() + "</p<br>";
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/order/")
	public Response getOrder(@QueryParam("id") String id, @QueryParam("name") String name, @Context UriInfo uriInfo, String content) {
	     MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters(); 
	     String nameParam = queryParams.getFirst("name");
	     String idParam = queryParams.getFirst("id");
	     String output = "Order ID is " + idParam + " and name is " + nameParam;
	     return Response.status(200).entity(output).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createOrder")
	public Response postOrder(String json) throws JsonParseException, JsonMappingException, IOException, SQLException{
		ObjectMapper mapper = new ObjectMapper();
		Order order = null;
		order = mapper.readValue(json, Order.class);
		String item_list = "";
		for (int i = 0; i < order.getItems().size(); i++) {
			if (i < order.getItems().size() - 1) item_list = item_list + order.getItems().get(i) + ", ";
			else item_list = item_list + order.getItems().get(i);
		}
		
		Connection connection = H2FileDatabaseExample.getDBConnection();
        Statement stmt = null;
//        connection.setAutoCommit(false);
        stmt = connection.createStatement();
		String result = "Order created and items are " + item_list;
		
		ResultSet rs = stmt.executeQuery("select * from PERSON");
        System.out.println("H2 Database inserted through Statement");
        while (rs.next()) {
            System.out.println("Id "+rs.getInt("id")+" Name "+rs.getString("name"));
        }
        stmt.close();
        connection.commit();
        
		return Response.status(201).entity(result).build();
	}
	
	@GET
	@Path("/getTrack")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrackInJSON() {

		Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");

		return track;

	}

	@POST
	@Path("/postTrack")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postTrackInJSON(String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		Track track = null;
		try {
			 track = mapper.readValue(json, Track.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = "Track saved : " + track.getSinger();
		return Response.status(201).entity(result).build();

	}
}
