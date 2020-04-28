package com.futago.studioghibli.rest.filter;

import lombok.Data;

@Data
public class FeatureFilmFilter {

	private String title;
	private String year;
	private String director;
	private String screenwriter;
	private String producer;
	private String music;
}