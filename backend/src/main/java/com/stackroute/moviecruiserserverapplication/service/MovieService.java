package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;

import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.model.Movie;

public interface MovieService {

	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;

	boolean updateMovie(Movie updateMovie) throws MovieNotFoundException;

	boolean deleteMovieById(int id) throws MovieNotFoundException;

	Movie getMovieById(int id) throws MovieNotFoundException;

	List<Movie> getMovies(String userId);
}
