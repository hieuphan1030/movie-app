package com.bezkoder.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.ECategory;
import com.bezkoder.springjwt.models.Category;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category, Long> {
	Optional<Category> findByName(ECategory name);
}
