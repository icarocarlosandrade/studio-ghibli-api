package com.futago.studioghibli.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futago.studioghibli.domain.entity.FeatureFilm;
import com.futago.studioghibli.domain.repository.custom.FeatureFilmRepositoryCustom;

@Repository
public interface FeatureFilmRepository extends JpaRepository<FeatureFilm, Long>, FeatureFilmRepositoryCustom {

}
