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

	public FeatureFilm save(FeatureFilm featureFilm) {
		featureFilm.setId(0L);
		return repository.save(featureFilm);
	}

	public FeatureFilm update(FeatureFilm featureFilm) {
		return repository.save(featureFilm);
	}

	public FeatureFilm findById(Long id) {
		Optional<FeatureFilm> result = repository.findById(id);

		if (result.isPresent()) {
			return result.get();
		}

		return null;
	}

	public List<FeatureFilm> findByFilter(FeatureFilmFilter filter) {
		return repository.findByFilter(filter);
	}
}
