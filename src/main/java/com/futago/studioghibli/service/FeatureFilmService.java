package com.futago.studioghibli.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.repository.FeatureFilmRepository;
import com.futago.studioghibli.rest.filter.FeatureFilmFilter;

@Service
public class FeatureFilmService {

	private FeatureFilmRepository repository;

	@Autowired
	public FeatureFilmService(FeatureFilmRepository featureFilmRepository) {
		this.repository = featureFilmRepository;
	}

	public FeatureFilm findById(Long id) {
		FeatureFilm featureFilm = null;

		Optional<FeatureFilm> result = repository.findById(id);

		if (result.isPresent()) {
			featureFilm = result.get();
		}

		return featureFilm;
	}

	public List<FeatureFilm> findAll() {
		return repository.findAll();
	}

	public List<FeatureFilm> getByFilter(FeatureFilmFilter filter) {
		return repository.getByFilter(filter);
	}
}
