package com.futago.studioghibli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.repository.custom.FeatureFilmRepositoryCustom;

public interface FeatureFilmRepository extends JpaRepository<FeatureFilm, Long>, FeatureFilmRepositoryCustom {

	List<FeatureFilm> findByYear(String year);

	List<FeatureFilm> findByDirectorContaining(String director);

	@Query("select f from FeatureFilm f where f.titles.japanese like %:title% or f.titles.hepburn like %:title% or f.titles.english like %:title% or f.titles.portuguese like %:title%")
	List<FeatureFilm> findByTitle(@Param("title") String title);

}
