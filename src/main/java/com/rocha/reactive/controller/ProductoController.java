package com.rocha.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rocha.reactive.models.documents.Producto;
import com.rocha.reactive.service.ProductService;

import reactor.core.publisher.Flux;

@Controller
public class ProductoController {

	@Autowired
	private ProductService productService;

	@GetMapping("/listart")
	public String listar(Model model) {
		Flux<Producto> productList = productService.findAll();
		model.addAttribute("productos", productList);
		model.addAttribute("titulo", "Listado de productos");
		return "listar";
	}

}
