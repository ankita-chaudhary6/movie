package com.stackroute.moviecruiserserverapplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.model.Movie;
import com.stackroute.moviecruiserserverapplication.service.MovieService;
import io.jsonwebtoken.Jwts;
@CrossOrigin
@RestController
@RequestMapping("/api/movie")
public class MovieController {

	private MovieService movieService;

	@Autowired
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}


	@PostMapping
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie,HttpServletRequest request) {
		ResponseEntity<?> responseEntity;
		String userId=getUserId(request);
		System.out.println(userId);
		try {
			movie.setUserId(userId);

			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
			return responseEntity;
		} catch (MovieAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("movie already exists", HttpStatus.CONFLICT);
			return responseEntity;
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("server not responding", HttpStatus.INTERNAL_SERVER_ERROR);
			return responseEntity;
		}

	}

	@PutMapping
	public ResponseEntity<?> updateMovie(@RequestBody Movie updateMovie) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.updateMovie(updateMovie);
			responseEntity = new ResponseEntity<Movie>(updateMovie, HttpStatus.OK);
			return responseEntity;
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("movie not found", HttpStatus.NOT_FOUND);
			return responseEntity;
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("server not responding", HttpStatus.INTERNAL_SERVER_ERROR);
			return responseEntity;

		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(id);
			responseEntity = new ResponseEntity<String>("movie successfully deleted", HttpStatus.OK);
			return responseEntity;
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("movie not found", HttpStatus.NOT_FOUND);
			return responseEntity;
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("server not responding", HttpStatus.INTERNAL_SERVER_ERROR);
			return responseEntity;
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") Integer id) {
		ResponseEntity<?> responseEntity;
		try {
			Movie movie = movieService.getMovieById(id);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
			return responseEntity;
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("movie not found", HttpStatus.NOT_FOUND);
			return responseEntity;
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("server not responding", HttpStatus.INTERNAL_SERVER_ERROR);
			return responseEntity;
		}

	}

	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovies(HttpServletRequest request) {
		String userId=getUserId(request);
		return new ResponseEntity<List<Movie>>(movieService.getMovies(userId),HttpStatus.OK);

		}
		/*
		 * } catch (Exception e) { responseEntity = new
		 * ResponseEntity<String>("server not responding",
		 * HttpStatus.INTERNAL_SERVER_ERROR); return responseEntity; }
		 */

	
	public String getUserId(HttpServletRequest request)
	{
		final String authHeader = request.getHeader("authorization");
    	final String token = authHeader.substring(7);
    	String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
	    return userId;
	}

}
