package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Image;

@Repository
public interface ImageReponsitory extends JpaRepository<Image, Long> {
	@Query("select c from Image c where c.movie = ?1")
	List<Image> findAllByMovie(Long movie);
}
