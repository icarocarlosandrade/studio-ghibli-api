package com.futago.studioghibli.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.futago.studioghibli.rest.filter.FeatureFilmFilter;
import com.futago.studioghibli.rest.filter.converter.FeatureFilmFilterConverter;

@ControllerAdvice
public class GlobalBindingInitializer {

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(FeatureFilmFilter.class, new FeatureFilmFilterConverter());
	}
}