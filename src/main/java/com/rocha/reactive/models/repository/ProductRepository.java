package com.rocha.reactive.models.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rocha.reactive.models.documents.Producto;

public interface ProductRepository  extends ReactiveMongoRepository<Producto, String>{

}
