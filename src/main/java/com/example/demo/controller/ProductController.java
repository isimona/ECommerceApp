package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProductService productService;
	
	
	 @GetMapping("/products")
	 public ResponseEntity<Page<Item>> getAllProducts(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size,
	            @RequestParam(defaultValue = "name") String sortBy,
	            @RequestParam(defaultValue = "true") boolean ascending
	    ) {
	        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	        Pageable pageable = PageRequest.of(page, size, sort);
	        return new ResponseEntity<>(productService.findAll(pageable),HttpStatus.OK);
	    }
	 
	 @GetMapping("/products/{id}")
	 public ResponseEntity<Item> getItemById(@PathVariable int id) {
		 if(itemRepository.findById(id).isPresent()) {
			 Optional<Item> i = itemRepository.findById(id);
			 Item item = i.get();
			 return new ResponseEntity<>(item, HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
	 }
	
}
