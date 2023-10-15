package com.prueba.producto.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
@IdClass(ProductoPK.class)
public class Producto implements Serializable {
	
	private static final long serialVersionUID = -629636322850605729L;
	
	@Id
	@Column(nullable = false, name = "id")
	private Long id;

	@Id
	@Column(nullable = false, name = "nombre")
	private String nombre;

	@Column(nullable = false, name = "descripcion")
	private String descripcion;

	@Column(nullable = true, name = "precio")
	private BigDecimal precio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
}
