package com.prueba.producto.service;

import org.springframework.stereotype.Service;

import com.prueba.producto.dto.ProductoDTO;
import com.prueba.producto.dto.ProductoDTOList;
import com.prueba.producto.dto.Respuesta;

import fj.data.Either;

@Service
public interface ProductoService {
	
	Either<Exception, Respuesta> crearProducto (ProductoDTO productoDTO);
	
	Either<Exception, ProductoDTOList> buscarProducto (Long id);
	
	Either<Exception, Respuesta> BorrarProducto (Long id);

}
