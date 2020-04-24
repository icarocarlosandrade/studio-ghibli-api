package com.futago.studioghibli.dto;

import com.futago.studioghibli.entity.FeatureFilm;

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

	public FeatureFilmDTO(FeatureFilm featureFilm) {
		this.id = featureFilm.getId();
		this.year = featureFilm.getYear();
		this.japanese = featureFilm.getTitles().getJapanese();
		this.hepburn = featureFilm.getTitles().getHepburn();
		this.english = featureFilm.getTitles().getEnglish();
		this.portuguese = featureFilm.getTitles().getPortuguese();
		this.director = featureFilm.getDirector();
		this.screenwriter = featureFilm.getScreenwriter();
		this.producer = featureFilm.getProducer();
		this.music = featureFilm.getMusic();
		this.runningTime = featureFilm.getRunningTime();
	}
}
