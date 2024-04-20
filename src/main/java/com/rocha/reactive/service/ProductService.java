package com.rocha.reactive.service;

import com.rocha.reactive.models.documents.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
	
	public Flux<Producto> findAll();
	public Mono<Producto> findById(String id);
	public Mono<Producto> save(Producto p);
	public Mono<Void> deleteById(String id);

}
