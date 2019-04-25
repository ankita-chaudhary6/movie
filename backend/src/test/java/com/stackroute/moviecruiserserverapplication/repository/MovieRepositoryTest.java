package com.stackroute.moviecruiserserverapplication.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiserserverapplication.MoviecruiserserverapplicationApplication;
import com.stackroute.moviecruiserserverapplication.model.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = MoviecruiserserverapplicationApplication.class)
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private transient MovieRepository movieRepository;

	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Test
	public void testSaveMovie() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", 45.5, 112));
		final Movie movie = movieRepository.getOne(1);
		assertThat(movie.getId()).isEqualTo(1);
	}

	@Test
	public void testUpdateMovie() throws Exception {
		final Movie movie1 = movieRepository
				.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", 45.5, 112));
		final Movie movie = movieRepository.findById(movie1.getId()).orElse(null);
		assertThat(movie.getTitle()).isEqualTo("superman");
		movie.setComments("bad movie");
		movieRepository.save(movie);
		final Movie tempMovie = movieRepository.findById(movie1.getId()).orElse(null);
		assertThat("bad movie").isEqualTo(tempMovie.getComments());
	}

	@Test
	public void testDeleteMovie() throws Exception {
		Movie savedMovie = movieRepository
				.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", 45.5, 112));
		final Movie movie = movieRepository.getOne(savedMovie.getId());
		assertEquals("superman", movie.getTitle());
		movieRepository.delete(movie);
		assertEquals(Optional.empty(), movieRepository.findById(1));
	}

	@Test
	public void testGetMovie() throws Exception {
		Movie savedMovie = movieRepository
				.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", 45.5, 112));
		final Movie movie = movieRepository.getOne(savedMovie.getId());
		assertEquals("superman", movie.getTitle());
	}

	@Test
	public void testGetMyMovies() throws Exception {
		movieRepository.save(new Movie(1, "superman", "good movie", "www.abc.com", "2015-03-23", 45.5, 112));
		movieRepository.save(new Movie(2, "superman-1", "good movie", "www.cde.com", "2015-03-23", 45.5, 112));
		final List<Movie> movies = movieRepository.findAll();
		assertEquals("superman", movies.get(0).getTitle());
	}

}
