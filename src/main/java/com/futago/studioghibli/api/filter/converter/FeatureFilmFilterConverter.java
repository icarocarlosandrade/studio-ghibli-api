package com.futago.studioghibli.api.filter.converter;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.futago.studioghibli.api.filter.FeatureFilmFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FeatureFilmFilterConverter extends PropertyEditorSupport {

	@Override
	public void setAsText(final String text) {
		setValue(convertFilter(text));
	}

	private FeatureFilmFilter convertFilter(String filter) {
		try {
			// ObjectMapper provides functionality for reading and writing JSON
			ObjectMapper mapper = new ObjectMapper();

			// Method for registering a module that can extend functionality provided by
			// this mapper
			
			// Class that registers capability of serializing java.time objects with the
			// Jackson core
			mapper.registerModule(new JavaTimeModule());

			// Method for changing state of an on/off deserialization feature for this
			// object mapper
			
			// Enumeration that defines simple on/off features that affect the way Java
			// objects are deserialized from JSON
			
			// Feature that determines whether encountering of unknown properties (ones that
			// do not map to a property, and there is no "any setter" or handler that can
			// handle it) should result in a failure (by throwing a JsonMappingException) or
			// not.
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			// Method to deserialize JSON content from given JSON content String
			return mapper.readValue(filter, FeatureFilmFilter.class);
		} catch (Exception e) {
			String error = "Error on converting filter";
			log.error(error, e);
			throw new IllegalArgumentException(error);
		}
	}
}