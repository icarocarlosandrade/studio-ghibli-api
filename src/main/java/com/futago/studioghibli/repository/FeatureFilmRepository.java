package com.futago.studioghibli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futago.studioghibli.entity.FeatureFilm;

public interface FeatureFilmRepository extends JpaRepository<FeatureFilm, Integer> {

	List<FeatureFilm> findByYear(String year);

	List<FeatureFilm> findByDirectorContaining(String director);

}
