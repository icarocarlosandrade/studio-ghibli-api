package com.futago.studioghibli.dto;

import com.futago.studioghibli.entity.FeatureFilm;

import lombok.Data;

@Data
public class FeatureFilmDTO {

	private Integer id;
	private String year;
	private String title;
	private String director;
	private String screenwriter;
	private String producer;
	private String music;
	private String runningTime;

	public FeatureFilmDTO(FeatureFilm featureFilm) {
		this.id = featureFilm.getId();
		this.year = featureFilm.getYear();
		this.title = featureFilm.getTitle();
		this.director = featureFilm.getDirector();
		this.screenwriter = featureFilm.getScreenwriter();
		this.producer = featureFilm.getProducer();
		this.music = featureFilm.getMusic();
		this.runningTime = featureFilm.getRunningTime();
	}
}
