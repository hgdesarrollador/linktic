package com.prueba.producto.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.prueba.producto.dto.Error;
import com.prueba.producto.exception.ProductoException;


@Service
public class ErrorProductoService {
	
	public Error getError(Exception e) {

		if (e instanceof ProductoException) {
			return new Error("EquiposException", ((ProductoException) e).getCausaError(),
					((ProductoException) e).getStatus());
		}

		Map<String, String> params = new HashMap<>();
		params.put("Exception", e.getClass() + "-" + e.getMessage());
		params.put("Time", LocalDateTime.now().toString());

		return new Error("EquiposException", "Unknown Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
