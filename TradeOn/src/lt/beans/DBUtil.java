package lt.beans;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.*;

import lt.beans.Enumerations.ItemType;
import lt.beans.Enumerations.*;

public class DBUtil {
	
	/**
	 * Dummy function to test the connection.
	 */
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
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");

		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from item");
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
	
	/**
	 * Prints the values in a resultset.
	 * @param resultSet
	 * @throws SQLException
	 */
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
	
	/**
	 * Prints the metadata about the ResultSet.
	 * @param resultSet
	 * @throws SQLException
	 */
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
	 *Inserts a list of items in the item table.
	 * @param userid
	 * @param items
	 * @throws SQLException
	 */
	public static void addItemsToUserList(int userid, List<Item> items) throws SQLException {
		// TODO Auto-generated method stub
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{			
			 // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");
		      
		      preparedStatement = connect
		          .prepareStatement("insert into item values (?, ?, ?, ? , ?, ?, ?)");
		     
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

	/**
	 * Deletes a record specified by the itemId in the list of items.
	 * @param uid
	 * @param items
	 * @throws SQLException
	 */
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
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");
		      
		      preparedStatement = connect
		          .prepareStatement("delete from item where itemid = ?");
		     
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
			  if (preparedStatement != null){
				  preparedStatement.close();
			  }
			} 
			catch (Exception e) {
			    	e.printStackTrace();
		    }
	    }
	}

	
	/**
	 * Saves the state of the offer to db.
	 * @param offer
	 */
	public static void saveOfferStateToDb(Offer offer) throws SQLException{
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{			
			 // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");
		      
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from offer where oid = "+offer.getOfferId());
		      
		      if(resultSet.first()){
		    	  preparedStatement = connect.prepareStatement("update offer set senderId =?, receiverId = ?, offer_status= ? where oid = "+offer.getOfferId());
		    	  preparedStatement.setInt(0, offer.getSenderId());
		    	  preparedStatement.setInt(1, offer.getReceiverId());
		    	  preparedStatement.setInt(2, offer.getStatus().getValue());
		    	  preparedStatement.executeUpdate();
		      }
		      else{
		    	  preparedStatement = connect.prepareStatement("insert into offer values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		    	  preparedStatement.setInt(0, offer.getSenderId());
		    	  preparedStatement.setInt(1, offer.getReceiverId());
		    	  preparedStatement.setInt(2, offer.getStatus().getValue());
		    	  int id = preparedStatement.executeUpdate();
		    	  offer.setOfferId(id);
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
			  if (preparedStatement != null){
				  preparedStatement.close();
			  }
			} 
			catch (Exception e) {
			    	e.printStackTrace();
		    }
	    }
	}

	
	
	/**
	 * Makes necessary changes to the db when an offer is made.
	 * 
	 * @param offer
	 */
	public static void makeOffer(Offer offer) throws SQLException {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			// DB connection
			 connect = DriverManager
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");
			 
			 // CREATE THE OFFER RECORD.
			 preparedStatement = connect.prepareStatement("insert into offer values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			 preparedStatement.setInt(0, offer.getSenderId());
			 preparedStatement.setInt(1, offer.getReceiverId());
			 preparedStatement.setInt(1, offer.getStatus().getValue());
			 int id = preparedStatement.executeUpdate();
			 offer.setOfferId(id);
			 
			 // CREATE THE ITEMS-IN-OFFER RECORDS
			 for(Item item : offer.getItemsToBeSent()){
				 preparedStatement = connect.prepareStatement("insert into ItemsInOffer values(?, ?, ?, ?, ?)");
				 preparedStatement.setInt(0, offer.getOfferId());
				 preparedStatement.setInt(1, Enumerations.OfferItemSide.SENDER.getValue());
				 preparedStatement.setInt(2, offer.getOfferItemtype().getValue());
				 preparedStatement.setInt(3, item.getItemId());
				 preparedStatement.setInt(4, item.getPoints());
				 preparedStatement.executeUpdate();
			 }
			 for(Item item : offer.getItemsToBeReceived()){
				 preparedStatement = connect.prepareStatement("insert into ItemsInOffer values(?, ?, ?, ?, ?)");
				 preparedStatement.setInt(0, offer.getOfferId());
				 preparedStatement.setInt(1, Enumerations.OfferItemSide.RECEIVER.getValue());
				 preparedStatement.setInt(2, offer.getOfferItemtype().getValue());
				 preparedStatement.setInt(3, item.getItemId());
				 preparedStatement.setInt(4, item.getPoints());
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
			  if (preparedStatement != null){
				  preparedStatement.close();
			  }
			} 
			catch (Exception e) {
			    	e.printStackTrace();
		    }
	    }
	}
	
	
	// ACCEPT OFFER
	/**
	 * Changes the state of the given offer to accepted.
	 * Changes the item userids and ItemStatus types. for the items in the sendList and receiveList.
	 * Changes the status of all other offers in which these items were involved to invalid.
	 * @param offer
	 */
	public static void acceptOffer(Offer offer) throws SQLException{
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{			
			 // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");
		      
		      // SET THE OFFER STATUS TO ACCEPTED
		      preparedStatement = connect.prepareStatement("update offer set offer_status = ? where offerid = ?");
		      preparedStatement.setInt(0, Enumerations.OfferStatus.ACCEPTED.getValue());
		      preparedStatement.executeUpdate();
		      
		      // CHANGE ITEM ATTRIBUTES FOR SENT AS WELL AS RECEIVED ITEMS
		      
		      for(Item item : offer.getItemsToBeSent()){
		    	  preparedStatement = connect.prepareStatement("update item set need_have_traded = ?, uid=? where itemId = ?");
		    	  preparedStatement.setInt(0, Enumerations.ItemStatus.TRADED.getValue());
		    	  preparedStatement.setInt(1, offer.getReceiverId());
		    	  preparedStatement.setInt(2, item.getItemId());
		    	  
		    	  preparedStatement.executeUpdate();
		      }
		      for(Item item : offer.getItemsToBeReceived()){
		    	  preparedStatement = connect.prepareStatement("update item set need_have_traded = ?, uid=? where itemId = ?");
		    	  preparedStatement.setInt(0, Enumerations.ItemStatus.TRADED.getValue());
		    	  preparedStatement.setInt(1, offer.getSenderId());
		    	  preparedStatement.setInt(2, item.getItemId());
		    	  
		    	  preparedStatement.executeUpdate();
		      }
		      
		      
		      
		      // SET STATUS FOR ALL OTHER ORDERS INCLUDING THESE ITEMS AS INVALID
		      for(Item item : offer.getItemsToBeSent()){
		    		  preparedStatement = connect.prepareStatement("update offer set offer_status = ? where itemId = ? and offer_status!=?");
			    	  preparedStatement.setInt(0, Enumerations.OfferStatus.INVALID.getValue());
			    	  preparedStatement.setInt(1, item.getItemId());
			    	  // IF THE OFFER STATUS IS ACCEPTED, WE DO NOT NEED TO INVALIDATE IT.
			    	  preparedStatement.setInt(2, Enumerations.OfferStatus.ACCEPTED.getValue());
			    	  preparedStatement.executeUpdate();
		      }
		      for(Item item : offer.getItemsToBeReceived()){
	    		  preparedStatement = connect.prepareStatement("update offer set offer_status = ? where itemId = ? and offer_status!=?");
		    	  preparedStatement.setInt(0, Enumerations.OfferStatus.INVALID.getValue());
		    	  preparedStatement.setInt(1, item.getItemId());
		    	  // IF THE OFFER STATUS IS ACCEPTED, WE DO NOT NEED TO INVALIDATE IT.
		    	  preparedStatement.setInt(2, Enumerations.OfferStatus.ACCEPTED.getValue());
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
			  if (preparedStatement != null){
				  preparedStatement.close();
			  }
			} 
			catch (Exception e) {
			    	e.printStackTrace();
		    }
	    }
	}

	
	
	public static void rejectOffer(Offer offer) throws SQLException {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			// DB connection
			 connect = DriverManager
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");
			 
			 // SET OFFER STATUS TO CANCELLED
			 preparedStatement = connect.prepareStatement("update offer set offer_status=? where offerid = ?");
			 preparedStatement.setInt(0, Enumerations.OfferStatus.CANCELLED.getValue());
			 preparedStatement.setInt(1, offer.getOfferId());
			 preparedStatement.executeUpdate();
			 
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
			  if (preparedStatement != null){
				  preparedStatement.close();
			  }
			} 
			catch (Exception e) {
			    	e.printStackTrace();
		    }
	    }
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static List<User> getAllUsers() {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> users = new ArrayList<User>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			// DB connection
			 connect = DriverManager
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");
			 
			 // CREATE STATEMENT
			 statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from user");
		      
		      while(resultSet.next()){
		    	  User user = new User();
		    	  user.setUid(resultSet.getInt("uid"));
		    	  user.setUserName(resultSet.getString("uname"));
		    	  user.setEmailAddress(resultSet.getString("email"));
		    	  user.setPoints(resultSet.getInt("points"));
		    	  user.setProfileImageId(resultSet.getInt("profile_img_id"));
		    	  ResultSet imgResultSet = statement.executeQuery("Select uri from images where imageId="+resultSet.getInt("profile_img_id"));
		    	  if(imgResultSet.first()){
		    		  user.setProfileImageUri(imgResultSet.getString(0));
		    	  }
		    	  
		    	  
		    	  users.add(user);
		      }
		      
		      return users;
		}
		catch(SQLException e){
			e.printStackTrace();
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
			  if (preparedStatement != null){
				  preparedStatement.close();
			  }
			} 
			catch (Exception e) {
			    	e.printStackTrace();
		    }
	    }
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Item> getAllItems() {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Item> items = new ArrayList<Item>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			// DB connection
			 connect = DriverManager
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");
			 
			 // CREATE STATEMENT
			 statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from item");
		      
		      while(resultSet.next()){
		    	  Item item = new Item();
		    	  item.setItemId(resultSet.getInt("itemId"));
		    	  item.setTitle(resultSet.getString("title"));
		    	  item.setType(ItemType.fromValue(resultSet.getInt("product_or_skill")));
		    	  item.setDescription(resultSet.getString("description"));
		    	  item.setPoints(resultSet.getInt("points_asked"));
		    	  item.setUserId(resultSet.getInt("uid"));
		    	  item.setPicId(resultSet.getInt("picID"));
		    	  item.setStatus(ItemStatus.fromValue(resultSet.getInt("need_have_traded")));
		    	  ResultSet imgResultSet = statement.executeQuery("Select uri from images where imageId="+resultSet.getInt("picID"));
		    	  if(imgResultSet.first()){
		    		  item.setPicUri(imgResultSet.getString(0));
		    	  }
		    	  items.add(item);
		      }
		      
		      return items;
		}
		catch(SQLException e){
			e.printStackTrace();
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
			  if (preparedStatement != null){
				  preparedStatement.close();
			  }
			} 
			catch (Exception e) {
			    	e.printStackTrace();
		    }
	    }
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Offer> getAllOffers() {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Offer> offers = new ArrayList<Offer>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			// DB connection
			 connect = DriverManager
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");
			 
			 // CREATE STATEMENT
			 statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from item");
		      
		      while(resultSet.next()){
		    	  Offer offer = new Offer();
		    	  offer.setOfferId(resultSet.getInt("itemId"));
		    	  offer.setSenderId(resultSet.getInt("senderId"));
		    	  offer.setReceiverId(resultSet.getInt("receiverId"));
		    	  offer.setStatus(OfferStatus.fromValue(resultSet.getInt("offer_status")));
		    	  offers.add(offer);
		      }
		      
		      return offers;
		}
		catch(SQLException e){
			e.printStackTrace();
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
			  if (preparedStatement != null){
				  preparedStatement.close();
			  }
			} 
			catch (Exception e) {
			    	e.printStackTrace();
		    }
	    }
		return null;
	}

	public static List<ItemsInOffer> getItemsInOffer() {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ItemsInOffer> itemsInOffers = new ArrayList<ItemsInOffer>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			// DB connection
			 connect = DriverManager
			          .getConnection("jdbc:mysql://107.170.251.126:3306/tradeon?user=ltm&password=password");
			 
			 // CREATE STATEMENT
			 statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from itemsInOffer");
		      
		      while(resultSet.next()){
		    	  ItemsInOffer itemsInOffer = new ItemsInOffer();
		    	  itemsInOffer.setOfferId(resultSet.getInt("itemId"));
		    	  itemsInOffer.setItemId(resultSet.getInt("senderId"));
		    	  itemsInOffer.setPoints(resultSet.getInt("receiverId"));
		    	  itemsInOffer.setItemType(ItemType.fromValue(resultSet.getInt("item_type")));
		    	  itemsInOffer.setOfferItemType(resultSet.getString("offer_status").charAt(0));
		    	  itemsInOffers.add(itemsInOffer);
		      }
		      
		      return itemsInOffers;
		}
		catch(SQLException e){
			e.printStackTrace();
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
			  if (preparedStatement != null){
				  preparedStatement.close();
			  }
			} 
			catch (Exception e) {
			    	e.printStackTrace();
		    }
	    }
		return null;
	}
}
