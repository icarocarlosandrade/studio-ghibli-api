package com.futago.studioghibli.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.futago.studioghibli.api.dto.FeatureFilmDTO;
import com.futago.studioghibli.api.dto.mapper.FeatureFilmMapper;
import com.futago.studioghibli.api.exception.NotFoundException;
import com.futago.studioghibli.api.filter.FeatureFilmFilter;
import com.futago.studioghibli.domain.entity.FeatureFilm;
import com.futago.studioghibli.domain.repository.FeatureFilmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureFilmService {

	private final FeatureFilmRepository repository;
	private final FeatureFilmMapper mapper;

	public FeatureFilmDTO save(FeatureFilmDTO featureFilmDTO) {
		FeatureFilm featureFilm = mapper.toEntity(featureFilmDTO);

		repository.save(featureFilm);
		return getDTOById(featureFilm.getId());
	}

	public List<FeatureFilmDTO> getAllByFilter(FeatureFilmFilter filter) {
		List<FeatureFilmDTO> featureFilmDTOList = new ArrayList<>();
		List<FeatureFilm> featureFilmList = repository.findByFilter(filter);
		featureFilmList.forEach(featureFilm -> featureFilmDTOList.add(mapper.toDTO(featureFilm)));
		return featureFilmDTOList;
	}

	public FeatureFilmDTO getDTOById(Long id) {
		return mapper.toDTO(getById(id));
	}

	private FeatureFilm getById(Long id) {
		Optional<FeatureFilm> optionalEntity = repository.findById(id);

		if (!optionalEntity.isPresent()) {
			throw new NotFoundException("Feature film not found (Id = " + id + ")");
		}

		return optionalEntity.get();
	}
}
