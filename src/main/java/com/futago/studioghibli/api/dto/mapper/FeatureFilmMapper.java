package com.futago.studioghibli.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.futago.studioghibli.api.dto.FeatureFilmDTO;
import com.futago.studioghibli.domain.entity.FeatureFilm;
import com.futago.studioghibli.domain.entity.Titles;

@Component
public class FeatureFilmMapper {

	public FeatureFilmDTO toDTO(FeatureFilm featureFilm) {
		FeatureFilmDTO featureFilmDTO = new FeatureFilmDTO();

		featureFilmDTO.setId(featureFilm.getId());
		featureFilmDTO.setYear(featureFilm.getYear());
		featureFilmDTO.setDirector(featureFilm.getDirector());
		featureFilmDTO.setScreenwriter(featureFilm.getScreenwriter());
		featureFilmDTO.setProducer(featureFilm.getProducer());
		featureFilmDTO.setMusic(featureFilm.getMusic());
		featureFilmDTO.setRunningTime(featureFilm.getRunningTime());
		
		// Não é possível usar o BeanUtils.copyProperties porque os objetos não são iguais
		featureFilmDTO.setJapanese(featureFilm.getTitles().getJapanese());
		featureFilmDTO.setHepburn(featureFilm.getTitles().getHepburn());
		featureFilmDTO.setEnglish(featureFilm.getTitles().getEnglish());
		featureFilmDTO.setPortuguese(featureFilm.getTitles().getPortuguese());

		return featureFilmDTO;
	}

	public FeatureFilm toEntity(FeatureFilmDTO featureFilmDTO) {
		FeatureFilm featureFilm = new FeatureFilm();
		featureFilm.setTitles(new Titles());

		featureFilm.setId(featureFilmDTO.getId());
		featureFilm.setYear(featureFilmDTO.getYear());
		featureFilm.setDirector(featureFilmDTO.getDirector());
		featureFilm.setScreenwriter(featureFilmDTO.getScreenwriter());
		featureFilm.setProducer(featureFilmDTO.getProducer());
		featureFilm.setMusic(featureFilmDTO.getMusic());
		featureFilm.setRunningTime(featureFilmDTO.getRunningTime());
		
		// Não é possível usar o BeanUtils.copyProperties porque os objetos não são iguais		
		featureFilm.getTitles().setJapanese(featureFilmDTO.getJapanese());
		featureFilm.getTitles().setHepburn(featureFilmDTO.getHepburn());
		featureFilm.getTitles().setEnglish(featureFilmDTO.getEnglish());
		featureFilm.getTitles().setPortuguese(featureFilmDTO.getPortuguese());

		return featureFilm;
	}
}
