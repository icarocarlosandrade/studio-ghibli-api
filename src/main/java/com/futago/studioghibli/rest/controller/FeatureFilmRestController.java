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
	public List<FeatureFilmDTO> find(@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "director", required = false) String director,
			@RequestParam(value = "title", required = false) String title) {

		List<FeatureFilm> featureFilmList = new ArrayList<>();

		if (year == null || director == null || title == null) {
			if (year != null) {
				featureFilmList = featureFilmService.findByYear(year);
			}

			if (director != null) {
				featureFilmList = featureFilmService.findByDirector(director);
			}

			if (title != null) {
				featureFilmList = featureFilmService.findByTitle(title);
			}

			if (year == null && director == null && title == null) {
				featureFilmList = featureFilmService.findAll();
			}
		}

		return buildListOfDTO(featureFilmList);
	}

	private List<FeatureFilmDTO> buildListOfDTO(List<FeatureFilm> featureFilmList) {
		List<FeatureFilmDTO> featureFilmDTOList = new ArrayList<>();

		if (!featureFilmList.isEmpty()) {
			
			// Isso deveria ser injetado?
			FeatureFilmMapper featureFilmMapper = new FeatureFilmMapper();
			
			for (FeatureFilm featureFilm : featureFilmList) {
				FeatureFilmDTO featureFilmDTO = featureFilmMapper.toDTO(featureFilm);
				featureFilmDTOList.add(featureFilmDTO);
			}
		}

		return featureFilmDTOList;
	}
}