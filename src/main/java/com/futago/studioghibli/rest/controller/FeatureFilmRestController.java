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
import com.futago.studioghibli.rest.filter.FeatureFilmFilter;
import com.futago.studioghibli.service.FeatureFilmService;

@RestController
@RequestMapping("/featurefilm")
public class FeatureFilmRestController {

	private FeatureFilmService service;

	@Autowired
	public FeatureFilmRestController(FeatureFilmService featureFilmService) {
		this.service = featureFilmService;
	}

	@GetMapping("{id}")
	public FeatureFilmDTO findById(@PathVariable Long id) {
		FeatureFilm film = service.findById(id);

		if (film == null) {
			throw new NotFoundException("Feature film not found (Id = " + id + ")");
		}

		FeatureFilmDTO filmDTO = new FeatureFilmDTO(film);
		return filmDTO;
	}

	@GetMapping
	public List<FeatureFilmDTO> find(@RequestParam(value = "filter", required = false) FeatureFilmFilter filter) {
		return buildListOfDTO(service.getByFilter(filter));
	}

	private List<FeatureFilmDTO> buildListOfDTO(List<FeatureFilm> films) {
		List<FeatureFilmDTO> filmsDTO = new ArrayList<>();

		if (!films.isEmpty()) {
			films.forEach(film -> filmsDTO.add(new FeatureFilmDTO(film)));
		}

		return filmsDTO;
	}
}