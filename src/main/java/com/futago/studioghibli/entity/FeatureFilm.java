package com.futago.studioghibli.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "feature_film")
@Data
public class FeatureFilm {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feature_film_seq")
	@SequenceGenerator(name = "feature_film_seq", sequenceName = "feature_film_id_seq", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "year")
	private String year;

	@Column(name = "director")
	private String director;

	@Column(name = "screenwriter")
	private String screenwriter;

	@Column(name = "producer")
	private String producer;

	@Column(name = "music")
	private String music;

	@Column(name = "running_time")
	private String runningTime;

	@JoinColumn(name = "titles_id", referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.ALL)
	private Titles titles;
}