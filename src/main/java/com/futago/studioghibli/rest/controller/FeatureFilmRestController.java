package com.futago.studioghibli.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.rest.dto.FeatureFilmDTO;
import com.futago.studioghibli.rest.dto.FeatureFilmMapper;
import com.futago.studioghibli.rest.exception.NotFoundException;
import com.futago.studioghibli.rest.filter.FeatureFilmFilter;
import com.futago.studioghibli.service.FeatureFilmService;

@RestController
@RequestMapping("/featurefilm")
public class FeatureFilmRestController {

	private FeatureFilmService featureFilmService;

	@Autowired
	public FeatureFilmRestController(FeatureFilmService featureFilmService) {
		this.featureFilmService = featureFilmService;
	}

	@GetMapping("{id}")
	public FeatureFilmDTO findById(@PathVariable Long id) {
		FeatureFilm featureFilm = featureFilmService.findById(id);

		if (featureFilm == null) {
			throw new NotFoundException("Feature film not found (Id = " + id + ")");
		}

		// Isso deveria ser injetado?
		FeatureFilmMapper featureFilmMapper = new FeatureFilmMapper();

		FeatureFilmDTO filmDTO = featureFilmMapper.toDTO(featureFilm);
		return filmDTO;
	}

	@GetMapping
	public List<FeatureFilmDTO> findFeatureFilmByFilter(
			@RequestParam(value = "filter", required = false) FeatureFilmFilter filter) {
		return buildListOfDTO(featureFilmService.getByFilter(filter));
	}

	private List<FeatureFilmDTO> buildListOfDTO(List<FeatureFilm> featureFilmList) {
		List<FeatureFilmDTO> featureFilmDTOList = new ArrayList<>();

		if (!featureFilmDTOList.isEmpty()) {
			// Isso deveria ser injetado?
			FeatureFilmMapper featureFilmMapper = new FeatureFilmMapper();
			featureFilmList.forEach(featureFilm -> featureFilmDTOList.add(featureFilmMapper.toDTO(featureFilm)));
		}

		return featureFilmDTOList;
	}
}