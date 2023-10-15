package com.prueba.producto.repository;

import org.springframework.data.repository.CrudRepository;

import com.prueba.producto.domain.Producto;
import com.prueba.producto.domain.ProductoPK;

public interface ProductoRepository extends CrudRepository<Producto, ProductoPK>  {

}
