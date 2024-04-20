package com.rocha.reactive.handler;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.rocha.reactive.models.documents.Producto;
import com.rocha.reactive.service.ProductService;

import reactor.core.publisher.Mono;

@Component
public class ProductoHandler {

	@Autowired
	private ProductService productService;

	public Mono<ServerResponse> listar(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.findAll(),
				Producto.class);

	}

	public Mono<ServerResponse> getById(ServerRequest request) {

		return productService.findById(request.pathVariable("id"))
				.flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(p))
				.switchIfEmpty(ServerResponse.notFound().build());

	}

	public Mono<ServerResponse> createProduct(ServerRequest request) {

		Mono<Producto> producto = request.bodyToMono(Producto.class);

		return producto.flatMap(p -> {
			p.setCreateAt(new Date());
			return productService.save(p);
		}).flatMap(p -> ServerResponse.created(URI.create("api/v2/productos/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON).bodyValue(p));

	}

	public Mono<ServerResponse> editProduct(ServerRequest request) {

		Mono<Producto> productoDatabase = productService.findById(request.pathVariable("id"));
		Mono<Producto> producto = request.bodyToMono(Producto.class);

		return productoDatabase.zipWith(producto, (db, rq) -> {
			db.setNombre(rq.getNombre());
			db.setPrice(rq.getPrice());
			return db;
		}).flatMap(p -> ServerResponse.created(URI.create("api/v2/productos/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(productService.save(p), Producto.class))
				.switchIfEmpty(ServerResponse.notFound().build());

	}



	public Mono<ServerResponse> deleteProductById(ServerRequest request) {
		Mono<Producto> productoDatabase = productService.findById(request.pathVariable("id"));
		return productoDatabase
				.flatMap(p -> productService.deleteById(p.getId()).then(ServerResponse.noContent().build()))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

}
