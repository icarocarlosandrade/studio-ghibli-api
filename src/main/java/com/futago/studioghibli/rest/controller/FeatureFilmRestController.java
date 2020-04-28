package com.futago.studioghibli.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.exception.NotFoundException;
import com.futago.studioghibli.rest.dto.FeatureFilmDTO;
import com.futago.studioghibli.rest.dto.FeatureFilmMapper;
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

	@PostMapping
	public FeatureFilmDTO save(@RequestBody FeatureFilmDTO filmDTO) {
		// TODO Isso deveria ser injetado?
		FeatureFilmMapper mapper = new FeatureFilmMapper();
		FeatureFilm film = mapper.toEntity(filmDTO);
		film.setId(0L);
		film = service.save(film);
		filmDTO.setId(film.getId());
		return filmDTO;
	}

	@PutMapping
	public FeatureFilmDTO update(@RequestBody FeatureFilmDTO filmDTO) {
		if (filmDTO.getId() == null) {
			// TODO Deveria ser Bad Request
			throw new NotFoundException("Id is null");
		}

		// TODO Isso deveria ser injetado?
		FeatureFilmMapper mapper = new FeatureFilmMapper();
		FeatureFilm film = mapper.toEntity(filmDTO);
		film = service.save(film);
		return filmDTO;
	}

	@GetMapping("{id}")
	public FeatureFilmDTO findById(@PathVariable Long id) {
		FeatureFilm film = service.findById(id);

		// TODO Isso deveria estar aqui ou no Service?
		if (film == null) {
			throw new NotFoundException("Feature film not found (Id = " + id + ")");
		}

		// TODO Isso deveria ser injetado?
		FeatureFilmMapper mapper = new FeatureFilmMapper();

		FeatureFilmDTO filmDTO = mapper.toDTO(film);
		return filmDTO;
	}

	@GetMapping
	public List<FeatureFilmDTO> findByFilter(
			@RequestParam(value = "filter", required = false) FeatureFilmFilter filter) {
		return buildListOfDTO(service.findByFilter(filter));
	}

	private List<FeatureFilmDTO> buildListOfDTO(List<FeatureFilm> filmList) {
		List<FeatureFilmDTO> filmDTOList = new ArrayList<>();

		if (!filmList.isEmpty()) {
			// TODO Isso deveria ser injetado?
			FeatureFilmMapper mapper = new FeatureFilmMapper();

			filmList.forEach(film -> filmDTOList.add(mapper.toDTO(film)));
		}

		return filmDTOList;
	}
}