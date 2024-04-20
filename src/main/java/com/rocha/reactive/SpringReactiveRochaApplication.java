package com.rocha.reactive;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;
import com.rocha.reactive.models.documents.Producto;
import com.rocha.reactive.models.repository.ProductRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringReactiveRochaApplication implements CommandLineRunner{
	
	private final Logger log = LoggerFactory.getLogger(SpringReactiveRochaApplication.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveRochaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		log.info("Started");
		
	}
	
	private void insertInMongoData() {
		Flux.fromIterable(getListProduct())
		.flatMap(p -> productRepository.save(p))
		.subscribe(p ->log.info("product inserted {}", p));
	}
	
	private List<Producto> getListProduct(){
		Faker faker = new Faker();
		return Stream.iterate(1, n->n+1)
				.limit(1000)
				.map(n -> new Producto(faker.name().firstName(), faker.number().randomDouble(2, 100, 1500))).toList();
		
	}

}
