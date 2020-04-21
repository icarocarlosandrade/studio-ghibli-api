package com.futago.studioghibli.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.service.FeatureFilmService;

@RestController
@RequestMapping("/featurefilm")
public class FeatureFilmRestController {

	@Autowired
	private FeatureFilmService featureFilmService;

	@GetMapping("{id}")
	public FeatureFilm findbyId(@PathVariable Integer id) {
		return featureFilmService.findById(id);
	}

	@GetMapping
	public List<FeatureFilm> findAll() {
		return featureFilmService.findAll();
	}

}