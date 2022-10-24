package com.futago.studioghibli.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private int status;
	private String message;
	private long timestamp;

}