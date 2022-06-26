package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.MovieFavourite;

@Repository
public interface MovieFavouriteReponsitory extends JpaRepository<MovieFavourite, Long> {
	@Query("select c from MovieFavourite c where c.user = ?1 and c.movie = ?2")
	MovieFavourite findByUserAndMovie(Long user, Long movie);
	
	@Query("select c from MovieFavourite c where c.user = ?1")
	List<MovieFavourite> findAllByUser(Long user);
}
