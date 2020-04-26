package com.futago.studioghibli.rest.filter.converter;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.futago.studioghibli.rest.filter.FeatureFilmFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FeatureFilmFilterConverter extends PropertyEditorSupport {

	@Override
	public void setAsText(final String text) {
		setValue(converterFiltro(text));
	}

	private FeatureFilmFilter converterFiltro(String filter) {
		try {
			return new ObjectMapper().registerModule(new JavaTimeModule())
					.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.readValue(filter, FeatureFilmFilter.class);
		} catch (Exception e) {
			String erro = "Erro ao converter filtro.";
			log.error(erro, e);
			throw new IllegalArgumentException(erro);
		}
	}
}