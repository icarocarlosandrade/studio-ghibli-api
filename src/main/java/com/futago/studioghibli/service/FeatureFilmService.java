package com.futago.studioghibli.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.repository.FeatureFilmRepository;

@Service
public class FeatureFilmService {

	private FeatureFilmRepository featureFilmRepository;

	@Autowired
	public FeatureFilmService(FeatureFilmRepository featureFilmRepository) {
		this.featureFilmRepository = featureFilmRepository;
	}

	public FeatureFilm findById(Long id) {
		FeatureFilm featureFilm = null;

		Optional<FeatureFilm> result = featureFilmRepository.findById(id);

		if (result.isPresent()) {
			featureFilm = result.get();
		}

		return featureFilm;
	}

	public List<FeatureFilm> findAll() {
		return featureFilmRepository.findAll();
	}

	public List<FeatureFilm> findByYear(String year) {
		return featureFilmRepository.findByYear(year);
	}

	public List<FeatureFilm> findByDirector(String director) {
		return featureFilmRepository.findByDirectorContaining(director);
	}

	public List<FeatureFilm> findByTitle(String title) {
		return featureFilmRepository.findByTitle(title);
	}
}
