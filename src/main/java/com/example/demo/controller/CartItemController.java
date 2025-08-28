package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.CartItemService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class CartItemController {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired 
	private CartItemService cartItemService;
	
	
	@PostMapping("/cart/add")
	public ResponseEntity<String> addItemToCart(@RequestBody @Valid CartDTO cartDto){
		if(cartItemService.addNewCartItem(cartDto)) {
			return new ResponseEntity<>("OK",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Incorrect input",HttpStatus.BAD_REQUEST);
	} 
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<List<ItemDTO>> getCartContent(@PathVariable int id ){
		List<ItemDTO> items = cartItemService.getAllCartItems(id);
		return items != null ? new ResponseEntity<>(items,HttpStatus.OK) :  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/cart/item/{id}")
	public ResponseEntity<ItemDTO> updateCartItemQuantity(@PathVariable int id, @RequestParam(value = "quantity", required = true) int quantity){
		ItemDTO updatedItem = cartItemService.updateQuantity(id, quantity);
	    return updatedItem != null ? new ResponseEntity<>(updatedItem, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/cart/item/{id}")
	public ResponseEntity<String> removeItemFromCart(@PathVariable int id){
		return cartItemService.deleteItemFromCart(id) ? new ResponseEntity<>("Item Deleted Successfully", HttpStatus.OK) 
				: new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST); 
	}
	
	
	
	}
