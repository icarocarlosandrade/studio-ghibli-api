package com.futago.studioghibli.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class FeatureFilm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Integer id;

	private String year;

	private String director;

	private String screenwriter;

	private String producer;

	private String music;

	private String runningTime;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "titles_id", nullable = false)
	private Titles titles;
}