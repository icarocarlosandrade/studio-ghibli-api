package com.futago.studioghibli.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.futago.studioghibli.dto.FeatureFilmDTO;
import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.service.FeatureFilmService;

@RestController
@RequestMapping("/featurefilm")
public class FeatureFilmRestController {

	@Autowired
	private FeatureFilmService featureFilmService;

	@GetMapping("{id}")
	public FeatureFilmDTO findById(@PathVariable Integer id) {
		FeatureFilm film = featureFilmService.findById(id);
		FeatureFilmDTO filmDTO = new FeatureFilmDTO(film);
		return filmDTO;
	}

	@GetMapping
	public List<FeatureFilmDTO> find(@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "director", required = false) String director) {

		List<FeatureFilm> films = new ArrayList<>();

		if (year == null || director == null) {
			if (year != null) {
				films = featureFilmService.findByYear(year);
			}

			if (director != null) {
				films = featureFilmService.findByDirector(director);
			}

			if (year == null && director == null) {
				films = featureFilmService.findAll();
			}
		}

		return buildListOfDTO(films);
	}

	public List<FeatureFilmDTO> findByDirector() {
		List<FeatureFilm> films = featureFilmService.findAll();
		List<FeatureFilmDTO> filmsDTO = new ArrayList<>();

		if (!films.isEmpty()) {
			for (FeatureFilm film : films) {
				FeatureFilmDTO filmDTO = new FeatureFilmDTO(film);
				filmsDTO.add(filmDTO);
			}
		}

		return filmsDTO;
	}

	public List<FeatureFilmDTO> findAll() {
		List<FeatureFilm> films = featureFilmService.findAll();
		List<FeatureFilmDTO> filmsDTO = new ArrayList<>();

		if (!films.isEmpty()) {
			for (FeatureFilm film : films) {
				FeatureFilmDTO filmDTO = new FeatureFilmDTO(film);
				filmsDTO.add(filmDTO);
			}
		}

		return filmsDTO;
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