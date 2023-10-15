package com.prueba.producto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class Respuesta {

	private final String code;
	private final String description;

	public Respuesta(String code, String description) {

		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
