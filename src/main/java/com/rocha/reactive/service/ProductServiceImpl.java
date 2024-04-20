package com.rocha.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocha.reactive.models.documents.Producto;
import com.rocha.reactive.models.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRespository;
	
	@Override
	public Flux<Producto> findAll() {
		return productRespository.findAll();
	}

	@Override
	public Mono<Producto> findById(String id) {
		return productRespository.findById(id);
	}

	@Override
	public Mono<Producto> save(Producto p) {
		Mono<Producto> result = productRespository.save(p);
		return result;
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return productRespository.deleteById(id);
	}

}
