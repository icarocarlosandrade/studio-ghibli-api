package com.futago.studioghibli.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.repository.FeatureFilmRepository;

@Service
public class FeatureFilmService {

	@Autowired
	private FeatureFilmRepository featureFilmRepository;

	public FeatureFilm findById(Integer id) {
		FeatureFilm featureFilm = null;

		Optional<FeatureFilm> result = featureFilmRepository.findById(id);

		if (result.isPresent()) {
			featureFilm = result.get();
		} else {
			throw new RuntimeException("Film not found (Id = " + id + ")");
		}
		return featureFilm;
	}

	public List<FeatureFilm> findAll() {
		return featureFilmRepository.findAll();
	}
}
