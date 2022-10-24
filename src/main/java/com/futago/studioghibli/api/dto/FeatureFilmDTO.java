package com.futago.studioghibli.api.dto;

import lombok.Data;

@Data
public class FeatureFilmDTO {

	private Long id;
	private String year;
	private String japanese;
	private String hepburn;
	private String english;
	private String portuguese;
	private String director;
	private String screenwriter;
	private String producer;
	private String music;
	private String runningTime;
}
