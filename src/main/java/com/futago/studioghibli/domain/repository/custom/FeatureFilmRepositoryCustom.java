package com.futago.studioghibli.domain.repository.custom;

import java.util.List;

import com.futago.studioghibli.domain.entity.FeatureFilm;
import com.futago.studioghibli.api.filter.FeatureFilmFilter;

public interface FeatureFilmRepositoryCustom {

	List<FeatureFilm> findAllByFilter(FeatureFilmFilter filter);
}