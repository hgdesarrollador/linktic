package com.prueba.producto.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.producto.domain.Producto;
import com.prueba.producto.domain.ProductoPK;
import com.prueba.producto.dto.ProductoDTO;
import com.prueba.producto.dto.ProductoDTOList;
import com.prueba.producto.dto.Respuesta;
import com.prueba.producto.exception.ProductoException;
import com.prueba.producto.repository.ProductoRepository;
import com.prueba.producto.service.ProductoService;

import fj.data.Either;

@Service
public class ProductoServiceImp implements ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public Either<Exception, Respuesta> crearProducto (ProductoDTO productoDTO) {
		try {
			Respuesta respuesta;
			if (null == productoDTO.getId() || null == productoDTO.getNombre()
					|| null == productoDTO.getDescripcion() || null == productoDTO.getPrecio()) {
				return Either.left(new ProductoException(
						"Los campos id, nombre, descripcion y precio son obligatorios",
						HttpStatus.BAD_REQUEST));
			}

			ProductoPK productosPK = new ProductoPK();
			productosPK.setId(productoDTO.getId());

			Optional<Producto> productoLlave = productoRepository.findById(productosPK);

			ObjectMapper mapper = new ObjectMapper();
			Producto producto = mapper.convertValue(productoDTO, new TypeReference<Producto>() {
			});
			productoRepository.save(producto);

			if (productoLlave.isPresent()) {
				respuesta = new Respuesta("200", "producto actualizado con exito");
			} else {
				respuesta = new Respuesta("200", "producto creado con exito");
			}

			return Either.right(respuesta);
		} catch (Exception e) {
			return Either.left(new Exception("No es posible crear producto"));
		}
	}
	
	@Override
	public Either<Exception, ProductoDTOList> buscarProducto (Long id) {
		try {
			if (null == id) {
				return Either.left(new ProductoException(
						"El parametro id es obligatorio", HttpStatus.BAD_REQUEST));
			}

			ProductoPK productoPK = new ProductoPK();
			productoPK.setId(id);

			Optional<Producto> prodcuto = productoRepository.findById(productoPK);

			if (prodcuto.isPresent()) {
				ObjectMapper mapper = new ObjectMapper();
				ProductoDTOList productoDto = mapper.convertValue(prodcuto.get(), new TypeReference<ProductoDTOList>() {
				});
				//equipoDto.setPerdida(equipoDto.getValorCompra()*0.04);
				return Either.right(productoDto);
			} else {
				return Either.left(new ProductoException("producto no encontrado con los criterios enviados",
						HttpStatus.BAD_REQUEST));
			}

		} catch (Exception e) {
			return Either.left(new Exception("No es posible encontrar el producto"));
		}
	}
	
	@Override
	public Either<Exception, Respuesta> BorrarProducto (Long id) {
		try {
			Respuesta respuesta = null;
			if (null == id) {
				return Either.left(new ProductoException(
						"El parametro id es obligatorio", HttpStatus.BAD_REQUEST));
			}

			ProductoPK productoPK = new ProductoPK();
			productoPK.setId(id);

			Optional<Producto> productoLlave = productoRepository.findById(productoPK);

			ObjectMapper mapper = new ObjectMapper();
			Producto producto = mapper.convertValue(productoLlave.get(), new TypeReference<Producto>() {
			});
			productoRepository.delete(producto);

			if (productoLlave.isPresent()) {
				respuesta = new Respuesta("200", "producto Borrado con exito");
			}

			return Either.right(respuesta);
		} catch (Exception e) {
			return Either.left(new Exception("No es posible Borrar el producto"));
		}
	}

}
