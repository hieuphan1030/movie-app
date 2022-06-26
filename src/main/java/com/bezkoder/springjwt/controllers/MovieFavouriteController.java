package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.security.services.MovieFavouriteService;

import com.bezkoder.springjwt.models.MovieFavourite;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/test")
public class MovieFavouriteController {
	@Autowired
	private MovieFavouriteService movieFavouriteService;

	@GetMapping("/movie/favourite/{user}/{movie}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<MovieFavourite> getMovieFavourite(@PathVariable Long user, @PathVariable Long movie) {
		MovieFavourite movieFavourite = movieFavouriteService.getMovieFavourite(user, movie);
		return new ResponseEntity<>(movieFavourite, HttpStatus.OK);
	}

	@GetMapping("/movie/favourite/{user}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<MovieFavourite>> getAllMovieFavourite(@PathVariable Long user) {
		List<MovieFavourite> movieFavourite = movieFavouriteService.getAllMovieFavourite(user);
		return new ResponseEntity<>(movieFavourite, HttpStatus.OK);
	}

	@PostMapping("/movie/favourite")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<MovieFavourite> addProject(@RequestBody MovieFavourite movieFavourite) {

		return new ResponseEntity<>(movieFavouriteService.addMovieFavourite(movieFavourite), HttpStatus.OK);
	}

	@DeleteMapping("/movie/favourite/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public void deleteMovieFavourite(@PathVariable Long id) {
		movieFavouriteService.deleteMovieFavourite(id);
	}
}
