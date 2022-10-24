package com.futago.studioghibli.api.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.futago.studioghibli.api.dto.FeatureFilmDTO;
import com.futago.studioghibli.api.filter.FeatureFilmFilter;
import com.futago.studioghibli.api.service.FeatureFilmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/featurefilm")
@RequiredArgsConstructor
public class FeatureFilmResource {

	private final FeatureFilmService service;

	@PostMapping
	public FeatureFilmDTO save(@RequestBody FeatureFilmDTO featureFilmDTO) {
		return service.save(featureFilmDTO);
	}

	@PutMapping
	public FeatureFilmDTO update(@RequestBody FeatureFilmDTO featureFilmDTO) {
		// Por enquanto utiliza o mesmo método, porque não há nenhuma regra específica no update
		return service.save(featureFilmDTO);
	}

	@GetMapping("/{id}")
	public FeatureFilmDTO getById(@PathVariable Long id) {
		return service.getDTOById(id);
	}

	@GetMapping
	public List<FeatureFilmDTO> findByFilter(
			@RequestParam(value = "filter", required = false) FeatureFilmFilter filter) {
		return service.getAllByFilter(filter);
	}
}