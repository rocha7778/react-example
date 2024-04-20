package com.rocha.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.rocha.reactive.models.documents.Producto;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class SpringReactiveRochaApplicationTests {

	@Autowired
	private WebTestClient webClient;
	
	@Test
	void listartTest() {
		
		webClient.get().uri("/api/v2/productos")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Producto.class);
		
	}

}
