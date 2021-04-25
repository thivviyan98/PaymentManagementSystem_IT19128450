package model;

import java.sql.*;

public class Project {

	// DB Connection
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project_management", "root", "");

			// For testing
			System.out.print("DB Successfully connected");
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.print("DB not connected");
		}

		return con;
	}

	// Insert
	public String insertProject(int project_Id  , String project_Name, Double project_Cost, int Duration,String Author) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into project(`project_Id`,`project_Name`,`project_Cost`,`Duration`,`Author`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, project_Id);
			preparedStmt.setString(2,project_Name);
			preparedStmt.setDouble(3, project_Cost);
			preparedStmt.setInt(4, Duration);
			preparedStmt.setString(5, Author);
			
		

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Insertion successful";

		} catch (Exception e) {
			output = "Insertion Unsuccess";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	// Read
	public String readProject() {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>project_Id</th>" + "<th>project_Name</th><th>project_Cost</th>"
					+ "<th>Duration</th>" + "<th>Author</th></tr>";

			String query = "select * from project";

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String project_Id  = Integer.toString(rs.getInt("project_Id"));
				String project_Name = rs.getString("project_Name");
				String project_Cost = Integer.toString(rs.getInt("project_Cost"));
				String Duration = Integer.toString(rs.getInt("Duration"));
				String Author = rs.getString("Author");
				
			
				

				// Add into the html table
				output += "<tr><td>" + project_Id + "</td>";
				output += "<td>" + project_Name + "</td>";
				output += "<td>" + project_Cost+ "</td>";
				output += "<td>" + Duration + "</td>";
				output += "<td>" + Author + "</td>";

			}

			con.close();

			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	// Update
	public String updateProject(String project_Id ,String project_Name, String project_Cost, String Duration, String Author) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE project SET project_Id=?,project_Name=?,project_Cost=?, Duration=?,Author=? WHERE project_Id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(4, Integer.parseInt(project_Id));
			preparedStmt.setString(2, project_Name);
			preparedStmt.setDouble(3, Double.parseDouble(project_Cost));
			preparedStmt.setInt(4, Integer.parseInt(Duration));
			preparedStmt.setString(5, Author);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Successfully Updated";

		} catch (Exception e) {
			output = "Updating unsuccesful .";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	// Delete
	public String deleteProject(String project_Id) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from project where project_Id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(project_Id));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";

		} catch (Exception e) {
			output = "Error while deleting the Product Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
