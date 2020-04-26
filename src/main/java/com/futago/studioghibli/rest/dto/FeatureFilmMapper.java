package com.futago.studioghibli.rest.dto;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.entity.Titles;

public class FeatureFilmMapper {

	public FeatureFilmDTO toDTO(FeatureFilm featureFilm) {
		FeatureFilmDTO featureFilmDTO = new FeatureFilmDTO();

		featureFilmDTO.setId(featureFilm.getId());
		featureFilmDTO.setYear(featureFilm.getYear());
		featureFilmDTO.setJapanese(featureFilm.getTitles().getJapanese());
		featureFilmDTO.setHepburn(featureFilm.getTitles().getHepburn());
		featureFilmDTO.setEnglish(featureFilm.getTitles().getEnglish());
		featureFilmDTO.setPortuguese(featureFilm.getTitles().getPortuguese());
		featureFilmDTO.setDirector(featureFilm.getDirector());
		featureFilmDTO.setScreenwriter(featureFilm.getScreenwriter());
		featureFilmDTO.setProducer(featureFilm.getProducer());
		featureFilmDTO.setMusic(featureFilm.getMusic());
		featureFilmDTO.setRunningTime(featureFilm.getRunningTime());

		return featureFilmDTO;
	}

	public FeatureFilm toEntity(FeatureFilmDTO featureFilmDTO) {
		FeatureFilm featureFilm = new FeatureFilm();
		featureFilm.setTitles(new Titles());

		featureFilm.setId(featureFilmDTO.getId());
		featureFilm.setYear(featureFilmDTO.getYear());
		featureFilm.getTitles().setJapanese(featureFilmDTO.getJapanese());
		featureFilm.getTitles().setHepburn(featureFilmDTO.getHepburn());
		featureFilm.getTitles().setEnglish(featureFilmDTO.getEnglish());
		featureFilm.getTitles().setPortuguese(featureFilmDTO.getPortuguese());
		featureFilm.setDirector(featureFilmDTO.getDirector());
		featureFilm.setScreenwriter(featureFilmDTO.getScreenwriter());
		featureFilm.setProducer(featureFilmDTO.getProducer());
		featureFilm.setMusic(featureFilmDTO.getMusic());
		featureFilm.setRunningTime(featureFilmDTO.getRunningTime());

		return featureFilm;
	}
}
