package com.rocha.reactive.models.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
public class Producto {
	
	@Id
	private String id;
	private String nombre;
	private Double price;
	private Date createAt;
	
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Producto(String nombre, Double price) {
		super();
		this.nombre = nombre;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", price=" + price + "]";
	}
	
	
	
	
	
	

	
	
	

}
