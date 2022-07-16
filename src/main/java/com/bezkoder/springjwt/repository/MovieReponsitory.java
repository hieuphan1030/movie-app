package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Movie;

@Repository
public interface MovieReponsitory extends JpaRepository<Movie, Long> {
	List<Movie> findByNameContaining(String title);
}
