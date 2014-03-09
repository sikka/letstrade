package lt.beans;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DBUtil {
	
	
	public static void tryConnectingToDb(){
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{			
			 // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
		          .getConnection("jdbc:mysql://107.170.251.126:3306?"
		              + "user=ltm&password=password");

		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from tradeon.item");
		      writeResultSet(resultSet);
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
			      if (resultSet != null) {
			        resultSet.close();
			      }

			      if (statement != null) {
			        statement.close();
			      }

			      if (connect != null) {
			        connect.close();
			      }
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
	    }
	}
	
	private static void writeResultSet(ResultSet resultSet) throws SQLException {
	    // ResultSet is initially before the first data set
	    while (resultSet.next()) {
	      // It is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g. resultSet.getSTring(2);
	      String title = resultSet.getString("title");
	      String desc = resultSet.getString("description");
	      String points = resultSet.getString("points_asked");
	      
	      System.out.println("User: " + title);
	      System.out.println("Website: " + desc);
	      System.out.println("Summary: " + points);
	    }
	  }
	 private void writeMetaData(ResultSet resultSet) throws SQLException {
		//   Now get some metadata from the database
		// Result set get the result of the SQL query
		
		System.out.println("The columns in the table are: ");
		
		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
		  System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		}
	}
	 
	/**
	 * Adds a list of Items to a user's wishList
	 * @param userid
	 * @param items
	 * @throws SQLException
	 */
	public static void addItemsToUserList(int userid, List<Item> items) throws SQLException {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{			
			 // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
		          .getConnection("jdbc:mysql://107.170.251.126:3306?"
		              + "user=ltm&password=password");
		      
		      preparedStatement = connect
		          .prepareStatement("insert into tradeon.item values (?, ?, ?, ? , ?, ?, ?)");
		     
		      for(Item item: items){
		    	  Enumerations.ItemType itemType = item.getType();
		    	  preparedStatement.setString(1, item.getTitle());
			      preparedStatement.setInt(2, itemType.getValue());
			      preparedStatement.setString(3, item.getDescription());
			      preparedStatement.setInt(4, item.getPoints());
			      preparedStatement.setInt(5, item.getUserId());
			      preparedStatement.setInt(6, item.getPicId());
			      preparedStatement.setInt(7, item.getStatus().getValue());
			      preparedStatement.executeUpdate();
		      }

		}
		catch(SQLException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
			      if (resultSet != null) {
			        resultSet.close();
			      }

			      if (statement != null) {
			        statement.close();
			      }

			      if (connect != null) {
			        connect.close();
			      }
			    } catch (Exception e) {

			    }
	    }
	}

	public static void removeItemsFromUserList(int uid, List<Item> items) throws SQLException {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{			
			 // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
		          .getConnection("jdbc:mysql://107.170.251.126:3306?"
		              + "user=ltm&password=password");
		      
		      preparedStatement = connect
		          .prepareStatement("delete from tradeon.item where itemid = ?");
		     
		      for(Item item: items){
		    	  preparedStatement.setInt(1, item.getItemId());
			      preparedStatement.executeUpdate();
		      }

		}
		catch(SQLException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
			      if (resultSet != null) {
			        resultSet.close();
			      }
	
			      if (statement != null) {
			        statement.close();
			      }
	
			      if (connect != null) {
			        connect.close();
			      }
			    } catch (Exception e) {
				e.printStackTrace();
			    }
	    }
	}
}
