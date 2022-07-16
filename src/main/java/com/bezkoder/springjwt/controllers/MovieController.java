package com.bezkoder.springjwt.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.models.ECategory;
import com.bezkoder.springjwt.models.Image;
import com.bezkoder.springjwt.models.Movie;
import com.bezkoder.springjwt.repository.CategoryReponsitory;
import com.bezkoder.springjwt.repository.ImageReponsitory;
import com.bezkoder.springjwt.repository.MovieReponsitory;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/test")
public class MovieController {

	@Autowired
	MovieReponsitory movieReponsitory;

	@Autowired
	CategoryReponsitory categoryReponsitory;


	@Autowired
	ImageReponsitory imageReponsitory;

	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String title) {
		try {
			List<Movie> movies = new ArrayList<Movie>();
			if (title == null)
			{
				movieReponsitory.findAll().forEach(movies::add);	
			}
			else
				movieReponsitory.findByNameContaining(title).forEach(movies::add);
			if (movies.isEmpty()) {
				return new ResponseEntity<>(new ArrayList<Movie>(),HttpStatus.OK);
			}

			return new ResponseEntity<>(movies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/movies/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
		Optional<Movie> tutorialData = movieReponsitory.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/movies/image/{id}")
	public ResponseEntity<List<Image>> getImageById(@PathVariable("id") long id) {
		try {
			List<Image> images = new ArrayList<Image>();
			imageReponsitory.findAllByMovie(id).forEach(images::add);
			if (images.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(images, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("/movies")
	public ResponseEntity<Movie> createMovies(@RequestBody Movie movie) {
		try {

			Set<Category> categorys = new HashSet<>();

			Category categoryMovie = categoryReponsitory.findByName(ECategory.HoatHinh)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			categorys.add(categoryMovie);

			Movie _movie = movieReponsitory.save(new Movie(movie.getName(), movie.getAge(), movie.getVote(),
					movie.getIntroduce(), movie.getDate(), movie.getTurmover(), movie.getTime(), movie.getKey_youtube(), movie.getName_youtube(),movie.getUrl(), categorys));

			return new ResponseEntity<>(_movie, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/movies/image")
	public ResponseEntity<Image> createImages(@RequestBody Image image) {
		try {
			Image _image = imageReponsitory.save(new Image(image.getUrl(), image.getMovie()));
			return new ResponseEntity<>(_image, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@PutMapping("/movies/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
		Optional<Movie> movieData = movieReponsitory.findById(id);

		if (movieData.isPresent()) {
			Movie _movie = movieData.get();
			_movie.setName(movie.getName());
			_movie.setAge(movie.getAge());
			_movie.setVote(movie.getVote());
			_movie.setIntroduce(movie.getIntroduce());
			_movie.setDate(movie.getDate());
			_movie.setTurmover(movie.getTurmover());
			_movie.setTime(movie.getTime());
			_movie.setUrl(movie.getUrl());

			return new ResponseEntity<>(movieReponsitory.save(_movie), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/movies/{id}")
	public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") long id) {
		try {
			movieReponsitory.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
