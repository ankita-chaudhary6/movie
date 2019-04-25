package com.stackroute.moviecruiserserverapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.model.Movie;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

public class MovieServiceImplTest {

	@Mock
	private transient MovieRepository movieRepo;
	
	private transient Movie movie;
	
	@InjectMocks
	private transient MovieServiceImpl movieServiceImpl;
	
	transient Optional<Movie> options;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		movie=new Movie("id1","POC","good movie","www.abc.com","2015-03-31",45.5,112,"100");
		movie.setId(1);
		options=Optional.of(movie);
	}
	@Test
	public void testMockCreation() {
		assertNotNull("JpaRepository creation failed: use @InjectMocks on movieServiceImpl", movie);
	}

	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException {
		when(movieRepo.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		assertTrue("saving movie passed", flag);
		verify(movieRepo, times(1)).save(movie);
	}
	
	@Test(expected = MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
	}


	@Test
	public void testUpdateMovie() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		movie.setComments("not so good movie");
		final boolean flag = movieServiceImpl.updateMovie(movie);
		assertTrue("updating movie failed", flag);
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}
	


	@Test
	public void testGetMovieById() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		final Movie movie1 = movieServiceImpl.getMovieById(1);
		assertEquals("fetching movie by id failed", movie, movie1);
		verify(movieRepo, times(1)).findById(movie.getId());
	}

	@Test
	public void testGetAllMovies() throws MovieNotFoundException {
		final List<Movie> movies = new ArrayList<>();
		movies.add(movie);
		when(movieRepo.findByUserId("100")).thenReturn(movies);
		final List<Movie> movies1 = movieServiceImpl.getMovies("100");
		assertEquals(movies, movies1);
		verify(movieRepo, times(1)).findByUserId(Mockito.anyString());
	}



}
