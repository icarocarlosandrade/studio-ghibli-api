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
		FeatureFilm film = featureFilmService.findById(id);
		
		if (film == null) {
			throw new NotFoundException("Feature film not found (Id = " + id + ")");
		}
		
		FeatureFilmDTO filmDTO = new FeatureFilmDTO(film);
		return filmDTO;
	}

	@GetMapping
	public List<FeatureFilmDTO> find(@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "director", required = false) String director,
			@RequestParam(value = "title", required = false) String title) {

		List<FeatureFilm> films = new ArrayList<>();

		if (year == null || director == null || title == null) {
			if (year != null) {
				films = featureFilmService.findByYear(year);
			}

			if (director != null) {
				films = featureFilmService.findByDirector(director);
			}

			if (title != null) {
				films = featureFilmService.findByTitle(title);
			}

			if (year == null && director == null && title == null) {
				films = featureFilmService.findAll();
			}
		}

		return buildListOfDTO(films);
	}

	private List<FeatureFilmDTO> buildListOfDTO(List<FeatureFilm> films) {
		List<FeatureFilmDTO> filmsDTO = new ArrayList<>();

		if (!films.isEmpty()) {
			for (FeatureFilm film : films) {
				FeatureFilmDTO filmDTO = new FeatureFilmDTO(film);
				filmsDTO.add(filmDTO);
			}
		}

		return filmsDTO;
	}
}