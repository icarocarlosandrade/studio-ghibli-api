package com.futago.studioghibli.repository.custom;

import java.util.List;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.rest.filter.FeatureFilmFilter;

public interface FeatureFilmRepositoryCustom {

	List<FeatureFilm> getByFilter(FeatureFilmFilter filter);
}