package com.rocha.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocha.reactive.models.documents.Producto;
import com.rocha.reactive.models.repository.ProductRepository;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("rest")
public class ProductoController2 {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/listar")
	public Flux<Producto> listar(Model model) {
		return productRepository.findAll();
		
	}

}
