package com.rocha.reactive.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.rocha.reactive.handler.ProductoHandler;

@Configuration
public class RouterConfig {

	@Autowired
	ProductoHandler handler;

	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route(GET("api/v2/productos"), handler::listar)
				.andRoute(GET("api/v2/productos/{id}"), handler::getById)
				.andRoute(POST("api/v2/productos"), handler::createProduct)
				.andRoute(PUT("api/v2/productos/{id}"), handler::editProduct)
				.andRoute(DELETE("api/v2/productos/{id}"), handler::deleteProductById);

	}

}
