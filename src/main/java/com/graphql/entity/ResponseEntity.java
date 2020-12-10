package com.graphql.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode
@Getter
@Setter
public class ResponseEntity {
	String message;
}
