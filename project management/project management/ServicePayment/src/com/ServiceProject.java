package com;

//Model
import model.Project;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

//SET PATH ..............................................
@Path("/ProjectService")
public class ServiceProject {
	
	// Object
	Project projectObj = new Project();

	// Read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readProject() {

		return projectObj.readProject();
	}
	
	// Insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(

			@FormParam("project_Id") String project_Id,
			@FormParam("project_Name") String project_Name,
			@FormParam("project_Cost")String project_Cost,
			@FormParam("Duration") String Duration,
			@FormParam("Author") String Author) {

		String output = projectObj.insertProject(Integer.parseInt(project_Id), project_Name, Double.valueOf(project_Cost), Integer.parseInt(Duration), Author);
		return output;
	}

	// Update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String projectData) {

		// Convert the input string to a JSON object
		JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();

		// Read the values from the JSON object
		String project_Id = projectObject.get("project_Id").getAsString();
		String project_Name = projectObject.get("project_Name").getAsString();
		String project_Cost = projectObject.get("project_Cost").getAsString();
		String Duration = projectObject.get("Duration").getAsString();
		String Author =projectObject.get(" Author").getAsString();
		

		String output = projectObj.updateProject(project_Id, project_Name, project_Cost, Duration, Author);

		return output;
	}

	
	// Delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String projectData) {

		// Convert the input string to an XML document
		Document doc = Jsoup.parse(projectData, "", Parser.xmlParser());

		// Read the value from the element <ProductID>
		String project_Id = doc.select("project_Id").text();

		String output = projectObj.deleteProject(project_Id);
		return output;
	}

}
