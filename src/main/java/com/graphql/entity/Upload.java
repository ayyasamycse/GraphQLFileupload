package com.graphql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name="upload")
public class Upload {
	
	@Id
	private int id;
	@Column(name = "fileDescription")
	private String fileDescription;
	
}
