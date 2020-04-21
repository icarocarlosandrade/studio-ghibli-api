package com.futago.studioghibli.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "titles")
@Data
public class Titles {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "titles_seq")
	@SequenceGenerator(name = "titles_seq", sequenceName = "titles_id_seq", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "japanese")
	private String japanese;
	
	@Column(name = "hepburn")
	private String hepburn;
	
	@Column(name = "english")
	private String english;
	
	@Column(name = "portuguese")
	private String portuguese;

}
