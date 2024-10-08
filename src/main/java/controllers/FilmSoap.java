package controllers;

import java.io.PrintWriter;

import java.util.ArrayList;

import com.google.gson.Gson;

import database.FilmDAO;
import models.Film;

public class FilmSoap {
//http://127.0.0.1:50526/wse/wsexplorer/wsexplorer.jsp?org.eclipse.wst.ws.explorer=0
	public String getAllFilms() {

		// Get the FilmDAO object
		FilmDAO dao = new FilmDAO();

		// Get a list of films from the FilmDAO object
		ArrayList<Film> allFilms = dao.getAllFilms();

		// Create a Gson object to convert the list of films to a JSON string
		Gson gson = new Gson();

		// Convert the list of films to a JSON string
		String json = gson.toJson(allFilms);

		// Return the JSON string
		return json;
	}

	public String insertFilm(String title, int year, String director, String stars, String review) {
		// Create a FilmDAO object
		FilmDAO dao = new FilmDAO();
		// Create a Film object
		Film f = new Film();

		// Set the values of the Film object
		f.setTitle(title);
		f.setDirector(director);
		f.setYear(year);
		f.setStars(stars);
		f.setReview(review);

		// Insert the Film object into the database using the FilmDAO object
		dao.insertFilm(f);

		// Return a success message
		return "Film inserted!";
	}

	public String updateFilm(int id, String title, int year, String director, String stars, String review) {
		// Create a FilmDAO object
		FilmDAO dao = new FilmDAO();
		// Create a Film object
		Film f = new Film();

		// Set the values of the Film object
		f.setId(id);
		f.setTitle(title);
		f.setDirector(director);
		f.setYear(year);
		f.setStars(stars);
		f.setReview(review);

		// Update the Film object into the database using the FilmDAO object
		dao.updateFilm(f);

		// Return a success message
		return "Film updated!";
	}

	public String deleteFilm(int id) {
		// Create a FilmDAO object
		FilmDAO dao = new FilmDAO();
		// Create a Film object
		Film f = new Film();

		// Set the values of the Film object
		f.setId(id);

		// Delete the Film object into the database using the FilmDAO object
		dao.deleteFilm(f.getId());

		// Return a success message
		return "Film deleted!";
	}

}
