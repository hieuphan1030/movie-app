package com.bezkoder.springjwt.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.MovieFavourite;
import com.bezkoder.springjwt.repository.MovieFavouriteReponsitory;

@Service
public class MovieFavouriteService {
	@Autowired
	private MovieFavouriteReponsitory movieFavouriteReponsitory;

	public List<MovieFavourite> getAllMovieFavourite(Long user) {
		return movieFavouriteReponsitory.findAllByUser(user);
	}

	public MovieFavourite getMovieFavourite(Long user, Long movie) {
		return movieFavouriteReponsitory.findByUserAndMovie(user, movie);
	}

	public MovieFavourite addMovieFavourite(MovieFavourite movieFavourite) {
		return movieFavouriteReponsitory.save(movieFavourite);
	}

	public void deleteMovieFavourite(Long id) {
		movieFavouriteReponsitory.deleteById(id);
	}

}
