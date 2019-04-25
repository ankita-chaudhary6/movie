package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.model.Movie;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

public final transient MovieRepository movieRepo;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}


	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistsException {
		Optional<Movie> object = movieRepo.findById(movie.getId());
		if (object.isPresent()) {
			throw new MovieAlreadyExistsException("Movie already exists");
		}
		movieRepo.save(movie);
		return true;
	}

	@Override
	public boolean updateMovie(Movie updateMovie) throws MovieNotFoundException {
		Optional<Movie> movie = movieRepo.findById(updateMovie.getId());
		if (!movie.isPresent()) {
			throw new MovieNotFoundException("No movie present");
		}
		movieRepo.save(updateMovie);
		return true;
	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		Optional<Movie> movie = movieRepo.findById(id);
		if (!movie.isPresent()) {
			throw new MovieNotFoundException("No movie present");
		}
		movieRepo.deleteById(id);
		return true;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		Movie movieObject = null;
		Optional<Movie> movie = movieRepo.findById(id);
		if (!movie.isPresent()) {
			throw new MovieNotFoundException("No movie present");
		}
		movieObject = movie.get();
		return movieObject;
	}


	@Override
	public List<Movie> getMovies(String userId) {
		List<Movie> list=movieRepo.findByUserId(userId);
		return list;
	}

	

}
