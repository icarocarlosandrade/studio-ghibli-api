package com.futago.studioghibli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.repository.custom.FeatureFilmRepositoryCustom;

public interface FeatureFilmRepository extends JpaRepository<FeatureFilm, Long>, FeatureFilmRepositoryCustom {

}
