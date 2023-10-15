package com.prueba.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.producto.dto.Error;
import com.prueba.producto.dto.ProductoDTO;
import com.prueba.producto.dto.ProductoDTOList;
import com.prueba.producto.dto.Respuesta;
import com.prueba.producto.service.ErrorProductoService;
import com.prueba.producto.service.ProductoService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping(path = "${controller.properties.base-path}")
@RestController
@Controller
public class ProductoController {
	
	@Autowired
	private ErrorProductoService errorProductoService;

	@Autowired
	private ProductoService productoService;
	
	@PostMapping(value = "/crearProducto")
	public ResponseEntity<?> crearProducto (@RequestBody ProductoDTO productoDTO) {

		Either<Exception, Respuesta> resultEither = productoService.crearProducto(productoDTO);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorProductoService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}
	
	@GetMapping(value = "/buscaProducto")
	public ResponseEntity<?> buscarProducto (@RequestParam(value = "id", required = true) Long id) {

		Either<Exception, ProductoDTOList> resultEither = productoService.buscarProducto(id);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorProductoService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}
	
	@PostMapping(value = "/borrarProducto")
	public ResponseEntity<?> borrarProducto (@RequestParam(value = "id", required = true) Long id) {

		Either<Exception, Respuesta> resultEither = productoService.BorrarProducto(id);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorProductoService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}
}
