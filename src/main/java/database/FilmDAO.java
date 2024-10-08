package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import models.Film;

import java.sql.*;


public class FilmDAO {
	
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
	String user = "***";
    String password = "***";
    String url = "***";

	public FilmDAO() {}

	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
	
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
   }

   public Film getFilmByID(int id){
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return oneFilm;
   }
   
   public ArrayList<Film> getFilmByYear(int year){
	   
	   ArrayList<Film> dataArray = new ArrayList<Film>();
		openConnection();
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where year like'%"+year+"%'";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	dataArray.add(oneFilm);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return dataArray;
   }
   
   
   public ArrayList<Film> getFilmByTitle(String title){
	   
	   ArrayList<Film> dataArray = new ArrayList<Film>();
		openConnection();
	    // Create select statement and execute it
		try{
			String selectSQL = "select * from films where title like'%"+title+"%'";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	dataArray.add(oneFilm);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	   return dataArray;
   }
      
   //Insert
   public void insertFilm (Film f) {
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
			String insertSQL = "insert into films (title,year,director,stars,review) values ('" + f.getTitle() + "','" + f.getYear() + "','" + f.getDirector() + "','" + f.getStars() + "','" + f.getReview() + "');";
		    boolean rs1 = stmt.execute(insertSQL);
	    // Retrieve the results
		 

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	   return;  
   }
   
   ///Update
   public void updateFilm (Film f) {
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
			String updateSQL = "update films set title = '" + f.getTitle() + "' , year = '" + f.getYear() + "' , director = '" + f.getDirector() + "' , stars = '" + f.getStars() + "' , review = '" + f.getReview() + "' WHERE id = " + f.getId() + ";";
			boolean rs1 = stmt.execute(updateSQL);
	    // Retrieve the results
		 

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	   return;
   }
   
   ///Delete
   public void deleteFilm(int id) {
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
			String deleteSQL = "delete from films where id ="+id;
			boolean rs1 = stmt.execute(deleteSQL);
	    // Retrieve the results
		 

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	   return;
   }
      
   public Collection searchFilms(String searchStr) {
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		String sql = "select * from films where searchStr like ?";
		
		openConnection();
		oneFilm = null;
		try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + searchStr + "%");
			java.sql.ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				oneFilm = getNextFilm(rs);
				allFilms.add(oneFilm);
			}
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return allFilms;
   }
}
